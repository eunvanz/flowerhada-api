package com.flowerhada.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="address_history")
@ToString
public class AddressHistory {

	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private Long userId;
	
	@Getter @Setter
	private String postCode;
	
	@Getter @Setter
	private String address;
	
	@Getter @Setter
	private String restAddress;
	
	@Getter @Setter
	private LocalDateTime updateDate;
	
}
