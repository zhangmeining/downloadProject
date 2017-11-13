package com.my.service;

import java.util.List;

import com.my.domain.Book;



public interface BookService {
	public	int insert(Book b);
	public	int delete(long id);
	public	int update(Book b);
	public List<Book> getAllBooks();
	public	Book getBookById(long id);

}
