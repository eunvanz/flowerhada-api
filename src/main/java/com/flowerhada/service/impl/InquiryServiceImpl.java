package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.Inquiry;
import com.flowerhada.repository.InquiryRepository;
import com.flowerhada.service.InquiryService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

	@Autowired InquiryRepository inquiryRepository;
	
	@Override
	public Inquiry createInquiry(Inquiry inquiry) {
		inquiry.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return inquiryRepository.save(inquiry);
	}

	@Override
	public Page<Inquiry> readAllInquiries(Pageable pageable) {
		return inquiryRepository.findAll(pageable);
	}

	@Override
	public Page<Inquiry> readInquiriesByUserId(Long userId, Pageable pageable) {
		Page<Inquiry> inquiries = inquiryRepository.findByUserIdOrderByIdDesc(userId, pageable);
		for (Inquiry inquiry : inquiries) {
			List<Inquiry> answers = inquiryRepository.findByParentId(inquiry.getId());
			for (Inquiry answer : answers) {
				inquiry.addAnswer(answer);
			}
		}
		return inquiries;
	}

	@Override
	public Inquiry updateInquiry(Inquiry inquiry) {
		Inquiry oldInquiry = inquiryRepository.findOne(inquiry.getId());
		oldInquiry.setCategory(inquiry.getCategory());
		oldInquiry.setContent(inquiry.getContent());
		oldInquiry.setParentId(inquiry.getParentId());
		oldInquiry.setTitle(inquiry.getTitle());
		oldInquiry.setUpdateDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return oldInquiry;
	}

	@Override
	public void deleteInquiryById(Long id) {
		inquiryRepository.delete(id);
	}

	@Override
	public Page<Inquiry> readAllByParentIdIsNull(Pageable pageable) {
		return inquiryRepository.findAllByParentIdIsNull(pageable);
	}

	@Override
	public Inquiry readInquiryById(Long id) {
		return inquiryRepository.findOne(id);
	}

}
