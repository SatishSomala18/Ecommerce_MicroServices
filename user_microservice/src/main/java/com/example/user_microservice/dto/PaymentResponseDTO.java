package com.example.user_microservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentResponseDTO {

	
	private int id;
	private int orderId;
	private double amount;
	private int userId;
	private boolean paymentStatus;
	private String paymentMethod;
}
