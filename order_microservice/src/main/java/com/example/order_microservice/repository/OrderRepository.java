package com.example.order_microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.order_microservice.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
	
	
	public List<Order> getOrdersByStatus(boolean status);
	
	@Modifying
	@Query("update Order o set o.status = ?1  where o.Id = ?2 ")
    public int updateOrderStatus(boolean status,int oid);

}
