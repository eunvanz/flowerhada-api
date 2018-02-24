package com.flowerhada.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.Cart;
import com.flowerhada.repository.CartRepository;
import com.flowerhada.service.CartService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	@Autowired CartRepository cartRepository;
	
	@Override
	public Cart createCart(Cart cart) {
		cart.setTransDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return cartRepository.save(cart);
	}

	@Override
	public List<Cart> readCartByUserIdAndType(Long userId, String type) {
		return cartRepository.findByUserIdAndType(userId, type);
	}

	@Override
	public List<Cart> readCartByUserId(Long userId) {
		return cartRepository.findByUserId(userId);
	}

	@Override
	public Cart updateCart(Cart cart) {
		Cart oldCart = cartRepository.findById(cart.getId()).get(0);
		oldCart.setItemPrice(cart.getItemPrice());
		oldCart.setLessonId(cart.getLessonId());
		oldCart.setOptions(cart.getOptions());
		oldCart.setOrderId(cart.getOrderId());
		oldCart.setProductId(cart.getProductId());
		oldCart.setQuantity(cart.getQuantity());
		oldCart.setReceiveArea(cart.getReceiveArea());
		oldCart.setReceiveDate(cart.getReceiveDate());
		oldCart.setReceiveTime(cart.getReceiveTime());
		oldCart.setStatus(cart.getStatus());
		oldCart.setTotalAmount(cart.getTotalAmount());
		oldCart.setTransDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		oldCart.setType(cart.getType());
		oldCart.setUserId(cart.getUserId());
		return oldCart;
	}

	@Override
	public void deleteCart(Long id) {
		cartRepository.delete(id);
	}

	@Override
	public Cart readCart(Long id) {
		return cartRepository.findOne(id);
	}

	@Override
	public List<Cart> findById(Long id) {
		return cartRepository.findById(id);
	}

	@Override
	public void deleteCartByUserIdAndLessonIdAndType(Long userId, Long lessonId, String type) {
		cartRepository.deleteByUserIdAndLessonIdAndType(userId, lessonId, type);
	}

	@Override
	public void deleteCartByUserIdAndProductIdAndType(Long userId, Long productId, String type) {
		cartRepository.deleteByUserIdAndProductIdAndType(userId, productId, type);
	}

	@Override
	public void deleteById(Long id) {
		cartRepository.deleteById(id);
	}

}
