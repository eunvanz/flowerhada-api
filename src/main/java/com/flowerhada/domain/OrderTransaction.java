package com.flowerhada.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class OrderTransaction {
	@Getter @Setter Long userId;
	@Getter @Setter Order order;
	@Getter @Setter List<Cart> carts;
	@Getter @Setter AddressHistory addressHistory;
	@Getter @Setter PointHistory spentPointHistory;
	@Getter @Setter PointHistory earnedPointHistory;
	@Getter @Setter String cartUpdateType;
	@Getter @Setter String addressUpdateType;
}
