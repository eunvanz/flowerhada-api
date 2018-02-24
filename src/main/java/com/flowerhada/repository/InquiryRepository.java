package com.flowerhada.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.flowerhada.domain.Inquiry;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
	public Page<Inquiry> findByUserIdOrderByIdDesc(Long userId, Pageable pageable);
	public Page<Inquiry> findAllByParentIdIsNull(Pageable pageable);
	public List<Inquiry> findByParentId(Long parentId);
}
