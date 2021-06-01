package com.india.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.india.ecommerce.entity.Product;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	@Query("select pro from Product pro where pro.productName=:productName or pro.category.name=:category")
	List<Product> findByProductNameContainsOrCategoryContains(String productName,String category);

	}