package com.flowerhada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.ProductOption;
import com.flowerhada.service.OptionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("options")
public class OptionController {

	@Autowired OptionService optionService;
	
	@GetMapping("/product/{productId}")
	public List<ProductOption> readOption(@PathVariable Long productId) {
		return optionService.readAllByProductId(productId);
	}
	
	@PostMapping()
	public ProductOption createOption(@RequestBody ProductOption option) {
		return optionService.createOption(option);
	}
	
	@PutMapping("/{id}")
	public ProductOption updateOption(@RequestBody ProductOption option, @PathVariable Long id) {
		option.setId(id);
		return optionService.updateOption(option);
	}
	
	@DeleteMapping("/{id}")
	public void deleteOption(@PathVariable Long id) {
		optionService.deleteOption(id);
	}
	
}
