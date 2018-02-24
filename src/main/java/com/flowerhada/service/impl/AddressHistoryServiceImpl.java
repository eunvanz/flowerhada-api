package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.AddressHistory;
import com.flowerhada.repository.AddressHistoryRepository;
import com.flowerhada.service.AddressHistoryService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressHistoryServiceImpl implements AddressHistoryService {

	@Autowired AddressHistoryRepository addressHistoryRepository;
	
	@Override
	public List<AddressHistory> readByUserId(Long userId) {
		return addressHistoryRepository.findByUserIdOrderByUpdateDateDesc(userId);
	}

	@Override
	public AddressHistory updateUpdateDate(Long id) {
		AddressHistory oldAddressHistory = addressHistoryRepository.findOne(id);
		oldAddressHistory.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return oldAddressHistory;
	}

	@Override
	public void deleteById(Long id) {
		addressHistoryRepository.delete(id);
	}

	@Override
	public AddressHistory createAddressHistory(AddressHistory addressHistory) {
		addressHistory.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return addressHistoryRepository.save(addressHistory);
	}

}
