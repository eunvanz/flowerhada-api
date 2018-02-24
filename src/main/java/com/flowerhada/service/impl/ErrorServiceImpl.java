package com.flowerhada.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.repository.ErrorRepository;
import com.flowerhada.service.ErrorService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

import com.flowerhada.domain.Error;

@Service
@Transactional
@RequiredArgsConstructor
public class ErrorServiceImpl implements ErrorService {

	@Autowired ErrorRepository errorRepository;
	
	@Override
	public Error createError(Error error) {
		error.setDate(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return errorRepository.save(error);
	}

	@Override
	public Error readErrorById(Long id) {
		return errorRepository.findOne(id);
	}

	@Override
	public Error updateError(Error error) {
		Error oldError = errorRepository.findOne(error.getId());
		oldError.setUserId(error.getUserId());
		oldError.setLog(error.getLog());
		oldError.setStatus(error.getStatus());
		oldError.setType(error.getType());
		return oldError;
	}

	@Override
	public void deleteError(Long id) {
		errorRepository.delete(id);
	}

	@Override
	public Page<Error> readAll(Pageable pageable) {
		return errorRepository.findAll(pageable);
	}

}
