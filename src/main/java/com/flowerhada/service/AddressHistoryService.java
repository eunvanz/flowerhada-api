package com.flowerhada.service;

import java.util.List;

import com.flowerhada.domain.AddressHistory;

public interface AddressHistoryService {
	public List<AddressHistory> readByUserId(Long userId);
	public AddressHistory updateUpdateDate(Long id);
	public void deleteById(Long id);
	public AddressHistory createAddressHistory(AddressHistory addressHistory);
}
