package com.flowerhada.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.flowerhada.domain.Error;;

public interface ErrorService {
	public Error createError(Error error);
	public Error readErrorById(Long id);
	public Page<Error> readAll(Pageable pageable);
	public Error updateError(Error error);
	public void deleteError(Long id);
}
