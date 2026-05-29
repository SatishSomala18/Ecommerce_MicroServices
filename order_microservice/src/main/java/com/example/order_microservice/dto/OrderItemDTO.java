package com.example.order_microservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderItemDTO {

	@NotNull
	private int orderId;
	@NotNull
	private int productId;
	@Min(1)
	@Max(100)
	private int quantity;
	
	
}
