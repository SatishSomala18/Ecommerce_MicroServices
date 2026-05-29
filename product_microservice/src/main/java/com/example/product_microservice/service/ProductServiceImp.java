package com.example.product_microservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.product_microservice.dto.ProductDTO;
import com.example.product_microservice.entity.Product;
import com.example.product_microservice.repository.ProductRepository;

@Service
public class ProductServiceImp implements IProductService {

	@Autowired
	ProductRepository repo;

	@Override
	public Product addProduct(ProductDTO p) {

		return repo.save(convertToEntity(p));

	}

	@Override
	public Product updateProduct(Product p) {

		return repo.save(p);

	}

	@Override
	public Product getProductById(int id) {
		return repo.findById(id).orElse(null);

	}

	@Override
	public void deleteById(int id) {
		repo.deleteById(id);

	}

	@Override
	public List<Product> getAllProducts() {
		return repo.findAll();

	}

	@Override
	public List<Product> getAllProdcutsByPriceGT(double price) {
		return repo.findByPriceGreaterThan(price);

	}

	@Override
	public List<Product> getProductsByPriceRange(double p1, double p2) {
		return repo.getProductsByPriceRange(p1, p2);

	}

	public Product convertToEntity(ProductDTO p) {
		Product pr = new Product();
		pr.setCategory(p.getCategory());
		pr.setName(p.getName());
		pr.setPrice(p.getPrice());
		pr.setStock(p.getStock());

		return pr;
	}

	public ProductDTO convertToDTO(Product p) {
		ProductDTO pd = new ProductDTO();
		pd.setCategory(p.getCategory());
		pd.setName(p.getName());
		pd.setPrice(p.getPrice());
		pd.setStock(p.getStock());

		return pd;
	}

}
