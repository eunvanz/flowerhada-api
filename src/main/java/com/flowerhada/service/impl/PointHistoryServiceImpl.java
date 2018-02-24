package com.flowerhada.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.PointHistory;
import com.flowerhada.domain.User;
import com.flowerhada.repository.PointHistoryRepository;
import com.flowerhada.repository.UserRepository;
import com.flowerhada.service.PointHistoryService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class PointHistoryServiceImpl implements PointHistoryService {

	@Autowired PointHistoryRepository pointHistoryRepository;
	@Autowired UserRepository userRepository;
	
	@Override
	public PointHistory createPointHistory(PointHistory pointHistory) throws Exception {
		pointHistory.setDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		int amount = pointHistory.getAmount();
		Long userId = pointHistory.getUserId();
		User oldUser = userRepository.findOne(userId);
		int currentPoint = oldUser.getPoint();
		if (amount < 0) {
			if (currentPoint < amount) {
				throw new Exception("포인트가 부족합니다.");
			}
		}
		oldUser.setPoint(currentPoint + amount);
		return pointHistoryRepository.save(pointHistory);
	}

	@Override
	public Page<PointHistory> readPointHistoryByUserId(Long userId, Pageable pageRequest) {
		return pointHistoryRepository.findByUserIdOrderByIdDesc(userId, pageRequest);
	}

}
