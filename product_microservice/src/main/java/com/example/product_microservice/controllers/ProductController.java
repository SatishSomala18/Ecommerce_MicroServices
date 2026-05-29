package com.example.product_microservice.controllers;

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

import com.example.product_microservice.dto.ProductDTO;
import com.example.product_microservice.entity.Product;
import com.example.product_microservice.service.IProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

	@Autowired
	IProductService service;

	@PostMapping(value = "/add")
	public Product addProduct(@RequestBody ProductDTO p) {
		return service.addProduct(p);
	}

	@PutMapping(value = "/update")
	public Product updateProduct(@RequestBody Product p) {
		return service.updateProduct(p);
	}

	@GetMapping(value = "/getbyid/{pid}")
	public Product getProductById(@PathVariable int pid) {
		return service.getProductById(pid);
	}

	@GetMapping(value = "/getall")
	public List<Product> getAllProdcuts() {
		return service.getAllProducts();
	}

	@DeleteMapping(value = "/deletebyid/{pid}")
	public String deleteProductById(@PathVariable int pid) {
		service.deleteById(pid);
		String res = "";
		Product p = service.getProductById(pid);
		if (p == null) {
			res = "Product Record Deleted Successfully";
		}
		return res;
	}

	@GetMapping(value = "/getbypricegt/{price}")
	public List<Product> getProductsPriceGT(@PathVariable double price) {
		return service.getAllProdcutsByPriceGT(price);
	}

	@GetMapping(value = "/getproductsbyrange/{p1}/{p2}")
	public List<Product> getProductsByRange(@PathVariable double p1, @PathVariable double p2) {
		return service.getProductsByPriceRange(p1, p2);
	}

}
