package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.ProductOption;
import com.flowerhada.repository.OptionRepository;
import com.flowerhada.service.OptionService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {

	@Autowired OptionRepository optionRepository;
	
	@Override
	public ProductOption createOption(ProductOption option) {
		return optionRepository.save(option);
	}

	@Override
	public List<ProductOption> readAllByProductId(Long productId) {
		return optionRepository.findByProductId(productId);
	}

	@Override
	public ProductOption updateOption(ProductOption option) {
		ProductOption oldOption = optionRepository.findOne(option.getId());
		oldOption.setAddPrice(option.getAddPrice());
		oldOption.setCategory(option.getCategory());
		oldOption.setName(option.getName());
		return oldOption;
	}

	@Override
	public void deleteOption(Long id) {
		optionRepository.delete(id);
	}

}
