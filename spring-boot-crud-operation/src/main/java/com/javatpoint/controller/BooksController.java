package com.javatpoint.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatpoint.model.Books;
import com.javatpoint.service.BooksService;

@RestController
public class BooksController {
	
	@Autowired
	private BooksService booksService;
	
	@GetMapping("/books")
	public List<Books> getAllBooks(){
		return booksService.getAllBooks();
	}

	@GetMapping("/books/order/desc")
	public List<Books> getAllBooksInDesc(){
		return booksService.getAllBooksInDesc();
	}	

//	@GetMapping("/books/author")
//	public List<String> getBooksByAuthor(){
//		return booksService.getAllBooksByAuthor();
//	}	
//
//	@GetMapping("/books/bookname")
//	public List<String> getBooksByBookNames(){
//		return booksService.getAllBooksByBookname();
//	}

	@GetMapping("/books/table/{column}")
	public List<String> getBooksByColumn(@PathVariable("column") String column){
		return booksService.getAllBooksByColumn(column);
	}
	
	
	@GetMapping("/books/bookname-author")
	public Map<String, String> getBooksTwoColumns() {
		return booksService.getBooksTwoColumn();
	}
	
	@GetMapping("/books/{bookid}")
	public Books getBooks(@PathVariable("bookid") int bookid) {
		return booksService.getBooksById(bookid);
	}
	
	@GetMapping("/books/deleted")
	public Map<String, String> getDeletedBooks() {
		return booksService.getDeletedBooks();
	}
	
	@DeleteMapping("/books/{bookid}")
	public String deleteBook(@PathVariable("bookid") int bookid) {
		return booksService.delete(bookid);
	}
	
	@PostMapping("/books")
	public List<Books> saveBook(@RequestBody List<Books> listBooks){
		booksService.saveAllBooks(listBooks);
		return getAllBooks();
	}
	
	@PutMapping("/books")
	public Books update(@RequestBody Books books){
		booksService.saveOrUpdate(books);
		return books;
	}
}
