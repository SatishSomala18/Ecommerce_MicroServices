package com.example.user_microservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_microservice.dto.UserDTO;
import com.example.user_microservice.dto.UserResponseDTO;
import com.example.user_microservice.entity.User;
import com.example.user_microservice.service.IUserService;


@RestController
@RequestMapping("/user")
public class UserRestController {
	
	@Autowired
	IUserService service;
	
	
	

	
	@PostMapping(value="/add")
	public UserResponseDTO addUser(@RequestBody UserDTO u) {
		return service.addUser(u);
	}
	
	@PutMapping(value="/update")
	public UserResponseDTO updateUser(@RequestBody UserDTO u,@PathVariable int uid) {
		return service.updateUser(u,uid);
	}
	
	@GetMapping(value="/getuserbyid/{uid}")
	public UserResponseDTO getUserById(@PathVariable int uid) {
		return service.getUserById(uid);
	}
	
	@GetMapping(value="/getusers")
	public List<UserResponseDTO> getAllUser(){
		return service.getAllUsers();
	}
	
	
	@DeleteMapping(value="/deleteuser/{uid}")
	public String deleteUserById(@PathVariable int uid) {
		String res="";
		service.deleteUserById(uid);
		UserResponseDTO u=service.getUserById(uid);
		if(u==null) {
			res="User Record Deleted Successfully";
		}
		return res;
	}
	
	@GetMapping(value="/getuserbyname/{uname}")
	public UserResponseDTO getUserByName(@PathVariable String uname) {
		return service.getUserByName(uname);
	}

}
