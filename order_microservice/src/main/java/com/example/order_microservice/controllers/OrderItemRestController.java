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

import com.example.order_microservice.dto.OrderItemDTO;
import com.example.order_microservice.dto.OrderItemResponseDTO;
import com.example.order_microservice.service.IOrderItemService;

@RestController
@RequestMapping(value = "/orderitem")
public class OrderItemRestController {

	@Autowired
	IOrderItemService service;

	@PostMapping(value = "/addorderitem")
	public OrderItemResponseDTO addOrderItem(@RequestBody OrderItemDTO dtoitem) {
		return service.addOrderItem(dtoitem);
	}

	@PutMapping(value = "/updateOrderItem/{itemid}")
	public OrderItemResponseDTO updateOrderItem(@RequestBody OrderItemDTO dtoitem,@PathVariable int itemid) {
		return service.updateOrderItem(dtoitem,itemid);
	}

	@GetMapping(value = "/getorderitem/{oid}")
	public OrderItemResponseDTO getOrderItemById(@PathVariable int oid) {
		return service.getOrderItemById(oid);
	}

	@DeleteMapping(value = "/deleteorderitembyid/{oid}")
	public String deleteOrderItemById(@PathVariable int oid) {
		service.deleteOrderItemById(oid);
		return "OrderItem Record Deleted Successfully";
	}

	@GetMapping(value = "/getallorderitems")
	public List<OrderItemResponseDTO> getAllOrderItems() {
		return service.getOrderItems();
	}

	@GetMapping(value = "/getorderitembyquantityrange/{q1}/{q2}")
	public List<OrderItemResponseDTO> getOrderItemsbyQuantityRange(@PathVariable int q1, @PathVariable int q2) {
		return service.getOrderItemsByQuantityRange(q1, q2);
	}

	@GetMapping(value = "/getorderitemsbyorderid/{oid}")
	public List<OrderItemResponseDTO> getOrderItemByOrderId(@PathVariable int oid) {
		return service.getOrderItemByOrderId(oid);
	}

}
