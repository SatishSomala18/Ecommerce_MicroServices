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

import com.example.user_microservice.dto.PaymentDTO;
import com.example.user_microservice.dto.PaymentResponseDTO;
import com.example.user_microservice.service.IPaymentService;



@RestController
@RequestMapping(value="/payment")
public class PaymentController {
	
	
	@Autowired
	IPaymentService service;
	@PostMapping(value="/addpayment")
	public PaymentResponseDTO addPayment(@RequestBody PaymentDTO p) {
		return  service.addPayment(p);
	}
	
	@PutMapping(value="/updatepayment/{pid}")
	public PaymentResponseDTO updatePayment(@RequestBody PaymentDTO p,@PathVariable int pid) {
		return service.updatePayment(p,pid);
	}
	
	@GetMapping(value="/getpaymentbyid/{pid}")
	public PaymentResponseDTO getPaymentById(@PathVariable int pid) {
		return service.getPaymentById(pid);
	}
	
	@GetMapping(value="/getallpayments")
	public List<PaymentResponseDTO> getAllPaymens(){
		return service.getAllPayments();
	}
	
	@GetMapping(value="/getpaymentsgt/{amount}")
	public List<PaymentResponseDTO> getPaymentsGt(@PathVariable double amount){
		return service.getPaymentGT(amount);
	}
	
	@GetMapping(value="/getpaymentbyorderid/{oid}")
	public PaymentResponseDTO getPaymentGt(@PathVariable int oid) {
		return service.getPaymentByOrderId(oid);
	}
	
	@DeleteMapping(value="/deletepaymentbyid/{pid}")
	public String deletePaymentById(@PathVariable int pid) {
		PaymentResponseDTO pay=service.getPaymentById(pid);
		service.deletePaymentById(pid);
		return "Payment Record Deleted Successfully";
	}
	
	

}
