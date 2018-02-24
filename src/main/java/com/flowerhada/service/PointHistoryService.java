package com.flowerhada.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flowerhada.domain.PointHistory;

public interface PointHistoryService {
	public PointHistory createPointHistory(PointHistory pointHistory) throws Exception;
	public Page<PointHistory> readPointHistoryByUserId(Long userId, Pageable pageRequest);
}
