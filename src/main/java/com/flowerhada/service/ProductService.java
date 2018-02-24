package com.flowerhada.service;

import java.util.List;

import com.flowerhada.domain.Product;

public interface ProductService {
	public Product createProduct(Product product);
	public Product readProduct(Long id);
	public Product updateProduct(Product product);
	public void deleteProduct(Long id);
	public List<Product> readProducts();
	public List<Product> readProductsByMainCategory(String mainCategory);
	public List<Product> readProductsBySubCategory(String subCategory);
	public List<Product> readProductsByRelationName(String relatinName);
}
