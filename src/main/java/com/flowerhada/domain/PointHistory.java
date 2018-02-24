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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="point_history")
@ToString
public class PointHistory {
	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@Column(name="user_id")
	@Getter @Setter
	private Long userId;
	
	@Getter @Setter
	private int amount;
	
	@Getter @Setter
	private String action;
	
	@Getter @Setter
	private LocalDateTime date;
}
