package com.example.user_microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.user_microservice.dto.UserDTO;
import com.example.user_microservice.dto.UserResponseDTO;
import com.example.user_microservice.entity.User;
import com.example.user_microservice.exceptions.UserNotFoundException;
import com.example.user_microservice.exceptions.WrongPasswordException;
import com.example.user_microservice.repository.UserRepository;


@Service
public class UserServiceImp implements IUserService{
	
	@Autowired
	UserRepository repo;

	@Override
	public UserResponseDTO addUser(UserDTO u) {
		User user=convertToUser(u);
		
		return convertToDTO(repo.save(user));
	}

	@Override
	public UserResponseDTO updateUser(UserDTO u,int uid){
		User user=convertToUser(u);
		user.setId(uid);
		User user2=repo.findById(uid).orElse(null);
		if(user2==null) throw new UserNotFoundException("User Not Found With ID :"+uid);
		if(user2.getPassword().equals(user.getPassword())) {
			return convertToDTO(repo.save(user));
		}
		
		throw new WrongPasswordException("Password is Wrong...!!!!  Please Enter Valid Password");
		
	}

	@Override
	public UserResponseDTO getUserById(int id) {
		User u=repo.findById(id).orElse(null);
		if(u==null) throw new UserNotFoundException("User Not Found With ID :"+id);
		return convertToDTO(u);
	}

	@Override
	public void deleteUserById(int id) {
		repo.deleteById(id);
		
	}

	@Override
	public List<UserResponseDTO> getAllUsers() {
		List<User> users= repo.findAll();
		List<UserResponseDTO> list=new ArrayList<>();
		for(User u:users) {
			list.add(convertToDTO(u));
		}
		return list;
		
	}

	@Override
	public UserResponseDTO	  getUserByName(String name) {
		
		User user=repo.findByName(name);
		if(user==null) throw new UserNotFoundException("User Not Found With Name :"+name);
		return convertToDTO(user);
		
	}
	
	public UserResponseDTO convertToDTO(User u) {
		UserResponseDTO ur=new UserResponseDTO();
		ur.setId(u.getId());
		ur.setName(u.getName());
		ur.setEmail(u.getEmail());
		ur.setAddress(u.getAddress());
		
		return ur;
	}
	
	public User convertToUser(UserDTO u) {
		User user=new User();
		user.setName(u.getName());
		user.setPassword(u.getPassword());
		user.setEmail(u.getEmail());
		user.setAddress(u.getAddress());
		
		return user;
		
	}
	


}
