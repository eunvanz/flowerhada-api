package com.flowerhada.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ProductOption {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="product_id")
	private Long productId;
	
	private String category;
	
	private String name;
	
	private int addPrice;
	
}
