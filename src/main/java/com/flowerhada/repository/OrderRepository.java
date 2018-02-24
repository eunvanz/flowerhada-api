package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	public Page<Order> findByUserId(Long userId, Pageable pageable);
	public List<Order> findByUserId(Long userId);
}
