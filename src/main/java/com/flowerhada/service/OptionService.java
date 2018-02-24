package com.flowerhada.service;

import java.util.List;

import com.flowerhada.domain.ProductOption;

public interface OptionService {
	public ProductOption createOption(ProductOption option);
	public List<ProductOption> readAllByProductId(Long productId);
	public ProductOption updateOption(ProductOption option);
	public void deleteOption(Long id);
}
