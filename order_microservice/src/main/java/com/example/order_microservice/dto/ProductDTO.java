package com.example.order_microservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {

	
	private int id;
	private String name;
	private double price;
	private int stock;
	private String category;
}
