package com.example.order_microservice.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "order_table")
@Data
@NoArgsConstructor
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int userId;
	private double totalAmount;
	@OneToMany(mappedBy ="order" ,cascade= CascadeType.ALL)
	private List<OrderItem> items;
    private boolean status;
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime orderDate;

}
