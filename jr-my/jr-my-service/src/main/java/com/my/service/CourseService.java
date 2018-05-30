package com.my.service;

import com.my.commom.pojo.MyResult;
import com.my.domain.Course;
import com.my.domain.UserCourse;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/715:29
 */
public interface CourseService {
    public MyResult createCourse(Course course);
    public  MyResult  deleteCourse(Long courseId);
    public MyResult getALLCourses(String courseUserId);
    public MyResult getALLCoursesCreator(String userId);
    public MyResult joinCourse(UserCourse userCourse);
    public  MyResult exitCourse(UserCourse userCourse);


    //获得某个课程的总分，平均分等
    public  MyResult  countScore(String userId,Long courseId);

    //获得某个课程的所有成员的所有作业的所有成绩
    public  MyResult  getAllStuByCourseId(Long courseId);

    //获得某个学生参加的所有的课程
    public  MyResult  getOneStuAllCourses(String userId);


    //获得某个课程下指定题型的分数相关
    public  MyResult  countScoreWithTeaByQuestionType(String userId,Long courseId,String questionType);

}
