package com.flowerhada.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flowerhada.domain.Inquiry;

public interface InquiryService {
	public Inquiry createInquiry(Inquiry inquiry);
	public Page<Inquiry> readAllInquiries(Pageable pageable);
	public Page<Inquiry> readInquiriesByUserId(Long userId, Pageable pageable);
	public Page<Inquiry> readAllByParentIdIsNull(Pageable pageable);
	public Inquiry updateInquiry(Inquiry inquiry);
	public void deleteInquiryById(Long id);
	public Inquiry readInquiryById(Long id);
}
