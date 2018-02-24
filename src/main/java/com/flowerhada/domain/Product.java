package com.flowerhada.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@Entity
@Table(name="product")
@ToString
public class Product {

	@Id
	@GeneratedValue
	@Getter @Setter
	private Long id;
	
	@NotNull
	@Getter @Setter
	private String title;
	
	@NotNull
	@Getter @Setter
	private String detail;
	
	@NotNull
	@Getter @Setter
	private String mainCategory;
	
	@Getter @Setter
	private String subCategory;
	
	@NotNull
	@Getter @Setter
	private String content;
	
	@NotNull
	@Getter @Setter
	private String titleImg;
	
	@Getter @Setter
	private String images;
	
	@Getter @Setter
	private int price;
	
	@Getter @Setter
	private int discountedPrice;
	
	@Getter @Setter
	private String groupName;
	
	@Getter @Setter
	private String relationName;
	
	@Getter @Setter
	private boolean soldout;
	
	@Getter @Setter
	private boolean activated;
	
	@Getter @Setter
	private String deliveryType;
	
	@Getter @Setter
	private LocalDateTime regDateTime;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE, orphanRemoval = true)
	@JoinColumn(name="product_id")
	private List<ProductOption> options;
	
	public void addOption(ProductOption option) {
		if (this.options == null) {
			options = new ArrayList<ProductOption>();
		}
		options.add(option);
	}
	
}
