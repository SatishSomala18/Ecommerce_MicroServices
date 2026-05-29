package com.example.user_microservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDTO {

	private int orderId;
	private boolean paymentStatus;
	private String paymentMethod;
}
