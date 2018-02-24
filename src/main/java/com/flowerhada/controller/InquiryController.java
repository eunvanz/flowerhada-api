package com.flowerhada.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.Inquiry;
import com.flowerhada.service.InquiryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("inquiries")
public class InquiryController {
	
	@Autowired InquiryService inquiryService;
	
	@PostMapping()
	public Inquiry postInquiry(@RequestBody Inquiry inquiry) {
		return inquiryService.createInquiry(inquiry);
	}
	
	@GetMapping()
	public Page<Inquiry> getAllInquiriesByParentIdIsNull(@RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return inquiryService.readAllByParentIdIsNull(pageRequest);
	}
	
	@GetMapping("/user/{userId}")
	public Page<Inquiry> getAllInauiriesByUserId(@PathVariable Long userId, @RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return inquiryService.readInquiriesByUserId(userId, pageRequest);
	}
	
	@GetMapping("/{id}")
	public Inquiry getInquiryById(@PathVariable Long id) {
		return inquiryService.readInquiryById(id);
	}
	
	@PutMapping("/{id}")
	public Inquiry putInquiry(@PathVariable Long id, @RequestBody Inquiry inquiry) {
		inquiry.setId(id);
		return inquiryService.updateInquiry(inquiry);
	}
	
	@DeleteMapping("/{id}")
	public void deleteInquiry(@PathVariable Long id) {
		inquiryService.deleteInquiryById(id);
	}
	
}
