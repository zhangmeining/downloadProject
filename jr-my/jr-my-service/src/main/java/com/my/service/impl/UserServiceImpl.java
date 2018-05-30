package com.my.service.impl;

import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.dao.UserDao;
import com.my.domain.User;
import com.my.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2017/12/2911:43
 */
@Service(value="userService")
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    /*
       /* @author zhangmeining
       * @date 2017/12/29 13:52
       * @Description: 用户注册
       * @param [user]
       * @return com.my.commom.pojo.MyResult
       */
    public MyResult insert(User user) {
        User user1=null;
        //判断这个id是否已经注册过
        user1=userDao.getUserById(user.getUserId());
        if(user1==null){
        }else{
            /**
             * 返回数据中的data，true：数据可用，false：数据不可用
             * **/
            return MyResult.build(ErrorConstants.USER_EXISTS_CODE,ErrorConstants.USER_EXISTS_MSG);   //前端取到MyResult中的data值判断是否是true，FALSE
        }
        //md5加密  使用spring中提供的加密类
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setCreated(new Date());
        int change=userDao.insert(user);
        if(change>0){
          return MyResult.ok(true);  //this.status = 200; this.msg = "OK";this.data = data;
        }
        return MyResult.build(ErrorConstants.USER_REGISTER_FAILURE_CODE,ErrorConstants.USER_REGISTER_FAILURE_MSG);
    }

//    public MyResult delete(long userId) {
//        int change=userDao.delete(userId);
//        if(change!=0){
//            return true;
//        }
//        return false;
//    }



    /*
       /* @author zhangmeining
       * @date 2017/12/29 13:54
       * @Description: 修改个人信息
       * @param [user]
       * @return com.my.commom.pojo.MyResult
       */
    public MyResult update(User user) {
        //先判断要修给的用户是否存在
        User user1=null;
        user1=userDao.getUserById(user.getUserId());
        if(user1==null){
            return MyResult.build(ErrorConstants.USER_NOTEXISTS_CODE,ErrorConstants.USER_NOTEXISTS_MSG);
        }
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        user.setUpdated(new Date());
        int change=userDao.update(user);
        if(change!=0){
            return MyResult.ok(true);
        }
        return MyResult.build(ErrorConstants.USER_MODIFY_CODE,ErrorConstants.USER_MODEIY_MSG);
    }

//    public List<User> getAllUsers() {
//        List<User>  users=userDao.getAllUsers();
//        return users;
//    }

//    public User getUserById(long userId) {
//        User user=userDao.getUserById(userId);
//        return user;
//    }



    /*
        /* @author zhangmeining
        * @date 2017/12/29 13:54
        * @Description: 用户登录
        * @param [user]
        * @return com.my.commom.pojo.MyResult
        */
    public MyResult login(User user) {
        String userId=user.getUserId();
        User user1=null;
       user1=userDao.getUserById(userId);


//如果没有此用户id
        if (user1==null) {
            return MyResult.build(ErrorConstants.USER_NAME_PASSWORD_CODE, ErrorConstants.USER_NAME_PASSWORD_MSG);
        }

        //比对密码
        if (!DigestUtils.md5DigestAsHex(user.getPassword().getBytes()).equals(user1.getPassword())) {
            return MyResult.build(ErrorConstants.USER_NAME_PASSWORD_CODE, ErrorConstants.USER_NAME_PASSWORD_MSG);
        }
        return MyResult.ok(true);

    }

    public MyResult getAllStudents() {
        List<User>  users=userDao.getAllStudents();
        if(users.size()<=0){
            return MyResult.build(ErrorConstants.Student_NOTEXISTS_CODE,ErrorConstants.Student_NOTEXISTS_MSG);
        }
        return MyResult.ok(users);
    }

    public MyResult deleteStudent() {
        return null;
    }
}
