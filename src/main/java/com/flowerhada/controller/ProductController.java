package com.flowerhada.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flowerhada.domain.Product;
import com.flowerhada.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

	@Autowired ProductService productService;
	
	@PostMapping()
	public Product createProduct(@ModelAttribute Product Product) {
		return productService.createProduct(Product);
	}
	
	@GetMapping("/main-category/{mainCategory}")
	public List<Product> readProductByMainCategory(@PathVariable String mainCategory) {
		return productService.readProductsByMainCategory(mainCategory);
	}
	
	@GetMapping("/sub-category/{subCategory}")
	public List<Product> readProductBySubCategory(@PathVariable String subCategory) {
		return productService.readProductsBySubCategory(subCategory);
	}
	
	@GetMapping("/relation-name/{relationName}")
	public List<Product> readProductByRelationName(@PathVariable String relationName) {
		return productService.readProductsByRelationName(relationName);
	}
	
	@GetMapping("/{id}")
	public Product readProduct(@PathVariable Long id) {
		return productService.readProduct(id);
	}
	
	@GetMapping()
	public List<Product> readProducts() {
		return productService.readProducts();
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@ModelAttribute Product Product, @PathVariable Long id) {
		Product.setId(id);
		return productService.updateProduct(Product);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}
	
}
