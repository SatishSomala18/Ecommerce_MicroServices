package com.example.order_microservice.service;

import java.util.List;

import com.example.order_microservice.dto.OrderItemDTO;
import com.example.order_microservice.dto.OrderItemResponseDTO;


public interface IOrderItemService{

	
	public OrderItemResponseDTO addOrderItem(OrderItemDTO dtitem);
	public OrderItemResponseDTO updateOrderItem(OrderItemDTO item,int itemid);
	public OrderItemResponseDTO getOrderItemById(int id);
	public void deleteOrderItemById(int id);
	public List<OrderItemResponseDTO> getOrderItems();
	
	public List<OrderItemResponseDTO> getOrderItemsByQuantityRange(int q1,int q2);
	
	public List<OrderItemResponseDTO> getOrderItemByOrderId(int id);
	
}
