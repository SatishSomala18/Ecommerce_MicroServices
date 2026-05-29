package com.example.user_microservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.user_microservice.entity.User;


@Repository
public interface UserRepository  extends JpaRepository<User,Integer>{
	
	public User findByName(String name);

}
