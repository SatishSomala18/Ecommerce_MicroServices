package com.example.order_microservice.service;

import java.util.List;

import com.example.order_microservice.dto.OrderDTO;
import com.example.order_microservice.dto.OrderResponseDTO;


public interface IOrderService {

	public OrderResponseDTO addOrder(OrderDTO ord);
	
	public OrderResponseDTO updateOrderStatus(int oid,boolean status);
	
	public OrderResponseDTO getOrderById(int id);
	
	public void deleteById(int id);

	public List<OrderResponseDTO> getAllOrders();
	
	public List<OrderResponseDTO> getOrdersByStatus(boolean status);
}
