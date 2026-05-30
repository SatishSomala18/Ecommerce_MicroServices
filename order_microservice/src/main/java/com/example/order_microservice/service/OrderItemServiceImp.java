package com.example.order_microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.order_microservice.dto.OrderItemDTO;
import com.example.order_microservice.dto.OrderItemResponseDTO;
import com.example.order_microservice.dto.ProductDTO;
import com.example.order_microservice.entity.Order;
import com.example.order_microservice.entity.OrderItem;
import com.example.order_microservice.exceptions.OrderItemNotFoundException;
import com.example.order_microservice.exceptions.OrderNotFoundException;
import com.example.order_microservice.repository.OrderItemRepository;
import com.example.order_microservice.repository.OrderRepository;

@Service
public class OrderItemServiceImp implements IOrderItemService {

	@Autowired
	RestTemplate rt;
	@Autowired
	OrderItemRepository repo;

	@Autowired
	OrderRepository repo1;

	@Override
	public OrderItemResponseDTO addOrderItem(OrderItemDTO dtitem) {

		OrderItem item = new OrderItem();

		Order ord = repo1.findById(dtitem.getOrderId()).orElse(null);
		if(ord==null) throw new OrderNotFoundException("Order Not Found With ID :"+dtitem.getOrderId());

		item.setProductId(dtitem.getProductId());
		item.setQuantity(dtitem.getQuantity());
		item.setOrder(ord);

		String url = "http://localhost:8181/product/getbyid/" + dtitem.getProductId();

		ProductDTO prod = rt.getForObject(url, ProductDTO.class);

		double total = prod.getPrice() * item.getQuantity();

		item.setTotalPrice(total);

		OrderItem ord1 = repo.save(item);

		OrderItemResponseDTO res = new OrderItemResponseDTO();
		res.setId(ord1.getId());
		res.setTotalPrice(ord1.getTotalPrice());
		res.setProductId(ord1.getProductId());
		res.setQuantity(ord1.getQuantity());
		res.setOrderId(ord1.getOrder().getId());

		updateTotalPrice(dtitem.getOrderId());
		return res;
	}

	@Override
	public OrderItemResponseDTO updateOrderItem(OrderItemDTO dtitem, int itemId) {
		
		OrderItemResponseDTO orderitem=getOrderItemById(itemId);
		
		OrderItem item = new OrderItem();

		Order ord = repo1.findById(dtitem.getOrderId()).orElse(null);
		if(ord==null) throw new OrderNotFoundException("Order Not Found With ID :"+dtitem.getOrderId());

		item.setProductId(dtitem.getProductId());
		item.setQuantity(dtitem.getQuantity());
		item.setId(itemId);
		item.setOrder(ord);

		
		String url = "http://localhost:8181/product/getbyid/" + dtitem.getProductId();

		ProductDTO prod = rt.getForObject(url, ProductDTO.class);

		double total = prod.getPrice() * item.getQuantity();

		item.setTotalPrice(total);

		OrderItem ord1 = repo.save(item);

		OrderItemResponseDTO res = new OrderItemResponseDTO();
		res.setId(ord1.getId());
		res.setTotalPrice(ord1.getTotalPrice());
		res.setProductId(ord1.getProductId());
		res.setQuantity(ord1.getQuantity());
		res.setOrderId(ord1.getOrder().getId());

		updateTotalPrice(dtitem.getOrderId());
		return res;
	}

	@Override
	public OrderItemResponseDTO getOrderItemById(int id) {
		OrderItem ord1 = repo.findById(id).orElse(null);
		if(ord1==null) throw new OrderItemNotFoundException("Order Item Not Found With ID :"+id);

		return convertToDTO(ord1);
	}

	@Override
	public void deleteOrderItemById(int id) {

		OrderItem ord1 = repo.findById(id).orElse(null);
		if(ord1==null) throw new OrderItemNotFoundException("Order Item Not Found With ID :"+id);
		
		
		repo.deleteById(id);

		Order or = ord1.getOrder();
		updateTotalPrice(or.getId());
	}

	@Override
	public List<OrderItemResponseDTO> getOrderItems() {

		List<OrderItemResponseDTO> list = new ArrayList<>();

		List<OrderItem> orditems = repo.findAll();

		for (OrderItem ord1 : orditems) {

			list.add(convertToDTO(ord1));
		}
		return list;
	}

	@Override
	public List<OrderItemResponseDTO> getOrderItemsByQuantityRange(int q1, int q2) {

		List<OrderItemResponseDTO> list = new ArrayList<>();

		List<OrderItem> orditems = repo.getOrderItemsByQuantityRange(q1, q2);

		for (OrderItem ord1 : orditems) {

			list.add(convertToDTO(ord1));
		}
		return list;
	}

	@Override
	public List<OrderItemResponseDTO> getOrderItemByOrderId(int id) {
		// TODO Auto-generated method stub

		List<OrderItemResponseDTO> list = new ArrayList<>();

		List<OrderItem> orditems = repo.findByOrderId(id);

		for (OrderItem ord1 : orditems) {

			list.add(convertToDTO(ord1));
		}
		return list;

	}

	public void updateTotalPrice(int orderId) {

		Order order = repo1.findById(orderId).orElse(null);

		List<OrderItem> items = repo.findByOrderId(orderId);

		double total = 0;

		for (OrderItem item : items) {

			total += item.getTotalPrice();
		}

		order.setTotalAmount(total);

		repo1.save(order);
	}

	public OrderItemResponseDTO convertToDTO(OrderItem item) {
		OrderItemResponseDTO response = new OrderItemResponseDTO();
		response.setId(item.getId());
		response.setOrderId(item.getOrder().getId());
		response.setProductId(item.getProductId());
		response.setQuantity(item.getQuantity());
		response.setTotalPrice(item.getTotalPrice());

		return response;
	}

}
