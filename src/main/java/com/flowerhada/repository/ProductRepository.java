package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByMainCategoryAndActivatedOrderByIdDesc(String mainCategory, boolean activated);
	List<Product> findBySubCategoryAndActivatedOrderByIdDesc(String subCategory, boolean activated);
	List<Product> findByRelationNameAndActivatedOrderByIdDesc(String relationName, boolean activated);
	List<Product> findByActivatedOrderByIdDesc(boolean activated);
	List<Product> findAllByOrderByIdDesc();
}
