package com.flowerhada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.AddressHistory;
import com.flowerhada.service.AddressHistoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("address-histories")
public class AddressHistoryController {
	
	@Autowired AddressHistoryService addressHistoryService;
	
	@GetMapping("/user/{userId}")
	public List<AddressHistory> getAddressHistoriesByUserId(@PathVariable Long userId) {
		return addressHistoryService.readByUserId(userId);
	}
	
	@PostMapping()
	public AddressHistory postAddressHistory(@RequestBody AddressHistory addressHistory) {
		return addressHistoryService.createAddressHistory(addressHistory);
	}
	
	@PutMapping("/refresh/{id}")
	public AddressHistory putAddressHistory(@PathVariable Long id) {
		return addressHistoryService.updateUpdateDate(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteAddressHistory(@PathVariable Long id) {
		addressHistoryService.deleteById(id);
	}
}
