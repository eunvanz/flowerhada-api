package com.flowerhada.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.AddressHistory;
import com.flowerhada.domain.Cart;
import com.flowerhada.domain.Order;
import com.flowerhada.domain.OrderTransaction;
import com.flowerhada.domain.PointHistory;
import com.flowerhada.domain.User;
import com.flowerhada.repository.AddressHistoryRepository;
import com.flowerhada.repository.CartRepository;
import com.flowerhada.repository.OrderRepository;
import com.flowerhada.repository.PointHistoryRepository;
import com.flowerhada.repository.UserRepository;
import com.flowerhada.service.OrderService;
import com.flowerhada.service.PointHistoryService;
import com.flowerhada.service.UserService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	@Autowired OrderRepository orderRepository;
	@Autowired CartRepository cartRepository;
	@Autowired AddressHistoryRepository addressHistoryRepository;
	@Autowired PointHistoryRepository pointHistoryRepository;
	@Autowired UserRepository userRepository;
	@Autowired PointHistoryService pointHistoryService;
	
	@Override
	public Order createOrder(Order order) {
		order.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return orderRepository.save(order);
	}

	@Override
	public Order readOrder(Long id) {
		return orderRepository.findOne(id);
	}

	@Override
	public Page<Order> readOrderByUserId(Long userId, Pageable pageable) {
		Page<Order> orders = orderRepository.findByUserId(userId, pageable);
		List<Cart> carts = cartRepository.findByUserId(userId);
		for (Order order : orders) {
			Long orderId = order.getId();
			for (Cart cart : carts) {
				if (cart.getOrderId() == orderId) {
					order.addCart(cart);
				}
			}
		}
		return orders;
	}

	@Override
	public Order updateOrder(Order order) {
		Order oldOrder = orderRepository.findOne(order.getId());
		oldOrder.setAddress(order.getAddress());
		oldOrder.setPaymentMethod(order.getPaymentMethod());
		oldOrder.setPointSpent(order.getPointSpent());
		oldOrder.setPostCode(order.getPostCode());
		oldOrder.setReceiver(order.getReceiver());
		oldOrder.setRestAddress(order.getRestAddress());
		oldOrder.setStatus(order.getStatus());
		oldOrder.setStudentNames(order.getStudentNames());
		oldOrder.setTotalAmount(order.getTotalAmount());
		oldOrder.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		oldOrder.setUserId(order.getUserId());
		oldOrder.setReceiverPhoneNumber(order.getReceiverPhoneNumber());
		oldOrder.setStudentPhoneNumbers(order.getStudentPhoneNumbers());
		oldOrder.setSender(order.getSender());
		oldOrder.setLetterMessage(order.getLetterMessage());
		oldOrder.setTransportMessage(order.getTransportMessage());
		oldOrder.setTransportCode(order.getTransportCode());
		return oldOrder;
	}

	@Override
	public Order createOrderTransaction(OrderTransaction orderTransaction) throws Exception {
		
		Order order = orderTransaction.getOrder();
		Long userId = orderTransaction.getUserId();
		
		Order createdOrder = createOrder(order); // create order
		
		/* pointHistory */
		PointHistory spentPointHistory = orderTransaction.getSpentPointHistory();
		if (spentPointHistory != null)	{
			User oldUser = userRepository.findOne(userId);
			int updatedPoint = oldUser.getPoint() + spentPointHistory.getAmount();
			if (updatedPoint < 0) {
				throw new Exception("포인트가 부족합니다.");
			}
			oldUser.setPoint(oldUser.getPoint() + spentPointHistory.getAmount());
			spentPointHistory.setDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
			pointHistoryRepository.save(spentPointHistory);
		}
		
		/* cart */
		String cartUpdateType = orderTransaction.getCartUpdateType();
		List<Cart> carts = orderTransaction.getCarts();
		for (Cart cart : carts) {
			cart.setOrderId(createdOrder.getId());
			if (cartUpdateType.equals("update")) {
				// 장바구니의 type과 status 변경
				Cart oldCart = cartRepository.findById(cart.getId()).get(0);
				oldCart.setOrderId(cart.getOrderId());
				oldCart.setQuantity(cart.getQuantity());
				oldCart.setStatus(cart.getStatus());
				oldCart.setTotalAmount(cart.getTotalAmount());
				oldCart.setTransDate(LocalDateTime.now());
				oldCart.setType(cart.getType());
			} else {
				// crate cart
				cartRepository.save(cart);
			}
		}
		
		/* addressHistory */
		AddressHistory addressHistory = orderTransaction.getAddressHistory();
		if (addressHistory != null) {
			String addressUpdateType = orderTransaction.getAddressUpdateType();
			if (addressUpdateType.equals("update")) {
				AddressHistory oldAddressHistory = addressHistoryRepository.findOne(addressHistory.getId());
				oldAddressHistory.setUpdateDate(LocalDateTime.now());
			} else {
				addressHistory.setUpdateDate(LocalDateTime.now());
				addressHistoryRepository.save(addressHistory);
				List<AddressHistory> userAddressHistories = addressHistoryRepository.findByUserIdOrderByUpdateDateDesc(userId);
				if (userAddressHistories.size() > 5) { // maximum recent address size is 5
					addressHistoryRepository.delete(userAddressHistories.get(5).getId());
				}
			}
		}
		
		return createdOrder;
	}

	@Override
	public List<Order> readOrderByUserIdNotPageable(Long userId) {
		List<Order> orders = orderRepository.findByUserId(userId);
		List<Cart> carts = cartRepository.findByUserId(userId);
		for (Order order : orders) {
			Long orderId = order.getId();
			for (Cart cart : carts) {
				if (cart.getOrderId() == orderId) {
					order.addCart(cart);
				}
			}
		}
		return orders;
	}
	
	@Override
	public Page<Order> readAllOrder(Pageable pageable) {
		Page<Order> orders = orderRepository.findAll(pageable);
		for (Order order : orders) {
			Long orderId = order.getId();
			List<Cart> carts = cartRepository.findByOrderId(orderId);
			System.out.println("carts: " + carts + ", orderId: " + orderId);
			for (Cart cart : carts) {
				order.addCart(cart);
			}
		}
		return orders;
	}

}
