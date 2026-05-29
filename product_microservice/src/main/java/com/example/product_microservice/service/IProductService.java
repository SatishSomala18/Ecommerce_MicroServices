package com.example.product_microservice.service;

import java.util.List;

import com.example.product_microservice.dto.ProductDTO;
import com.example.product_microservice.entity.Product;


public interface IProductService {
	
	
	
	public Product addProduct(ProductDTO p);
	
	public Product updateProduct(Product p);
	
	public Product getProductById(int id);
	
	public void deleteById(int id);
	
	public List<Product> getAllProducts();
	
	public List<Product> getAllProdcutsByPriceGT(double price);
	
	public List<Product> getProductsByPriceRange(double p1,double p2);
}
