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

import com.flowerhada.service.ErrorService;

import com.flowerhada.domain.Error;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("errors")
public class ErrorController {

	@Autowired ErrorService errorService;
	
	@GetMapping()
	public Page<Error> getAllErrors(@RequestParam("curPage") int curPage, @RequestParam("perPage") int perPage) {
		PageRequest pageRequest = new PageRequest(curPage, perPage, new Sort(Direction.DESC, "id"));
		return errorService.readAll(pageRequest);
	}
	
	@GetMapping("/{id}")
	public Error getError(@PathVariable Long id) {
		return errorService.readErrorById(id);
	}
	
	@PostMapping()
	public Error postError(@RequestBody Error error) {
		return errorService.createError(error);
	}
	
	@PutMapping("/{id}")
	public Error putError(@RequestBody Error error, @PathVariable Long id) {
		return errorService.updateError(error);
	}
	
	@DeleteMapping("/{id}")
	public void deleteError(@PathVariable Long id) {
		errorService.deleteError(id);
	}
	
}
