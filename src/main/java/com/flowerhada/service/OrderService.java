package com.flowerhada.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flowerhada.domain.Order;
import com.flowerhada.domain.OrderTransaction;

public interface OrderService {
	public Order createOrder(Order order);
	public Order readOrder(Long id);
	public Page<Order> readOrderByUserId(Long userId, Pageable pageable);
	public Order updateOrder(Order order);
	public Order createOrderTransaction(OrderTransaction orderTransaction) throws Exception;
	public List<Order> readOrderByUserIdNotPageable(Long userId);
	public Page<Order> readAllOrder(Pageable pageable);
}
