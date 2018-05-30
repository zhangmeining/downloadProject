package com.my.controller;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.ExceptionUtil;
import com.my.commom.utils.JsonUtils;
import com.my.domain.Job;
import com.my.domain.Question;
import com.my.domain.Userjob;
import com.my.service.JobService;
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
 * @date 2018/1/815:30
 */
//http://localhost:8080/job/createJob
@Controller
@RequestMapping(value = "job")
public class JobController {
            private final static Logger logger= (Logger) LoggerFactory.getLogger(JobController.class);
    @Autowired
    private JobService jobService;

    //**
    @RequestMapping(value = "/createJob",method= RequestMethod.POST)
    @ResponseBody
    public MyResult createJobController(Job job){
        /*
         /* @author zhangmeining
         * @date 2018/4/5 22:13
         * @Description: 创建作业,这个只是将题加入到题库，而且题库里的题是不会删除的
         * @param [job]
         * @return com.my.commom.pojo.MyResult
         */
        logger.info("JobController createJobController 入参是："+JsonUtils.objectToJson(job));
        MyResult myResult;
        try {
            myResult = jobService.createJob(job);
        }catch (Exception e){
            logger.error("createJobController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_CREATE_FAILURE_CODE,ErrorConstants.JOB_CREATE_FAILURE_MSG);
        }
        return myResult;
    }



    //**
    @RequestMapping(value = "/setQuestion",method= RequestMethod.POST)
    @ResponseBody
    public MyResult setQuestionController(Question question){
       /*
        /* @author zhangmeining
        * @date 2018/5/12 21:51
        * @Description: 这个是真正的出题的功能
        * @param [question]
        * @return com.my.commom.pojo.MyResult
        */
        logger.info("JobController setQuestionController 入参是："+JsonUtils.objectToJson(question));
        MyResult myResult;
        try {
            myResult = jobService.setQuestion(question);
            logger.info("返回的结果是："+JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("setQuestionController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.QUESTION_SET_USERJOB_FAILURE_CODE, ErrorConstants.QUESTION_SET_USERJOB_FAILURE_MSG);
        }
        return myResult;
    }



    //**
    @RequestMapping(value = "/getJobWithCourseAndQuestionType",method= RequestMethod.POST)
    @ResponseBody
    public MyResult getJobWithCourseAndQuestionTypeController(Long courseId,String questionType){
     /*
      /* @author zhangmeining
      * @date 2018/5/10 16:52
      * @Description: 教师端拉取题库中的题
      * @param [courseId, questionType]
      * @return com.my.commom.pojo.MyResult
      */
        logger.info("JobController getJobWithCourseAndQuestionType start");
        logger.info("JobController getJobWithCourseAndQuestionType 入参："+"courseId:"+courseId+"questionType"+questionType);
        MyResult myResult;
        try {
            myResult = jobService.getJobWithCourseAndQuestionType(courseId,questionType);
            logger.info("JobController getJobWithCourseAndQuestionType 返回结果是"+ JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("JobController getJobWithCourseAndQuestionType"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_GET_FAILURE_CODE,ErrorConstants.JOB_GET_FAILURE_MSG);
        }
        return myResult;
    }


    //**
    @RequestMapping(value = "/getCurrentJob",method= RequestMethod.POST)
    @ResponseBody
    public MyResult getCurrentJobController(Long courseId){
     /*
      /* @author zhangmeining
      * @date 2018/5/10 16:52
      * @Description: 得到当前的作业
      * @param [courseId, questionType]
      * @return com.my.commom.pojo.MyResult:返回的就是所有的当前作业
      */

        logger.info("JobController getCurrentJobController 入参："+"courseId:"+courseId);
        MyResult myResult;
        try {
            myResult = jobService.getCurrentJob(courseId);
            logger.info("JobController getCurrentJobController 返回结果是"+ JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("JobController getCurrentJobController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.NO_CURRENT_JOB_CODE, ErrorConstants.NO_CURRENT_JOB_MSG);
        }
        return myResult;
    }


    //**
    @RequestMapping(value = "/getCurrentJobsByQuesType",method= RequestMethod.POST)
    @ResponseBody
    public MyResult getCurrentJobsByQuesTypeWithTeaController(Long courseId,String questionType){
     /*
      /* @author zhangmeining
      * @date 2018/5/10 16:52
      * @Description: 得到当前作业指定题型的全部作业情况
      * @param [courseId, questionType]
      * @return com.my.commom.pojo.MyResult:返回的就是所有的当前作业，至于题型分类，app端子机判断
      */

        logger.info("JobController getCurrentJobsByQuesTypeWithTeaController 入参："+"courseId:"+courseId+"questionType"+questionType);
        MyResult myResult;
        try {
            myResult = jobService.getCurrentJobsByQuesType(courseId,questionType);
            logger.info("JobController getCurrentJobsByQuesTypeWithTeaController 返回结果是"+ JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("JobController getCurrentJobsByQuesTypeWithTeaController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_GET_FAILURE_CODE,ErrorConstants.JOB_GET_FAILURE_MSG);
        }
        return myResult;
    }




    @RequestMapping(value = "/doJob",method= RequestMethod.POST)
    @ResponseBody
    public MyResult doJobController(String userId,Long jobId,String answer ){
       /*
        /* @author zhangmeining
        * @date 2018/5/10 20:51
        * @Description: 做完作业会同步自己的成绩
        * @param [jobId, answer]
        * @return com.my.commom.pojo.MyResult
        */
        logger.info("doJobController start");
        MyResult myResult;
        try {
            myResult = jobService.doJob(userId,jobId,answer);
        }catch (Exception e){
            logger.error("doJobController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_SET_SCORE_FAILURE_CODE, ErrorConstants.JOB_SET_SCORE_FAILURE_MSG);
        }
        return myResult;
    }

    //
    @RequestMapping(value="/teacherCorrectJob", method= RequestMethod.POST)
    @ResponseBody
    public MyResult teacherCorrectJobController(Userjob userjob) {
      /*
       /* @author zhangmeining
       * @date 2018/5/3 9:57
       * @Description:老师批阅作业功能，传入userjob的信息。jobId，jobUser，score
       * @param []
       * @return com.my.commom.pojo.MyResult
       */
        logger.info("teacherCorrectJobController start");
        MyResult myResult;
        try {
            myResult = jobService.teacherCorrectJob(userjob);
        }catch (Exception e){
            logger.error("teacherCorrectJobController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_TEACHER_CORRECT_FAILURE_CODE, ErrorConstants.JOB_TEACHER_CORRECT_FAILURE_MSG);
        }
        return myResult;

    }



















    //*********************************************************************//

    //暂时没有
    @RequestMapping(value = "/deleteJob",method= RequestMethod.POST)
    @ResponseBody
    public MyResult deleteJobController(Long jobId){
        MyResult myResult;
        try {
            myResult = jobService.deleteJob(jobId);
        }catch (Exception e){
            logger.error("deleteJobController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_DELETE_FAILURE_CODE,ErrorConstants.JOB_DELETE_FAILURE_MSG);
        }
        return myResult;
    }


    @RequestMapping(value = "/showJobWithCourse",method= RequestMethod.POST)
    @ResponseBody
    public MyResult showJobWithCourseController(Long courseId){
        /*
         /* @author zhangmeining
         * @date 2018/4/17 19:33
         * @Description: 学生端进入一个课程的时候，展示他的所有作业的信息
         * @param [jobId]
         * @return com.my.commom.pojo.MyResult
         */
        logger.info("showJobWithCourseController start");
        MyResult myResult;
        try {
            myResult = jobService.getALLJobsAndScoreByCourseId(courseId);
        }catch (Exception e){
            logger.error("showJobWithCourseController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_SHOW_FAILURE_CODE,ErrorConstants.JOB_SHOW_FAILURE_MSG);
        }
        return myResult;
    }


//    //**
//    @RequestMapping(value = "/getJobWithUserCourseAndQuestionType",method= RequestMethod.POST)
//    @ResponseBody
//    public MyResult getJobWithUserAndCourseAndQuestionTypeController(Long courseId,String questionType,String userId){
//  /*
//   /* @author zhangmeining
//   * @date 2018/5/10 19:34
//   * @Description: 学生端那边某个学生的指定课程下指定题型下的题
//   * @param [courseId, questionType]
//   * @return com.my.commom.pojo.MyResult
//   */
//        logger.info("JobController getJobWithUserAndCourseAndQuestionTypeController start");
//        logger.info("JobController getJobWithUserAndCourseAndQuestionTypeController 入参："+"courseId:"+courseId+"questionType"+questionType+"userId"+userId);
//        MyResult myResult;
//        try {
//            myResult = jobService.getJobWithCourseAndQuestionType(courseId,questionType);
//            logger.info("JobController getJobWithCourseAndQuestionType 返回结果是"+ JsonUtils.objectToJson(myResult));
//        }catch (Exception e){
//            logger.error("JobController getJobWithCourseAndQuestionType"+ ExceptionUtil.getStackTrace(e));
//            return MyResult.build(ErrorConstants.JOB_GET_FAILURE_CODE,ErrorConstants.JOB_GET_FAILURE_MSG);
//        }
//        return myResult;
//    }




    //**
    @RequestMapping(value = "/showJobWithStu",method= RequestMethod.POST)
    @ResponseBody
    public MyResult showJobWithStuController(String jobUser ){
       /*
        /* @author zhangmeining
        * @date 2018/4/17 23:32
        * @Description:
        * @param [courseId]
        * @return com.my.commom.pojo.MyResult
        */
        logger.info("showJobWithStuController start");
        MyResult myResult;
        try {
            myResult = jobService.getALLJobsAndScoreByCourseIdWithStu(jobUser);
        }catch (Exception e){
            logger.error("showJobWithStuController"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.JOB_SHOW_FAILURE_CODE,ErrorConstants.JOB_SHOW_FAILURE_MSG);
        }
        return myResult;
    }




}
