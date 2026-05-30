package com.example.user_microservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
	
	
	@NotNull
	private String name;
	@Email
	private String email;
	private String password;
	private String address;

}
