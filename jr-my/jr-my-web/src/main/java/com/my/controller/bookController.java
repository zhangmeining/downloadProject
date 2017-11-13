package com.my.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.my.domain.Book;
import com.my.service.BookService;




@Controller
public class bookController {
	@Resource(name = "bookService")
	 private BookService    bookService;
    
    public bookController(){
   	 System.out.println("@@@@@@@@@@@@@@@bookController");
    }
    
  //http://localhost:8080/book/getAll
  	@RequestMapping("book/getAll")
  	//@ResponseBody
  	public String getAllBooks(){
  		List<Book> books=bookService.getAllBooks();
  		return "getAllBooks";
  		
  		
  	}
  	//http://localhost:8080/book/1
  	@RequestMapping("book/{id}")
  	//@ResponseBody
  	public ModelAndView getBookById(@PathVariable long id,ModelAndView modeview,Model model){
  		Book book=bookService.getBookById(id);
  		 modeview=new ModelAndView("getBookById");
  		 modeview.addObject("modeview", book);
  		 
  		 return modeview;
  		//model.addAttribute("model", book);
  		 //return   "getBookById";
  		
  		
  	}
  	

}
