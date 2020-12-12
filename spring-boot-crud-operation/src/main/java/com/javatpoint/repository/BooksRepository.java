package com.javatpoint.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.javatpoint.model.Books;

public interface BooksRepository extends JpaRepository<Books, Integer> {
	
//	@Query("Select c from Books c ORDER BY c.bookid DESC")
//	public List<Books> booksInDescendingOrder();
	
	public List<Books> findAllByOrderByBookidDesc();

	public List<Books> findAllByOrderByBookidAsc();
}
