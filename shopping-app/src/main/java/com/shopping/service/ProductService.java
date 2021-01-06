package com.shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Product;
import com.shopping.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> save(List<Product> products) {
		return productRepository.saveAll(products);
	}
	
	public Product getBookById(Long bookid) {
		return productRepository.findById(bookid).get();
	}

	public Object getProductsByCategory(String category) {
		productRepository.findAll().stream().forEach(b -> System.out.println("Product Category" + b.getCategory()));
		return productRepository.findAll().stream().filter(b -> b.getCategory().equals(categoryService.getCategoryByName(category))).collect(Collectors.toList());
	}

	public Object getCategoryById(Long bookid) {
		return productRepository.findById(bookid).get().getCategory().getName();
	}

}
