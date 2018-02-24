package com.flowerhada.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name = "\"order\"")
@ToString
public class Order {

	@Id
	@GeneratedValue
	@Getter
	@Setter
	private Long id;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "order_id", referencedColumnName="id")
	@Getter
	@Setter
	private List<Cart> carts;

	public void addCart(Cart cart) {
		if (this.carts == null) {
			carts = new ArrayList<Cart>();
		}
		carts.add(cart);
	}

	@Getter
	@Setter
	private String uid;

	@Getter
	@Setter
	private String paymentMethod;

	@Getter
	@Setter
	private int pointSpent;

	@Getter
	@Setter
	private int totalAmount;

	@Getter
	@Setter
	@Column(name = "user_id")
	private Long userId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	@Getter
	@Setter
	private User user;

	@Getter
	@Setter
	private String postCode;

	@Getter
	@Setter
	private String address;

	@Getter
	@Setter
	private String restAddress;

	@Getter
	@Setter
	private String receiver;

	@Getter
	@Setter
	private String studentNames;

	@Getter
	@Setter
	private String status;

	@Getter
	@Setter
	private String receiverPhoneNumber;

	@Getter
	@Setter
	private String studentPhoneNumbers;

	@Getter
	@Setter
	private LocalDateTime updateDate;

	@Getter
	@Setter
	private String sender;

	@Getter
	@Setter
	private String letterMessage;

	@Getter
	@Setter
	private String transportMessage;

	@Getter
	@Setter
	private String applyNum;

	@Getter
	@Setter
	private String vbankNum;

	@Getter
	@Setter
	private String vbankName;

	@Getter
	@Setter
	private String vbankHolder;

	@Getter
	@Setter
	private Long vbankDate;

	@Getter
	@Setter
	private String transportCode;

}
