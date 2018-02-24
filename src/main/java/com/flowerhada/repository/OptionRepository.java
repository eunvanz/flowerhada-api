package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.ProductOption;

public interface OptionRepository extends JpaRepository<ProductOption, Long> {

	public List<ProductOption> findByProductId(Long productId);
	
}
