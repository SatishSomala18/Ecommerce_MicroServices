package com.example.order_microservice.controllers;

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

import com.example.order_microservice.dto.OrderDTO;
import com.example.order_microservice.dto.OrderResponseDTO;
import com.example.order_microservice.service.IOrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderRestController {

	@Autowired
	IOrderService service;

	@PutMapping(value = "/updateorderstatus/{oid}/{status}")
	public OrderResponseDTO updateOrder(@PathVariable boolean status, @PathVariable int oid) {
		return service.updateOrderStatus(oid, status);
	}
	
	@PutMapping(value = "/updateOrder/{oid}")
	public OrderResponseDTO updateOrder(@RequestBody OrderDTO ord, @PathVariable int oid) {
		return service.updateOrder(ord,oid);
	}

	@PostMapping(value = "/addorder")
	public OrderResponseDTO addOrder(@RequestBody OrderDTO ord) {
		return service.addOrder(ord);
	}

	@GetMapping(value = "/getall")
	public List<OrderResponseDTO> getAllOrder() {
		return service.getAllOrders();
	}

	@GetMapping(value = "/getorderbyid/{oid}")
	public OrderResponseDTO getOrderById(@PathVariable int oid) {
		return service.getOrderById(oid);
	}

	@DeleteMapping(value = "/deleteorderbyid/{oid}")
	public String deleteOrderById(@PathVariable int oid) {
		service.deleteById(oid);
		String res = "";
		OrderResponseDTO ord = service.getOrderById(oid);
		if (ord == null) {
			res = "Order Record Deleted Successfully";
		}
		return res;
	}

	@GetMapping(value = "/getorderbystatus/{status}")
	public List<OrderResponseDTO> getOrdersByStatus(@PathVariable boolean status) {
		return service.getOrdersByStatus(status);
	}
	
	@GetMapping(value = "/getorderbyuserid/{uid}")
	public List<OrderResponseDTO> getOrdersByUserId(@PathVariable int uid) {
		return service.getOrdersByUserId(uid);
	}

}
