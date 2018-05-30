package com.my.dao.impl;

import ch.qos.logback.classic.Logger;
import com.my.dao.CourseDao;
import com.my.domain.*;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/217:11
 */



  @Repository
public class CourseDaoImpl  implements CourseDao{


    private final static Logger logger = (Logger) LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private SqlSessionFactory sqlSessionFactory;
    /*
            /* @author zhangmeining
            * @date 2018/1/7 17:03
            * @Description: 创建课程传入创建者
            * @param [course]
            * @return java.lang.String 返回的是创建的课程号
            */
    public int  createCourse(Course course) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.insert("CourseDao.insertCourse",course);
        sqlSession.close();
        return change;

    }




    /*
         /* @author zhangmeining
         * @date 2018/1/2 17:12
         * @Description: 根据课程号删除课程
         * @param [courseId]
         * @return int
         */
    public int deleteCourse(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.delete("CourseDao.deleteCourse",courseId);
        sqlSession.close();
        return change;
    }



    /*
        /* @author zhangmeining
        * @date 2018/1/2 17:12
        * @Description: 根据用户号得到这个同学参加的所有的课程
        * @param [courseUser]
        * @return java.util.List<com.my.domain.Course>
        */
    public List<Course> getALLCourses(String courseUserId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Course> courses=sqlSession.selectList("CourseDao.getAllCourses",courseUserId);
        sqlSession.close();
        return courses;
    }

    public Course getCourse(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        Course course=sqlSession.selectOne("CourseDao.getCourse",courseId);
        sqlSession.close();
        return course;
    }

    public List<Course> getALLCoursesCreator(String userId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Course> courses=sqlSession.selectList("CourseDao.getAllCoursesCreator",userId);
        sqlSession.close();
        return courses;
    }

    public int joinCourse(UserCourse userCourse) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.insert("CourseDao.joinCourse",userCourse);
        sqlSession.close();
        return change;
    }



    /*
       /* @author zhangmeining
       * @date 2018/1/9 14:55
       * @Description: 根据课程id查找加入这个课程的所有学生，在userjob中
       * @param [courseId]
       * @return java.util.List<com.my.domain.User>
       */
    public List<User> getAllUsersByCourseId(Long  courseId) {

        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<User> users=sqlSession.selectList("CourseDao.getAllUsersByCourseId",courseId);
        sqlSession.close();
        return users;
    }



    /*
        /* @author zhangmeining
        * @date 2018/1/9 16:21
        * @Description: 退出课程，当退出课程的时候，相关的表有（usercourse 和作业的表userjob）当然也要删除
        * @param [courseId]
        * @return int
        */
    public int exitCourse(UserCourse userCourse) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.delete("CourseDao.exitCourse",userCourse);
        sqlSession.close();
        return change;
    }

    public int deleteUserCourse(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int change;
        try {
          change = sqlSession.delete("CourseDao.deleteUserCourse", courseId);
        }finally {
            sqlSession.close();
        }
        return change;
    }

    public int deleteUserJobByCourseUser(Userjob userjob) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int change;
        try {
            change = sqlSession.delete("CourseDao.deleteUserJobByCourseIdAndUserId", userjob);
        }finally {
            sqlSession.close();
        }
        return change;
    }

    public List<User> getAllStuByCourseId(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<User> users=new ArrayList<User>();
       List<UserCourse> userCourses=null;
        try {
            userCourses = sqlSession.selectList("CourseDao.getAllStuByCourseId", courseId);
            if(userCourses.size()>0){
                for(int i=0;i<userCourses.size();i++){
                    User user=sqlSession.selectOne("user.getUserById",userCourses.get(i).getUserId());
                    users.add(user);
                }

            }
        }finally {
            sqlSession.close();
        }
        return users;
    }

    public List<UserCourse> getOneStuAllCourses(String userId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<UserCourse> userCourses=null;
        try {
            userCourses = sqlSession.selectList("CourseDao.getOneStuAllCourses", userId);
        }finally {
            sqlSession.close();
        }
        return userCourses;
    }

    public Course selectCourseByCourseId(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        Course course=null;
        try {
            course = sqlSession.selectOne("CourseDao.selectCourseByCourseId", courseId);
        }finally {
            sqlSession.close();
        }
        return course;
    }

    public int getCourseJobNumbers(Long courseId) {

        SqlSession sqlSession=sqlSessionFactory.openSession();
        int numbers;
        try {
            numbers = sqlSession.selectOne("CourseDao.getCourseJobNumbers", courseId);
        }finally {
            sqlSession.close();
        }
        return numbers;
    }

    public List<String> getCourseNumberName(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<String> numberNames=null;
        try {
            numberNames = sqlSession.selectList("CourseDao.getCourseNumberName", courseId);
        }finally {
            sqlSession.close();
        }
        return numberNames;
    }

    public List<Course> getCourseByCourseName(String courseName) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
       List<Course>  courses=new ArrayList<Course>();
        try {
            courses = sqlSession.selectList("CourseDao.getCourseByCourseName", courseName);
        }finally {
            sqlSession.close();
        }
        return courses;
    }

}
