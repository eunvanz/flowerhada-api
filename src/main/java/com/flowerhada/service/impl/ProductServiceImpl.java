package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.Product;
import com.flowerhada.repository.ProductRepository;
import com.flowerhada.service.ProductService;
import com.flowerhada.util.Utils;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

	@Autowired ProductRepository productRepository; 
	
	@Override
	public Product createProduct(Product product) {
		product.setRegDateTime(Utils.getZonedDateTimeNow("Asia/Seoul"));
		return productRepository.save(product);
	}

	@Override
	public Product readProduct(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product updateProduct(Product product) {
		Product oldProduct = productRepository.findOne(product.getId());
		oldProduct.setContent(product.getContent());
		oldProduct.setDetail(product.getDetail());
		oldProduct.setDiscountedPrice(product.getDiscountedPrice());
		oldProduct.setGroupName(product.getGroupName());
		oldProduct.setImages(product.getImages());
		oldProduct.setMainCategory(product.getMainCategory());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setSoldout(product.isSoldout());
		oldProduct.setSubCategory(product.getSubCategory());
		oldProduct.setTitle(product.getTitle());
		oldProduct.setTitleImg(product.getTitleImg());
		oldProduct.setRelationName(product.getRelationName());
		oldProduct.setActivated(product.isActivated());
		oldProduct.setDeliveryType(product.getDeliveryType());
		return oldProduct;
	}

	@Override
	public void deleteProduct(Long id) {
		productRepository.delete(id);
	}

	@Override
	public List<Product> readProducts() {
		return productRepository.findAllByOrderByIdDesc();
	}

	@Override
	public List<Product> readProductsByMainCategory(String mainCategory) {
		return productRepository.findByMainCategoryAndActivatedOrderByIdDesc(mainCategory, true);
	}
	
	@Override
	public List<Product> readProductsByRelationName(String relationName) {
		return productRepository.findByRelationNameAndActivatedOrderByIdDesc(relationName, true);
	}

	@Override
	public List<Product> readProductsBySubCategory(String subCategory) {
		return productRepository.findBySubCategoryAndActivatedOrderByIdDesc(subCategory, true);
	}

}
