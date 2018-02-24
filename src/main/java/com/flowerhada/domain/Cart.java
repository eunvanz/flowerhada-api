package com.flowerhada.domain;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="cart")
@ToString
public class Cart {
	
	@Id
	@GeneratedValue
	@Getter @Setter
	@Column(name="id")
	private Long id;
	
	@NotNull
	@Getter @Setter
	private Long userId;
	
	@Getter @Setter
	@Column(name="lesson_id")
	private Long lessonId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="lesson_id", insertable = false, updatable = false)
	@Getter @Setter
	private Lesson lesson;
	
	@Getter @Setter
	@Column(name="product_id")
	private Long productId;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name="product_id", insertable = false, updatable = false)
	@Getter @Setter
	private Product product;
	
	@Getter @Setter
	private int quantity;
	
	@NotNull
	@Getter @Setter
	private String type;
	
	@Getter @Setter
	private String status;
	
	@Getter @Setter
	private String options;
	
	@Getter @Setter
	private int totalAmount;
	
	@Getter @Setter
	private int itemPrice;
	
	@Getter @Setter
	private LocalDateTime transDate;
	
	@Getter @Setter
	private Date receiveDate;
	
	@Getter @Setter
	private String receiveTime;
	
	@Getter @Setter
	private String receiveArea;
	
//	@ManyToOne
//	@JoinColumn(name = "id")
//	@Getter @Setter
	@Getter @Setter
	@Column(name = "order_id")
	private Long orderId;
	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "order_id")
//	@ManyToOne
//	@JoinColumn(name = "order_id")
//	@Getter @Setter
//	private Order order;
	
}
