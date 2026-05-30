package com.example.order_microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.order_microservice.dto.OrderDTO;
import com.example.order_microservice.dto.OrderResponseDTO;
import com.example.order_microservice.entity.Order;
import com.example.order_microservice.exceptions.OrderNotFoundException;
import com.example.order_microservice.repository.OrderRepository;

import jakarta.transaction.Transactional;


@Transactional
@Service
public class OrderServiceImp implements IOrderService {

	@Autowired
	OrderRepository repo;

	@Autowired
	IOrderItemService itemrepo;

	@Override
	public OrderResponseDTO addOrder(OrderDTO ord) {

		Order or = new Order();
		or.setUserId(ord.getUserId());
		or.setTotalAmount(0);
		or.setStatus(true);

		or = repo.save(or);

		return convertToDTO(or);
	}
	
	@Override
	public OrderResponseDTO updateOrder(OrderDTO ord,int oid) {

		Order or = new Order();
		or.setId(oid);
		or.setUserId(ord.getUserId());
		or.setTotalAmount(0);
		or.setStatus(true);
		
		OrderResponseDTO order=getOrderById(oid);
		
		or = repo.save(or);

		return convertToDTO(or);
	}
	
	

	@Override
	public OrderResponseDTO updateOrderStatus(int oid, boolean status) {

		OrderResponseDTO order=getOrderById(oid);
		repo.updateOrderStatus(status, oid);
		Order or = repo.findById(oid).orElse(null);
		return convertToDTO(or);
	}

	@Override
	public OrderResponseDTO getOrderById(int id) {
		Order or = repo.findById(id).orElse(null);
		if(or==null) throw new OrderNotFoundException("Order Not Found With ID : "+id);

		return convertToDTO(or);
	}

	@Override
	public void deleteById(int id) {
		repo.deleteById(id);

	}

	@Override
	public List<OrderResponseDTO> getAllOrders() {
		List<Order> list = repo.findAll();

		List<OrderResponseDTO> ans = new ArrayList<>();
		for (Order or : list) {
			ans.add(convertToDTO(or));

		}
		return ans;
	}

	@Override
	public List<OrderResponseDTO> getOrdersByStatus(boolean status) {
		List<Order> list = repo.findByStatus(status);

		List<OrderResponseDTO> ans = new ArrayList<>();
		for (Order or : list) {

			ans.add(convertToDTO(or));

		}
		return ans;
	}

	public OrderResponseDTO convertToDTO(Order or) {
		OrderResponseDTO response = new OrderResponseDTO();
		response.setTotalAmount(or.getTotalAmount());
		response.setUserId(or.getUserId());
		response.setStatus(or.isStatus());
		response.setId(or.getId());
		response.setItems(itemrepo.getOrderItemByOrderId(or.getId()));
		return response;
	}

	@Override
	public List<OrderResponseDTO> getOrdersByUserId(int oid) {
		List<Order> list = repo.findByUserId(oid);

		List<OrderResponseDTO> ans = new ArrayList<>();
		for (Order or : list) {

			ans.add(convertToDTO(or));

		}
		return ans;
	}

}
