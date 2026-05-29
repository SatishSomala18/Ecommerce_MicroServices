package com.example.user_microservice.service;

import java.util.List;

import com.example.user_microservice.dto.PaymentDTO;
import com.example.user_microservice.dto.PaymentResponseDTO;
import com.example.user_microservice.entity.Payment;




public interface IPaymentService {

	
	public PaymentResponseDTO addPayment(PaymentDTO p);
	
	public PaymentResponseDTO updatePayment(PaymentDTO p,int pid);
	
	public PaymentResponseDTO getPaymentById(int id);
	
	public void  deletePaymentById(int id);
	
	public List<PaymentResponseDTO> getAllPayments();
	
	public List<PaymentResponseDTO> getPaymentGT(double amount);
	
	public PaymentResponseDTO getPaymentByOrderId(int id);
}
