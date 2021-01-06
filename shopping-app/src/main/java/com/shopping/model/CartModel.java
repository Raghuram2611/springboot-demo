package com.shopping.model;

import java.util.List;

import com.shopping.entity.Product;

public class CartModel {
	
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
