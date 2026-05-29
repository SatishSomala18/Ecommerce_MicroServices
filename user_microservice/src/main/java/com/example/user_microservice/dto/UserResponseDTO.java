package com.example.user_microservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserResponseDTO {

	private int id;
	private String name;
	private String email;
	private String address;
	
}
