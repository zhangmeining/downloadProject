package com.my.controller;

import com.my.dao.CourseDao;
import com.my.dao.UserDao;
import com.my.domain.User;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2017/12/2911:49
 */
@Controller
public class TestController {
    @Resource(name = "userService")
    private UserService userService;


    @Autowired
    private UserDao userDao;


    @Autowired
    private CourseDao courseDao;

    //http://localhost:8080/user/04141031
    @RequestMapping("user")
    @ResponseBody
    //@PathVariable
    public User getUserById( String userId, ModelAndView modeview, Model model){
        System.out.println("long long"+userId);
        //String userId1=String.valueOf(userId);
        System.out.println(userId);
      User user=userDao.getUserById(userId);
       return user;
    }


    //http://192.168.1.109:8080/user/getAll
    //http://localhost:8080/user/getAll
    @RequestMapping("user/getAll")
    @ResponseBody
    public List<User> getAllUsers(){

        System.out.println("9999");
        List<User> users=userDao.getAllUsers();
        return users;

    }





    @RequestMapping("course/getCourseJobNumbers")
    @ResponseBody
    public int  getCourseJobNumbers(Long courseId){

      int n= courseDao.getCourseJobNumbers(courseId);
       return n;

    }
}
