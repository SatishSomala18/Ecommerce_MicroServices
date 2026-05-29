package com.example.product_microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.product_microservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	
	public List<Product> findByPriceGreaterThan(double price);
	
	@Query("select p from Product p where p.price between ?1 and ?2")
	public List<Product> getProductsByPriceRange(double d1,double d2);

}
