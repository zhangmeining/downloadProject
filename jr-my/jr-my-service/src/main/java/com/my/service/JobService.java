package com.my.service;

import com.my.commom.pojo.MyResult;
import com.my.domain.Job;
import com.my.domain.Question;
import com.my.domain.Userjob;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/815:23
 */
public interface JobService {
    public MyResult createJob(Job job);


//出题的功能
    public MyResult setQuestion(Question question);


    public MyResult deleteJob(Long jobId);
    /*
  /* @author zhangmeining
  * @date 2018/4/17 20:17
  * @Description: 教师端查看某课程所有作业的信息
  * @param
  * @return
  */
    public MyResult  getALLJobsAndScoreByCourseId(Long courseId);

    //学生端显示的自己的作业信息
    //先是在userJob里查分数和jobId,然后到job表里查询全部信息
    public MyResult  getALLJobsAndScoreByCourseIdWithStu(String jobUser);

    //教师端拉取题库的操作，返回的是所有的题
    public MyResult  getJobWithCourseAndQuestionType(Long courseId,String questionType);


    //学生做作业
    public MyResult  doJob(String userId,Long jobId,String answer );

//老师批阅作业
    public MyResult  teacherCorrectJob(Userjob userjob );

    //教师端查看当前作业指定题型
    public MyResult  getCurrentJobsByQuesType(Long courseId ,String questionType);

    //教师端查看当前作业指定题型
    public MyResult  getCurrentJob(Long courseId );
}
