package com.flowerhada.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Tutor {

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String image;
	
	private String introduce;
	
}
