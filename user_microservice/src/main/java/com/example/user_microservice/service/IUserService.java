package com.example.user_microservice.service;

import java.util.List;

import com.example.user_microservice.dto.UserDTO;
import com.example.user_microservice.dto.UserResponseDTO;

public interface IUserService {

	public UserResponseDTO addUser(UserDTO u);
	public UserResponseDTO updateUser(UserDTO u,int uid);
	
	public UserResponseDTO getUserById(int id);
	
	public void deleteUserById(int id);
	
	public List<UserResponseDTO> getAllUsers();
	
	public UserResponseDTO getUserByName(String name);
	

	
}
