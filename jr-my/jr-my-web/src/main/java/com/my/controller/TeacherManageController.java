package com.my.controller;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.ExceptionUtil;
import com.my.commom.utils.JsonUtils;
import com.my.domain.Userjob;
import com.my.service.UserService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 这个只是简单的展示和删除学员
 * @date 2018/5/316:14
 */
@Controller
@RequestMapping(value = "teacherManage")
public class TeacherManageController {
    @Autowired
    private UserService userService;

    private final static Logger logger = (Logger) LoggerFactory.getLogger(TeacherManageController.class );



    @RequestMapping(value="/show", method= RequestMethod.POST)
    @ResponseBody
    public MyResult showStudentController() {
        /*
         /* @author zhangmeining
         * @date 2018/5/3 16:22
         * @Description: 显示学生列表
         * @param []
         * @return com.my.commom.pojo.MyResult
         */
        logger.info("TeacherManageController start...");
        MyResult myResult = null;
        try {
            myResult=userService.getAllStudents();
            logger.info("TeacherManageController  showStudentController myResut is:"+ JsonUtils.objectToJson(myResult));
        } catch (Exception e) {
         logger.error("TeacherManageController showStudentController error");
            return MyResult.build(ErrorConstants.Student_NOTEXISTS_CODE,ErrorConstants.Student_NOTEXISTS_MSG);
        }
        return myResult;

    }





//现在取消删除学员的这项功能
    @RequestMapping(value="/delete", method= RequestMethod.POST)
    @ResponseBody
    public MyResult deleteStudentController() {
        /*
         /* @author zhangmeining
         * @date 2018/5/3 16:23
         * @Description: 删除学生
         * 同步的操作有删除一个学生时同步删掉他的作业userjob表中，和usercourse表中的信息
         * @param []
         * @return com.my.commom.pojo.MyResult
         */
        MyResult myResult=null;
        try {

        }catch (Exception e){

        }
        return myResult;

    }



}
