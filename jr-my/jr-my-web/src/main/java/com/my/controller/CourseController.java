package com.my.controller;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.ExceptionUtil;
import com.my.commom.utils.JsonUtils;
import com.my.domain.Course;
import com.my.domain.UserCourse;
import com.my.service.CourseService;
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
 * @Description: ${todo}
 * @date 2018/1/715:17
 */
@Controller
@RequestMapping(value = "course")
public class CourseController {
    @Autowired
    private CourseService  courseService;

    private final static Logger logger = (Logger) LoggerFactory.getLogger(CourseController.class);

//http://localhost:8080/course/create

      /*
         /* @author zhangmeining
         * @date 2018/1/7 21:54
         * @Description: 创建课程
         * @param [course]
         * @return com.my.commom.pojo.MyResult
         */
@RequestMapping(value="/create", method= RequestMethod.POST)
    @ResponseBody
    public MyResult createCourseController(Course course) {
        MyResult myResult=null;
        try {
            myResult = courseService.createCourse(course);
        }catch (Exception e){
            logger.error("在CourseController--createCourseController"+"创建课程失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.CREATE_COURSE_FAILURE_CODE,ErrorConstants.CREATE_COURSE_FAILURE_MSG);
        }
       return myResult;

}

    /*
         /* @author zhangmeining
         * @date 2018/1/7 21:53
         * @Description: 根据课程号删除课程
         * @param [courseId]
         * @return com.my.commom.pojo.MyResult
         */
    // http://localhost:8080/course/delete
    @RequestMapping(value="/delete", method= RequestMethod.POST)
    @ResponseBody
    public MyResult deleteCourseController(Long courseId) {
        MyResult myResult=null;
        try {
            myResult = courseService.deleteCourse(courseId);
        }catch (Exception e){
            logger.error("在CourseController--deleteCourseController"+"删除课程失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.DELETE_COURSE_FAILURE_CODE,ErrorConstants.DELETE_COURSE_FAILURE_MSG);
        }
        return myResult;

    }


    /*
     /* @author zhangmeining
     * @date 2018/1/7 23:14
     * @Description: 得到创建者创建的全部的课程
     * @param [userId]
     * @return com.my.commom.pojo.MyResult
     */
    // http://localhost:8080/course/getAllCoursesCreator
    @RequestMapping(value="/getAllCoursesCreator", method= RequestMethod.POST)
    @ResponseBody
    public MyResult getAllCoursesCreatorController(String userId) {
        logger.info("入参是：" + userId);
        MyResult myResult = null;
        try {
            myResult = courseService.getALLCoursesCreator(userId);
        }catch (Exception e){
            logger.error("在CourseController--getAllCoursesController"+"得到创建者创建的全部的课程失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.COURSE_GET_FAILURE_CODE,ErrorConstants.COURSE_GET_FAILURE_MSG);
        }
        return myResult;
    }



    @RequestMapping(value="/getOneStuAllCourses", method= RequestMethod.POST)
    @ResponseBody
    public MyResult getOneStuAllCoursesController( String userId) {
     /*
      /* @author zhangmeining
      * @date 2018/5/10 21:43
      * @Description: 获得某个课程的所有学生
      * @param [courseId]
      * @return com.my.commom.pojo.MyResult
      */
        logger.info("入参是：courseId"+userId);
        MyResult myResult=null;
        try {
            myResult=courseService.getOneStuAllCourses(userId);
            logger.info("getOneStuAllCoursesController service返回结果是："+ JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            return  MyResult.build(ErrorConstants.STU_NO_COURSE_CODE, ErrorConstants.STU_NO_COURSE_MSG);
        }
        return myResult;

    }


    // http://localhost:8080/course/join
    @RequestMapping(value="/join", method= RequestMethod.POST)
    @ResponseBody
    public MyResult joinCourseController(UserCourse userCourse) {
        /*
         /* @author zhangmeining
         * @date 2018/4/5 22:05
         * @Description: 加入课程
         * @param [userCourse]
         * @return com.my.commom.pojo.MyResult
         */
        MyResult myResult=null;
        try {
            myResult = courseService.joinCourse(userCourse);
        }catch (Exception e){
            logger.error("在CourseController--joinCourseController"+"加入课程失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOIN_COURSE_FAILURE_CODE,ErrorConstants.JOIN_COURSE_FAILURE_MSG);
        }
        return myResult;
    }



    /*
          /* @author zhangmeining
          * @date 2018/1/9 17:44
          * @Description: 退出课程
          * @param [userCourse]
          * @return com.my.commom.pojo.MyResult
          */
    // http://localhost:8080/course/exit
    @RequestMapping(value="/exit", method= RequestMethod.POST)
    @ResponseBody
    public MyResult exitCourseController(UserCourse userCourse) {
        logger.info("入参是："+JsonUtils.objectToJson(userCourse));
        MyResult myResult=null;
        try {
            myResult = courseService.exitCourse(userCourse);
            logger.info("调用service返回的结果是："+JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("在CourseController--exitCourseController"+"退出课程失败"+ ExceptionUtil.getStackTrace(e));
            return   MyResult.build(ErrorConstants.EXIT_COURSE_FAILURE_CODE,ErrorConstants.EXITCOURSE_FAILURE_MSG);
        }
        return myResult;
    }



    @RequestMapping(value="/getAllStuByCourseId", method= RequestMethod.POST)
    @ResponseBody
    public MyResult getAllStuByCourseIdController( Long  courseId) {
     /*
      /* @author zhangmeining
      * @date 2018/5/10 21:43
      * @Description: 获得某个课程的所有学生
      * @param [courseId]
      * @return com.my.commom.pojo.MyResult
      */
        logger.info("入参是：courseId"+courseId);
        MyResult myResult=null;
        try {
            myResult=courseService.getAllStuByCourseId(courseId);
            logger.info("getAllStuByCourseIdController service返回结果是："+ JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            return MyResult.build(ErrorConstants.GET_STU_BYCOURSEID_FAILURE_CODE, ErrorConstants.GET_STU_BYCOURSEID_FAILURE_MSG);
        }
        return myResult;

    }


    @RequestMapping(value="/countScore", method= RequestMethod.POST)
    @ResponseBody
    public MyResult countScoreController( Long  courseId,String userId) {
      /*
       /* @author zhangmeining
       * @date 2018/5/7 10:06
       * @Description: 在一个课程的情况下统计这个课程的所有成员的所有作业的成绩,先拿到这个课程号然后拿到这个课程的所有成员，然后拿到这个成员的所有的作业然后统计这些作业的分数
       * 重要说明：这里的title意思改为题型的分类的意思。。
       * @param []
       * @return com.my.commom.pojo.MyResult
       */
        MyResult myResult=null;
        try {
            myResult=courseService.countScore(userId,courseId);
            logger.info("service返回结果是："+ JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("在CourseController--countScoreController"+"统计成绩失败"+ ExceptionUtil.getStackTrace(e));
        }
        return myResult;

    }




    @RequestMapping(value="/countScoreByQuestionType", method= RequestMethod.POST)
    @ResponseBody
    public MyResult countScoreByQuestionTypeController( Long  courseId,String userId,String questionType) {
      /*
       /* @author zhangmeining
       * @date 2018/5/7 10:06
       * @Description: 在一个课程的情况下统计这个课程的所有成员的所有作业的成绩,先拿到这个课程号然后拿到这个课程的所有成员，然后拿到这个成员的所有的作业然后统计这些作业的分数
       * 重要说明：这里的title意思改为题型的分类的意思。。
       * @param []
       * @return com.my.commom.pojo.MyResult
       */
        MyResult myResult=null;
        try {
            myResult=courseService.countScoreWithTeaByQuestionType(userId,courseId,questionType);
            logger.info("service返回结果是："+ JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("在countScoreByQuestionTypeController "+"统计课程成绩失败"+ ExceptionUtil.getStackTrace(e));
        }
        return myResult;

    }























    //******************************************************//



    /*
        /* @author zhangmeining
        * @date 2018/1/7 21:53
        * @Description: 根据用户号来拿到参加的所有课程
        * @param [courseUserId]
        * @return com.my.commom.pojo.MyResult
        */
    // http://localhost:8080/course/getAllCourses
    @RequestMapping(value="/getAllCourses", method= RequestMethod.POST)
    @ResponseBody
    public MyResult getAllCoursesController(String courseUserId) {
        MyResult myResult=null;
        try {
            myResult = courseService.getALLCourses(courseUserId);
        }catch (Exception e){
            logger.error("在CourseController--getAllCoursesController"+"得到全部的课程失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.COURSE_GET_FAILURE_CODE,ErrorConstants.COURSE_GET_FAILURE_MSG);
        }
        return myResult;

    }



}
