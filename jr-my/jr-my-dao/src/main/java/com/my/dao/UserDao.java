package com.my.dao;

import com.my.domain.User;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2017/12/2820:26
 */
public interface UserDao {
    public int insert(User user);
    public int delete( String userId);
    public int update(User user);
    public List<User> getAllUsers();
    public User getUserById(String userId);

//得到所有的学生
    public List<User> getAllStudents();

}
