package com.example.user_microservice.entity;

import jakarta.persistence.Column;
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
@NoArgsConstructor
@Data
@Table(name="payments_table")
public class Payment {
	
	@Id
	@Column(name="payment_id")
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private int orderId;
	private double amount;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	private boolean paymentStatus;
	private String paymentMethod;

}
