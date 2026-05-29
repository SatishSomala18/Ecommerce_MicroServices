package com.example.product_microservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductDTO {
	
	
	@NotBlank
	@Size(min=4 ,max=15)
	private String name;
	@Min(10)
	private double price;
	private int stock;
	
	@Pattern(regexp="[A-Z][a-z]{4,14}")
	private String category;

}
