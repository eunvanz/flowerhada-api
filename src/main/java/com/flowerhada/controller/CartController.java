package com.flowerhada.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.Cart;
import com.flowerhada.property.DatePropertyEditor;
import com.flowerhada.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("carts")
public class CartController {

	@Autowired CartService cartService;
	
	@GetMapping("/user/{userId}")
	public List<Cart> readCartByUserId(@PathVariable Long userId) {
		return cartService.readCartByUserId(userId);
	}
	
	@GetMapping("/user/{userId}/{type}")
	public List<Cart> readCartByUserIdAndType(@PathVariable Long userId, @PathVariable String type) {
		return cartService.readCartByUserIdAndType(userId, type);
	}
	
	@GetMapping("/{id}")
	public List<Cart> readCart(@PathVariable Long id) {
		return cartService.findById(id);
	}
	
	@PostMapping()
	public Cart createCart(@RequestBody Cart cart) {
		return cartService.createCart(cart);
	}
	
	@PutMapping("/{id}")
	public Cart updateCart(@RequestBody Cart cart, @PathVariable Long id) {
		cart.setId(id);
		return cartService.updateCart(cart);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCart(@PathVariable Long id) {
		cartService.deleteById(id);
	}
	
	@DeleteMapping("/user/{userId}/{itemType}/{itemId}/{cartType}")
	public void deleteCartByUserIdAndItemId(@PathVariable Long userId, @PathVariable String itemType, @PathVariable Long itemId, @PathVariable String cartType) {
		if (itemType.equals("lesson")) cartService.deleteCartByUserIdAndLessonIdAndType(userId, itemId, cartType);
		else cartService.deleteCartByUserIdAndProductIdAndType(userId, itemId, cartType);
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DatePropertyEditor());
	}
	
}
