package com.my.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.dao.BookDao;
import com.my.domain.Book;
import com.my.service.BookService;

@Service(value="bookService")
public class BookServiceImpl implements BookService{
	private BookServiceImpl(){
		System.out.println("BookServiceImpl初始化");
	}
	
	
	@Autowired
	private BookDao bookDao;

	@Override
	public int insert(Book b) {
		// TODO Auto-generated method stub
		
		return bookDao.insert(b);
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		
		return bookDao.delete(id);
	}

	@Override
	public int update(Book b) {
		// TODO Auto-generated method stub
		
		return bookDao.update(b);
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		List<Book> books=bookDao.getAllBooks();
		return books;
	}

	@Override
	public Book getBookById(long id) {
		// TODO Auto-generated method stub
//		System.out.println("BookServiceImpl BookServiceImpl"+id);
//		System.err.println("BookServiceImpl BookServiceImpl"+id);
		Book book=bookDao.getBookById(id);
		return book;
	}

}
