package com.javatpoint.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javatpoint.model.Books;
import com.javatpoint.repository.BooksRepository;

@Service
public class BooksService {
	
	@Autowired
	private BooksRepository booksRepository;
	
	Map<String,String> deletedBooks = new HashMap<>();
	
	public List<Books> getAllBooks() {
		return booksRepository.findAll();
	}
	
	public List<String> getAllBooksByAuthor() {
		return booksRepository.findAll().stream().map(e -> e.getAuthor()).collect(Collectors.toList());
	}
	
	public List<String> getAllBooksByBookname() {
		return booksRepository.findAll().stream().map(e -> e.getBookname()).collect(Collectors.toList());
	}

	public Books getBooksById(int bookid) {
		return booksRepository.findById(bookid).get();
	}

	public String delete(int bookid) {
		if (booksRepository.findById(bookid).isPresent()) {
			deletedBooks.put(booksRepository.findById(bookid).get().getBookname(), booksRepository.findById(bookid).get().getAuthor());
			booksRepository.deleteById(bookid);
			return "Book with id: "+bookid+" deleted";
		}
		return "Book with id: "+bookid+" not found";
	}
	
	public Map<String, String> getDeletedBooks() {
		return deletedBooks;
	}

	public void saveOrUpdate(Books books) {
		booksRepository.save(books);
	}

	public void saveAllBooks(List<Books> listBooks) {
		booksRepository.saveAll(listBooks);
	}

	public List<Books> getAllBooksInDesc() {
		return booksRepository.findAllByOrderByBookidDesc();
	}

	public Map<String, String> getBooksTwoColumn() {
		Map<String,String> map = new HashMap<>();
		for (Books books : booksRepository.findAll()) {
			map.put(books.getBookname(), books.getAuthor());
		}
		return map;
	}

	public List<String> getAllBooksByColumn(String column) {
		if (column.equals("author")) {
			return booksRepository.findAll().stream().map(b -> b.getAuthor()).collect(Collectors.toList());
		}else if (column.equals("bookname")) {
				return booksRepository.findAll().stream().map(b -> b.getBookname()).collect(Collectors.toList());
		}
		return null;
	}

}
