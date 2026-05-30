package com.example.order_microservice.service;

import java.util.List;

import com.example.order_microservice.dto.OrderDTO;
import com.example.order_microservice.dto.OrderResponseDTO;
import com.example.order_microservice.entity.Order;


public interface IOrderService {

	public OrderResponseDTO addOrder(OrderDTO ord);
	
	public OrderResponseDTO updateOrderStatus(int oid,boolean status);
	
	public OrderResponseDTO updateOrder(OrderDTO ord,int oid);
	
	public OrderResponseDTO getOrderById(int id);
	
	public void deleteById(int id);

	public List<OrderResponseDTO> getAllOrders();
	
	public List<OrderResponseDTO> getOrdersByStatus(boolean status);
	
	public List<OrderResponseDTO> getOrdersByUserId(int oid);
}
