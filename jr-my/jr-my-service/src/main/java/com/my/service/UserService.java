package com.my.service;

import com.my.commom.pojo.MyResult;
import com.my.domain.User;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2017/12/2911:43
 */
public interface UserService {
    public MyResult insert(User user);
    //public MyResult delete( long userId);
    public MyResult update(User user);
    //public List<User> getAllUsers();
    //public User getUserById(long userId);
    public MyResult login(User user);


    //得到所有的学生列表
    public MyResult getAllStudents();


    //删除某个学生，这里要做一些同步操作
    public MyResult deleteStudent();
}
