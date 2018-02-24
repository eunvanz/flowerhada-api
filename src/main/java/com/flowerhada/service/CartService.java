package com.flowerhada.service;

import java.util.List;

import com.flowerhada.domain.Cart;

public interface CartService {
	public Cart createCart(Cart cart);
	public List<Cart> readCartByUserIdAndType(Long userId, String type);
	public List<Cart> readCartByUserId(Long userId);
	public Cart updateCart(Cart cart);
	public void deleteCart(Long id);
	public Cart readCart(Long id);
	public List<Cart> findById(Long id);
	public void deleteCartByUserIdAndLessonIdAndType(Long userId, Long lessonId, String type);
	public void deleteCartByUserIdAndProductIdAndType(Long userId, Long productId, String type);
	public void deleteById(Long id);
}
