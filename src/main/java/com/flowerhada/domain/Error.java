package com.flowerhada.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="error")
@ToString
public class Error {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Getter @Setter
	private String type;
	
	@Getter @Setter
	private String log;
	
	@Getter @Setter
	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	@Getter @Setter
	private User user;
	
	@Getter @Setter
	private LocalDateTime date;
	
	@Getter @Setter
	private String status;

}
