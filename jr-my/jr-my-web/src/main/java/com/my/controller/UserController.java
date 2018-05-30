package com.my.controller;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.ExceptionUtil;
import com.my.commom.utils.JsonUtils;
import com.my.domain.User;
import com.my.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 在这里头像的连接是，先点击上传头像然后胡返回头像在图片服务器中的地址，然后app端一起传过来
 * @date 2017/12/2912:18
 */
//服务器的根路径是http://localhost:8080/
@Controller
@RequestMapping(value = "user")
public class UserController {
    @Autowired
    private UserService userService;

    private final static Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);


    /*
            /* @author zhangmeining
            * @date 2017/12/29 12:21
            * @Description: 用户注册模块
            * @param [user]
            * @return boolean
            */
    //http://192.168.1.109:8080/register
    //http://localhost:8080/register
    //创建用户value="/register", method=RequestMethod.POST，不写get，post都支持
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public MyResult registerController(User user) {
        logger.info("registerController 入参：" + JsonUtils.objectToJson(user));
        MyResult result = null;

        //参数有效性校验
        if (StringUtils.isBlank(user.getUserId())) {
            result = MyResult.build(ErrorConstants.USER_INPUT_PARAM_CODE, ErrorConstants.USER_INPUT_PARAM_MSG);
            return result;
        }


        try {
            MyResult myResult = userService.insert(user);
            logger.info("调用service返回的数据是：" + JsonUtils.objectToJson(myResult));
            return myResult;
        } catch (Exception e) {
            logger.error("UserController" + ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.USER_REGISTER_FAILURE_CODE, ErrorConstants.USER_REGISTER_FAILURE_MSG);
        }
    }


    /*
        /* @author zhangmeining
        * @date 2017/12/29 20:33
        * @Description: TODO
        * @param [user]
        * @return com.my.commom.pojo.MyResult
        */
    //http://192.168.1.109:8080/login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public MyResult loginController(User user) {
        try {
            MyResult result = userService.login(user);
            return result;
        } catch (Exception e) {
            logger.error("loginController  登录失败" + ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.USER_LOGIN_FAILURE_CODE, ErrorConstants.USER_LOGIN_FAILURE_MSG);
        }
    }

    /*
     /* @author zhangmeining
     * @date 2017/12/29 20:34
     * @Description: 完善信息时修改用户的信息
     * @param [user]
     * @return com.my.commom.pojo.MyResult
     */
    //http://192.168.1.109:8080/modify
    //http://localhost:8080/modify
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public MyResult modifyUserController(User user) {
        try {
            MyResult result = userService.update(user);
            return result;
        } catch (Exception e) {
            logger.error("modifyUserController 用户信息修改失败" + ExceptionUtil.getStackTrace(e));
            e.printStackTrace();
            return MyResult.build(ErrorConstants.USER_MODIFY_FAILURE_CODE, ErrorConstants.USER_MODIFY_FAILURE_MSG);
        }

    }
}
