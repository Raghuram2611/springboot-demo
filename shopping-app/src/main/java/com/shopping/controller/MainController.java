package com.shopping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shopping.entity.Cart;
import com.shopping.entity.Category;
import com.shopping.entity.Product;
import com.shopping.service.AccountService;
import com.shopping.service.CartService;
import com.shopping.service.CategoryService;
import com.shopping.service.ProductService;

@Controller
public class MainController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private AccountService accountService;

//	@GetMapping("/")
//	public String loginPage() {
//		return "login";
//	}
	
//	@GetMapping("/newuser")
//	public String newUserPage() {
//		return "newuser";
//	}
	
	@GetMapping("/")
	public String showWelcome(ModelMap model, @AuthenticationPrincipal UserDetails user) {
//		boolean isValidUser = accountService.validateUser(username, password);
//		if (!isValidUser) {
//			model.put("errorMessage", "Invalid Credentials");
//			return "login";
//		}
		model.put("user", accountService.getNameByUsername(user.getUsername()));
		model.put("products", getProducts());
		model.put("category", getCategories());
		return "welcome";
	}
	
//	@PostMapping("/newuser")
//	public String newUserCreation(ModelMap model,@RequestParam String name,@RequestParam String username,@RequestParam String password1,@RequestParam String password2) {
//		String creation = accountService.newUser(name, username, password1, password2);
//		if (creation.equals("Account Created")) {
//			model.put("errorMessage", creation);
//			return "redirect:/";
//		}
//		model.put("errorMessage", creation);
//		return "newuser";
//	}
	
	@GetMapping("/products")
	@ResponseBody
	public List<Product> getProducts(){
		return productService.getAllProducts();
	}
	
	@PostMapping("/products")
	@ResponseBody
	public List<Product> producUpload(@RequestBody List<Product> products){
		return productService.save(products);
	}
	
	@GetMapping("/book/{bookid}")
	public String productUpload(ModelMap model, @PathVariable Long bookid, @AuthenticationPrincipal UserDetails user) {
		model.put("page", "bookspg");
		model.put("products", productService.getBookById(bookid));
		model.put("bcategory", productService.getCategoryById(bookid));
		model.put("user", accountService.getNameByUsername(user.getUsername()));
		model.put("category", getCategories());
		return "bookspage";
	}
	
	@PostMapping("/book/{bookid}")
	public String productAdd(ModelMap model, @PathVariable Long bookid, @RequestParam("products") Long product, @AuthenticationPrincipal UserDetails user) {
		Cart cart = new Cart();
		cart.setUser(accountService.getAccountByName(user.getUsername()));
		cart.setProduct(productService.getBookById(product));
		cartService.add(cart);
		model.put("user", accountService.getNameByUsername(user.getUsername()));
		model.put("products", cartService.getProductsByUserId(accountService.getAccountByName(user.getUsername())));
		model.put("category", getCategories());
		return "cart";
	}
	
	@GetMapping("/{bookcat}")
	public String getPageByCategory(ModelMap model, @PathVariable("bookcat") String category, @AuthenticationPrincipal UserDetails user) {
		model.put("user", accountService.getNameByUsername(user.getUsername()));
		model.put("products", productService.getProductsByCategory(category));
		model.put("category", getCategories());
		return "welcome";
	}
	
	@GetMapping("/usercart")
	public String getUserCart(ModelMap model, @AuthenticationPrincipal UserDetails user) {
			model.put("user", accountService.getNameByUsername(user.getUsername()));
			model.put("products", cartService.getProductsByUserId(accountService.getAccountByName(user.getUsername())));
			model.put("category", getCategories());
			return "cart";
	}
	
	@PostMapping("/cart")
	@ResponseBody
	public String addToCart(@RequestBody List<Cart> carts) {
		return cartService.addToCart(carts);
	}
	
	@GetMapping("/cart")
	@ResponseBody
	public List<Cart> getCartItems(){
		return cartService.allCartItems();
	}
	
	@GetMapping("/category")
	@ResponseBody
	public List<Category> getCategories(){
		return categoryService.getAllCategory();
	}
	
	@PostMapping("/category")
	@ResponseBody
	public List<Category> saveAllCategory(@RequestBody List<Category> categories){
		return categoryService.addCategory(categories);
	}
}
