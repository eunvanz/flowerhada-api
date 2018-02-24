package com.flowerhada.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.PointHistory;

public interface PointHistoryRepository extends JpaRepository<PointHistory, Long> {
	public Page<PointHistory> findByUserIdOrderByIdDesc(Long userId, Pageable pageRequest);
}
