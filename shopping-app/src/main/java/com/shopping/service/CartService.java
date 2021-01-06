package com.shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.entity.Account;
import com.shopping.entity.Cart;
import com.shopping.entity.Product;
import com.shopping.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;

	public String addToCart(List<Cart> carts) {
		cartRepository.saveAll(carts);
		return "saved";
	}

	public List<Cart> allCartItems() {
		return cartRepository.findAll().stream().collect(Collectors.toList());
	}

	public void add(Cart cart) {
		cartRepository.save(cart);
	}

	public List<Product> getProductsByUserId(Account account) {
		return cartRepository.findAll().stream().filter(b -> b.getUser().equals(account)).map(b -> b.getProduct()).collect(Collectors.toList());
	}

}
