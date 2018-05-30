package com.my.dao;

import com.my.domain.Job;
import com.my.domain.NumberJob;
import com.my.domain.Userjob;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/723:25
 */
public interface JobDao {
 public int createJob(Job job);
//出题
 public int setQuestion(NumberJob numberJob);

 public int deleteJob(Long jobId);
 public  Job getJob(Long jobId);
 public int createUserJob(Userjob userjob );
public List<Job> getALLJobsByCourseId(Long courseId);
 public int deleteUserJob(Long jobId);
 //根据jobId来获取这个作业的分数
 public  Userjob getJobSoreByJobId(Long jobId);
 //根据JobUser(做作业的)来获取这个作业的分数
 public  List<Userjob> getJobSoreByJobUser(String jobUser);

 //根据学号和课程号获得这个学号下这个课程下的所有作业情况。
 public  List<Userjob> getJobSoreByJobUserCourse(String jobUser,Long courseId);


 public int deleteJobByCourseId(Long courseId);

//教师端拉取题库中的题
 public List<Job> getJobWithCourseAndQuestionTyp(Long courseId,String questionType);

 //
 public int doJob(String userId,Long jobId, String answer ,int score);


 //删除一个课程的所有次作业。删除numberjob表
 public int deleteNumberJob(Long courseId);


 //
 public List<NumberJob> getCurrentJobIdWithTea(Long courseId);


 //根据学号和课程号，和指定的题型获得这个学号下这个课程下的所有作业情况。
 public  List<Userjob> getJobSoreByJobUserCourseQuesType(String jobUser,Long courseId,String questionType);



 public  Job getJobByJobIdQuesType(Long jobId,String questionType);

 public String getCurrentNumberName(Long courseId);

 //老师批阅作业，主要是插入分数
 public int  teacherCorrectJob(Userjob userjob);
}
