package com.example.order_microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.order_microservice.entity.OrderItem;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{
	
	@Query("select oi from OrderItem oi where oi.quantity between ?1 and ?2")
	public List<OrderItem> getOrderItemsByQuantityRange(int q1,int q2);
	
	
	public List<OrderItem> findByOrderId(int id);

}
