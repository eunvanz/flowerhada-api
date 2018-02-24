package com.flowerhada.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Board {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String category;
	
	@NotNull
	@Column(name="user_id")
	private Long userId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="user_id", insertable = false, updatable = false)
	private User user;
	
	private LocalDateTime regDate;
	
	private LocalDateTime updateDate;
	
	private String title;
	
	private String content;
	
	private int view;
	
}
