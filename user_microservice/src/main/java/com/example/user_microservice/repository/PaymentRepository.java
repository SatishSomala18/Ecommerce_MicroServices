package com.example.user_microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.user_microservice.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{
	
	@Query("select p from Payment p where p.amount > ?1")
	public List<Payment> getPaymentsGT(double amount);
	
	public Payment findByOrderId(int id);

}
