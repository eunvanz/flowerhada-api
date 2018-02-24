package com.flowerhada.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.Order;
import com.flowerhada.domain.OrderTransaction;
import com.flowerhada.domain.PointHistory;
import com.flowerhada.service.OrderService;
import com.flowerhada.service.PointHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
	
	@Autowired OrderService orderService;
	@Autowired PointHistoryService pointHistoryService;
	
	@GetMapping("/{id}")
	public Order getOrderById(@PathVariable Long id) {
		return orderService.readOrder(id);
	}

	@GetMapping("/user/not-pageable/{userId}")
	public List<Order> getOrdersByUserId(@PathVariable Long userId) {
		return orderService.readOrderByUserIdNotPageable(userId);
	}
	
	@GetMapping("/user/{userId}")
	public Page<Order> getOrdersByUserId(@PathVariable Long userId, @RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return orderService.readOrderByUserId(userId, pageRequest);
	}
	
	@GetMapping()
	public Page<Order> getOrders(@RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return orderService.readAllOrder(pageRequest);
	}
	
	@PostMapping()
	public Order postOrder(@RequestBody Order order) {
		return orderService.createOrder(order);
	}
	
	@PutMapping("/{id}")
	public Order putOrderById(@RequestBody Order order, @PathVariable Long id) {
		order.setId(id);
		return orderService.updateOrder(order);
	}
	
	@PostMapping("/transaction")
	public Order postOrderTransaction(@RequestBody OrderTransaction orderTransaction) throws Exception {
		return orderService.createOrderTransaction(orderTransaction);
	}
	
	@PostMapping("/cancel")
	public JSONObject cancelOrder(@RequestBody Order order) throws Exception {
		
		String key = "6649166650148853";
		String secret = "rjFQ9gKNUeI5TpKquWRiPsGTumjKL2Adlf3vvOjq32vWrgnVdee2GUVbheV2gwgCLj8h8rIbtd8UtwQH";
		
		String url = "https://api.iamport.kr/users/getToken";
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);

		// add header
		post.setHeader("User-Agent", "Mozilla/5.0");

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("imp_key", key));
		urlParameters.add(new BasicNameValuePair("imp_secret", secret));
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		HttpResponse response = client.execute(post);
		
		String json = EntityUtils.toString(response.getEntity());
		
		JSONParser parser = new JSONParser();
		
		JSONObject jo = (JSONObject) parser.parse(json);
		JSONObject res = (JSONObject) jo.get("response");
		String token = (String) res.get("access_token");
		
		url = "https://api.iamport.kr/payments/cancel";
		post = new HttpPost(url);
		post.setHeader("Authorization", token);
		post.setHeader("User-Agent", "Mozilla/5.0");
		urlParameters = new ArrayList<NameValuePair>();
		
		urlParameters.add(new BasicNameValuePair("imp_uid", order.getUid()));
		
		if (order.getVbankHolder() != null) {
			String vbankName = order.getVbankName();
			String vbankCode = null;
			if ("기업은행".equals(vbankName))  vbankCode = "03";
			else if ("외환은행".equals(vbankName)) vbankCode = "05";
			else if ("농협중앙회".equals(vbankName)) vbankCode = "11";
			// TODO
			urlParameters.add(new BasicNameValuePair("refund_holder", order.getVbankHolder()));
			urlParameters.add(new BasicNameValuePair("refund_bank", vbankCode));
			urlParameters.add(new BasicNameValuePair("refund_account", order.getVbankNum()));
		}
		
		post.setEntity(new UrlEncodedFormEntity(urlParameters));
		
		response = client.execute(post);
		String resString = EntityUtils.toString(response.getEntity());
		
		if (order.getPointSpent() > 0) {
			PointHistory pointHistory = new PointHistory();
			pointHistory.setAction("결제취소");
			pointHistory.setAmount(order.getPointSpent());
			pointHistory.setUserId(order.getUserId());
			pointHistoryService.createPointHistory(pointHistory);
		}
		
		return (JSONObject) parser.parse(resString);
	}
	
}
