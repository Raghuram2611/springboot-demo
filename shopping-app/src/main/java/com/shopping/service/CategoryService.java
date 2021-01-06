package com.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Category;
import com.shopping.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> getAllCategory() {
		System.out.println(categoryRepository.findAll());
		return categoryRepository.findAll();
	}
	
	public List<Category> addCategory(List<Category> categories) {
		return categoryRepository.saveAll(categories);
	}

	public Category getCategoryByName(String category) {
		System.out.println("Category = " + categoryRepository.findByName(category));
		return categoryRepository.findByName(category);
	}

}
