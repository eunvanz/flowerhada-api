package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.AddressHistory;

public interface AddressHistoryRepository extends JpaRepository<AddressHistory, Long> {
	public List<AddressHistory> findByUserIdOrderByUpdateDateDesc(Long userId);
}
