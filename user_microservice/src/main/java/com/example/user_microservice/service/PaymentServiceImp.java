package com.example.user_microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.user_microservice.dto.OrderResponseDTO;
import com.example.user_microservice.dto.PaymentDTO;
import com.example.user_microservice.dto.PaymentResponseDTO;
import com.example.user_microservice.entity.Payment;
import com.example.user_microservice.entity.User;
import com.example.user_microservice.exceptions.PaymentNotFoundException;
import com.example.user_microservice.exceptions.UserNotFoundException;
import com.example.user_microservice.repository.PaymentRepository;
import com.example.user_microservice.repository.UserRepository;

@Service
public class PaymentServiceImp implements IPaymentService {

	@Autowired
	PaymentRepository repo;

	@Autowired
	UserRepository userrepository;
	@Autowired
	RestTemplate rt;

	@Override
	public PaymentResponseDTO addPayment(PaymentDTO p) {

		Payment payment = convertToPayment(p);

		String url = "http://localhost:8080/orders/getorderbyid/" + payment.getOrderId();

		OrderResponseDTO order = rt.getForObject(url, OrderResponseDTO.class);

		User user = userrepository.findById(order.getUserId()).orElse(null);
		
		if(user==null) throw new UserNotFoundException("User Not Found With ID :"+order.getUserId());

		payment.setUser(user);

		payment.setAmount(order.getTotalAmount());

		return convertToDTO(repo.save(payment));
	}

	@Override
	public PaymentResponseDTO updatePayment(PaymentDTO p, int pid) {
		Payment payment = convertToPayment(p);
		
		PaymentResponseDTO pment=getPaymentById(pid);
		payment.setId(pid);
		String url = "http://localhost:8080/orders/getorderbyid/" + payment.getOrderId();

		OrderResponseDTO order = rt.getForObject(url, OrderResponseDTO.class);

		User user = userrepository.findById(order.getUserId()).orElse(null);
		
		if(user==null) throw new UserNotFoundException("User Not Found With ID :"+order.getUserId());
		
		

		payment.setUser(user);

		payment.setAmount(order.getTotalAmount());

		

		return convertToDTO(repo.save(payment));
	}

	@Override
	public PaymentResponseDTO getPaymentById(int id) {
		Payment pay=repo.findById(id).orElse(null);
		if(pay==null) throw new PaymentNotFoundException("Payment Not Found With  ID :"+id);
		return convertToDTO(pay);
	}

	@Override
	public void deletePaymentById(int id) {
		repo.deleteById(id);
	}

	@Override
	public List<PaymentResponseDTO> getAllPayments() {
		List<Payment> payments = repo.findAll();
		List<PaymentResponseDTO> list = new ArrayList<>();
		for (Payment p : payments) {
			list.add(convertToDTO(p));
		}
		return list;
	}

	@Override
	public List<PaymentResponseDTO> getPaymentGT(double amount) {
		List<Payment> payments = repo.getPaymentsGT(amount);
		List<PaymentResponseDTO> list = new ArrayList<>();
		for (Payment p : payments) {
			list.add(convertToDTO(p));
		}
		return list;
	}

	@Override
	public PaymentResponseDTO getPaymentByOrderId(int id) {
		
		Payment pay=repo.findByOrderId(id);
		if(pay==null) throw new PaymentNotFoundException("Payment Not Found With Order ID :"+id);
		return convertToDTO(pay);
	}

	public PaymentResponseDTO convertToDTO(Payment p) {
		PaymentResponseDTO pay = new PaymentResponseDTO();
		pay.setId(p.getId());
		pay.setOrderId(p.getOrderId());
		pay.setAmount(p.getAmount());
		pay.setPaymentMethod(p.getPaymentMethod());
		pay.setPaymentStatus(p.isPaymentStatus());
		User user = p.getUser();
		pay.setUserId(user.getId());
		return pay;

	}

	public Payment convertToPayment(PaymentDTO pay) {
		Payment payment = new Payment();

		payment.setOrderId(pay.getOrderId());
		payment.setPaymentMethod(pay.getPaymentMethod());
		payment.setPaymentStatus(pay.isPaymentStatus());

		return payment;

	}

}
