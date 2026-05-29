package com.example.order_microservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderDTO {
	
	@NotNull
	private int userId;
	
	@Null
	private double totalAmount;
	
    private boolean status;

	
	
	/* @Min(100) private double totalAmount; private boolean status; */
	 

}
