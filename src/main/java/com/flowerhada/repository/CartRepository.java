package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	public List<Cart> findByUserIdAndType(Long userId, String type);
	public List<Cart> findByUserId(Long userId);
	public List<Cart> findById(Long id);
	public List<Cart> findByOrderId(Long orderId);
	public void deleteByUserIdAndProductIdAndType(Long userId, Long productId, String Type);
	public void deleteByUserIdAndLessonIdAndType(Long userId, Long lessonId, String type);
	public void deleteById(Long id);
}
