package com.my.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;






import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.Logger;

import com.my.dao.BookDao;
import com.my.domain.Book;

public class BookDaoImpl implements BookDao{
	private final static Logger logger = (Logger) LoggerFactory.getLogger(BookDaoImpl.class); 
	
	private SqlSessionFactory sqlSessionFactory;
	
	
	public void initMethod() throws Exception { 
		logger.info("logger中      BookDaoImpl被初始化了");
        System.out.println("initMethod 被执行"); 
        
   }  
    public void destroyMethod() throws Exception { 
    	logger.info("logger中   logger中  BookDaoImpl被销毁了");
    	
        System.out.println("destroyMethod 被执行");  
    }
	
	public SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	@Override
	public int insert(Book b) {
		// TODO Auto-generated method stub
		SqlSession sqlssssion=sqlSessionFactory.openSession();
		int  change=sqlssssion.insert("my.insert");
		return change;
		
	}

	@Override
	public int delete(long id) {
		// TODO Auto-generated method stub
		SqlSession sqlssssion=sqlSessionFactory.openSession();
		int  change=sqlssssion.delete("my.delete", id);
		return change;
		
	}

	@Override
	public int update(Book b) {
		// TODO Auto-generated method stub
		SqlSession sqlssssion=sqlSessionFactory.openSession();
		int  change=sqlssssion.update("my.update");
		return change;
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		SqlSession sqlSession=sqlSessionFactory.openSession();
		List<Book>  books=sqlSession.selectList("my.getAllBooks");
		return books;
	}

	@Override
	public Book getBookById(long id) {
		SqlSession sqlssssion=sqlSessionFactory.openSession();
		Book book=sqlssssion.selectOne("my.getBookById",id);
		return book;
	}

}
