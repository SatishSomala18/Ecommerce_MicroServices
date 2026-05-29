package com.example.order_microservice.dto;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class OrderResponseDTO {
	

	  private int id;
	    private int userId;
	    private double totalAmount;
	    
	    private boolean status;

	    private List<OrderItemResponseDTO> items;


}
