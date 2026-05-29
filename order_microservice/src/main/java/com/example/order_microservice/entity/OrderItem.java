package com.example.order_microservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_item_table")
@NoArgsConstructor
@Data
public class OrderItem {
	
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	private int productId;
	private int quantity;
	private double totalPrice;

	
	
	
	
	
	

}
