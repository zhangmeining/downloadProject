package com.my.dao;

import com.my.domain.*;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/217:05
 */
public interface CourseDao {
 public  int   createCourse(Course course);
 public  int  deleteCourse(Long courseId);
 public List<Course> getALLCourses(String courseUserId);
 public Course getCourse(Long courseId);
 public List<Course> getALLCoursesCreator(String UserId);
 public int joinCourse(UserCourse userCourse);
 public  List<User> getAllUsersByCourseId(Long courseId);
 public int exitCourse(UserCourse userCourse);


 //根据课程号删除userCourse表中的关联此课程号的用户信息
 public int deleteUserCourse(Long courseId);

 //根据课程号和用户号删除userCourse表中的关联此课程号的用户信息
 public int deleteUserJobByCourseUser(Userjob userjob);


 //获得某个课程的所有成员
 public List<User> getAllStuByCourseId(Long courseId);


 //获得某个学生参加的所有课程
 public List<UserCourse> getOneStuAllCourses(String userId);


 public Course selectCourseByCourseId(Long courseId);

//求这个课程的总作业次数
 public int getCourseJobNumbers(Long courseId);

 //得到这个课程的所有作业次数名字
 public List<String> getCourseNumberName(Long courseId);


 public List<Course> getCourseByCourseName(String  courseName);
}
