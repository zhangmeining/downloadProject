package com.my.service.impl;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.JsonUtils;
import com.my.dao.CourseDao;
import com.my.dao.JobDao;
import com.my.dao.UserDao;
import com.my.domain.*;
import com.my.service.CourseService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/715:30
 */
@Service
public class CourseServiceImpl implements CourseService {


    @Autowired
    private CourseDao courseDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobDao jobDao;

    private final static Logger logger = (Logger) LoggerFactory.getLogger(CourseServiceImpl.class);

    public MyResult createCourse(Course course) {
        User user1 = null;
        user1 = userDao.getUserById(course.getCourseCreator());
//如果没有此用户id
        if (user1 == null) {
            return MyResult.build(ErrorConstants.USER_NOTEXISTS_CODE, ErrorConstants.USER_NOTEXISTS_MSG);
        }
        //查看是否有这个课程，这里不知道是否客户端定义的是这样
        List<Course> courses= courseDao.getCourseByCourseName(course.getCourseName());
        if(courses.size()>0){
            return MyResult.build(ErrorConstants.COURSENAME_HAVED_CODE, ErrorConstants.COURSENAME_HAVED__MSG);
        }
        int change = courseDao.createCourse(course);
        //###返回的主键的值可以在service层拿到
//        if (change >= 1) {
//
//        } else {
//            return MyResult.build(ErrorConstants.CREATE_COURSE_FAILURE_CODE, ErrorConstants.CREATE_COURSE_FAILURE_MSG);
//        }
        if(change<0){
            return MyResult.build(ErrorConstants.CREATE_COURSE_FAILURE_CODE, ErrorConstants.CREATE_COURSE_FAILURE_MSG);
        }
//        return MyResult.ok("courseId:"+course.getCourseId());
//        course.setCourseCreator(null);
//        course.setCourseName(null);
        return MyResult.ok(JsonUtils.objectToJson(course));

    }

    public MyResult deleteCourse(Long courseId) {
        /*
        * 此处关联的好几个删除操作，关联的表有：course表（课程），userCourse表（学员加入的表），job表（这个课程的作业表），userJob表（这个作业关联的学员表）
        *这里没有使用外键的原因所以删除的时候才会不用管关联的是否删除即删除的顺序
         *  */

        //先判断此课程存在？
        Course course = courseDao.getCourse(courseId);
        if (course == null) {
            MyResult.build(ErrorConstants.NOTEXISTS_COURSE_CODE, ErrorConstants.NOTEXISTS_COURSE_MSG);
        }

        //
        //3.删除userJob表
        //#.先获取这个课程的所有作业，
        List<Job> jobs = jobDao.getALLJobsByCourseId(courseId);
        //#.删除这些所有作业
        for (int i = 0; i < jobs.size(); i++) {
            int change3 = jobDao.deleteUserJob(jobs.get(i).getJobId());
            if (change3 < 1) {
                return MyResult.build(ErrorConstants.DELETE_COURSE_USERJOB_FAILURE_CODE, ErrorConstants.DELETE_COURSE_USERJOB_FAILURE_MSG);
            }
        }
      //  1.删除userCourse表,,处理若是这个userCourse表没有数据呢，删除返回的change是多少
        int change1 = courseDao.deleteUserCourse(courseId);
        if (change1 < 0) {
            throw new RuntimeException();
//            return MyResult.build(ErrorConstants.DELETE_COURSE_USERCOURSE_FAILURE_CODE, ErrorConstants.DELETE_COURSE_USERCOURSE_FAILURE_MSG);
        }else {
//            //2.删除job表
//            int change2 = jobDao.deleteJobByCourseId(courseId);
//            if (change2 < 0) {
//                return MyResult.build(ErrorConstants.DELETE_COURSE_JOB_FAILURE_CODE, ErrorConstants.DELETE_COURSE_JOB_FAILURE_MSG);
//            }else{
//                //0.删除course表
//                    int change = courseDao.deleteCourse(courseId);
//                    if(change<0){
//                        return MyResult.build(ErrorConstants.DELETE_COURSE_FAILURE_CODE, ErrorConstants.DELETE_COURSE_FAILURE_MSG);
//                    }
//                    return MyResult.ok(true);
//            }
            //删除numberjob表中的这个课程的所有次作业
            int change = jobDao.deleteNumberJob(courseId);
            if (change < 0) {
                throw new RuntimeException();
            } else {
                // 0.删除course表
                int change11 = courseDao.deleteCourse(courseId);
                if (change11 < 0) {
                    throw new RuntimeException();
//                    return MyResult.build(ErrorConstants.DELETE_COURSE_FAILURE_CODE, ErrorConstants.DELETE_COURSE_FAILURE_MSG);
//                    throw new RuntimeException();
                }
                return MyResult.ok(true);
            }
        }
    }

    public MyResult getALLCourses(String courseUserId) {
        User user1 = null;
        user1 = userDao.getUserById(courseUserId);
        //如果没有此用户id
        if (user1 == null) {
            return MyResult.build(ErrorConstants.USER_NOTEXISTS_CODE, ErrorConstants.USER_NOTEXISTS_MSG);
        }
        List<Course> courses = courseDao.getALLCourses(courseUserId);
        return MyResult.ok(courses);
    }

    public MyResult getALLCoursesCreator(String userId) {
        User user1 = null;
        user1 = userDao.getUserById(userId);
        //如果没有此用户id
        if (user1 == null) {
            return MyResult.build(ErrorConstants.USER_NOTEXISTS_CODE, ErrorConstants.USER_NOTEXISTS_MSG);
        }
        List<Course> courses = courseDao.getALLCoursesCreator(userId);
        return MyResult.ok(courses);
    }

    public MyResult joinCourse(UserCourse userCourse) {
        //1.先判断是否有这个课程号
        Course course = courseDao.getCourse(userCourse.getCourseId());
        //如果没有此课程id
        if (course == null) {
            return MyResult.build(ErrorConstants.NOTEXISTS_COURSE_CODE, ErrorConstants.NOTEXISTS_COURSE_MSG);
        }
        //1.更新usercourse表
        int change = courseDao.joinCourse(userCourse);

        if (change >= 1) {

        } else {
            return MyResult.build(ErrorConstants.JOIN_COURSE_FAILURE_CODE, ErrorConstants.JOIN_COURSE_FAILURE_MSG);
        }
        //2.同步作业情况
        //当某个学生加入一个课程后就要插入他的作业情况。这个在创建作业的时候也进行这两个操作来保持，给学生获得作业的情况。
        //先根据课程号获得，这个课程的所有作业。
        List<Job> jobs = jobDao.getALLJobsByCourseId(userCourse.getCourseId());
        //将这些所有的作业插入userjob表中去。
        Userjob userjob = new Userjob();
        userjob.setJobUser(userCourse.getUserId());
        for (int i = 0; i < jobs.size(); i++) {
            userjob.setJobId(jobs.get(i).getJobId());
            jobDao.createUserJob(userjob);
        }
        return MyResult.ok(true);
    }


    /*
       /* @author zhangmeining
       * @date 2018/1/9 16:56
       * @Description: 某学生退出课程,当学生退出课程，这门课想关的课程也要删除
       * @param [courseId]
       * @return com.my.commom.pojo.MyResult
       */
    public MyResult exitCourse(UserCourse userCourse) {



        //当退出成功后就在userJob表中删除这个同学关于这个作业的相关信息。
        //1.拿出这个课程所有的作业好
        List<Job> jobs = jobDao.getALLJobsByCourseId(userCourse.getCourseId());
        //2.从表userjob表中删除这个学生关于这个课程的信息
        for (int i = 0; i < jobs.size(); i++) {
            Userjob userjob=new Userjob();
            userjob.setJobId(jobs.get(i).getJobId());
            userjob.setJobUser(userCourse.getUserId());
            int c = courseDao.deleteUserJobByCourseUser(userjob);
            if(c<1){
                return MyResult.build(ErrorConstants.EXIT_COURSE_USERJOB_FAILURE_CODE, ErrorConstants.EXIT_COURSE_USERJOB_FAILURE_MSG);
            }
        }
        //1.删除usercourse表信息
        int change = courseDao.exitCourse(userCourse);
        if (change< 1) {

            return MyResult.build(ErrorConstants.EXIT_COURSE_FAILURE_CODE, ErrorConstants.EXITCOURSE_FAILURE_MSG);
        } else {
            return MyResult.ok(true);
        }

    }

    public MyResult countScore(String userId,Long courseId) {
//        //1.获得某个课程的所有成员
//
//        List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
//        List<User> users = courseDao.getAllUsersByCourseId(courseId);
//        logger.info("这个课程的所有学生有" + JsonUtils.objectToJson(users));
//        //2.获得这个课程的某个成员的所有作业和对所有成绩精进行求和，及作业分数，根据数据库来推算应该先是求得这个成员的作业，再看作业是否是这个课程的
//        //也可以是先是获得这个课程的所有作业，在哪这个作业的成员分数
//        for (int i = 0; i < users.size(); i++) {
//            String userId = users.get(i).getUserId();
//            Map<String, Object> objects = new HashMap<String, Object>();
//            objects.put("userId", userId);
//
//            //得到这个学生这个课程的所有作业。
//            List<Userjob> userJobs = jobDao.getJobSoreByJobUserCourse(users.get(i).getUserId(), courseId);
//            logger.info("这个学生这个课程的所有作业" + JsonUtils.objectToJson(userJobs));
//            //这里应该再拿一次作业的title，移动端展示。
//
//            List<JobAndScore> jobAndScores = new ArrayList<JobAndScore>();
//            int score = 0;
//            for (int j = 0; j < userJobs.size(); j++) {
//                Userjob userjob = userJobs.get(i);
//                int score1 = userjob.getScore();
//                if (score1 == -1) {
//                    score1 = 0;
//                }
//                score = score + score1;
//                Job job = jobDao.getJob(userjob.getJobId());
//                JobAndScore jobAndScore = new JobAndScore();
//                jobAndScore.setJobUser(userId);
//                jobAndScore.setScore(userjob.getScore());
////                jobAndScore.setCourseId(job.getCourseId());
////                jobAndScore.setJobCreator(job.getJobCreator());
////                jobAndScore.setTimeLimit(job.getTimeLimit());
////                jobAndScore.setIllustration(job.getIllustration());
////                jobAndScore.setContent(job.getContent());
//                jobAndScore.setTitle(job.getTitle());
//                jobAndScore.setJobId(job.getJobId());
//                jobAndScores.add(jobAndScore);
//
//            }
//            logger.info("这个学生这个课程的所有jobAndScores" + JsonUtils.objectToJson(jobAndScores));
//            objects.put("jobs", jobAndScores);
//            objects.put("score", score);
//            logger.info("这个学生这个课程的情况是：" + JsonUtils.objectToJson(objects));
//            lists.add(objects);
//        }
//        logger.info("所有学生的这个课程的情况end：" + JsonUtils.objectToJson(lists));
//        logger.info("MyResult" + JsonUtils.objectToJson(MyResult.ok(lists)));
//        if (lists.size() > 0) {
//            return MyResult.ok(lists);
//        } else {
//            return MyResult.build(ErrorConstants.JOB_COUNT_FAILURE_CODE, ErrorConstants.JOB_COUNT_FAILURE_MSG);
//        }

//
        //获得这个同学课程下的所有作业，然后求出总次数求出平均分
       List<Userjob> userjobs= jobDao.getJobSoreByJobUserCourse(userId,courseId);
       //求出这些作业的所有固定分数是多少
        int fixedScore=0;
        for(int i=0;i<userjobs.size();i++){
          Job job=  jobDao.getJob(userjobs.get(i).getJobId());
          int scoreV=job.getScoreValue();
            fixedScore=fixedScore+scoreV;
        }
        int score = 0;
        for (int i = 0; i < userjobs.size(); i++) {
            score = score + userjobs.get(i).getScore();
        }
        //求出这个课程的总次数
        int n = courseDao.getCourseJobNumbers(courseId);
        int average = score / n;
        Score score1 = new Score();
        score1.setAverageScore(average);
        score1.setNumbers(n);
        score1.setScore(score);
        score1.setFixedScore(fixedScore);
        return MyResult.ok(score1);

    }

    public MyResult getAllStuByCourseId(Long courseId) {
        List<User> users= courseDao.getAllStuByCourseId(courseId);
        if(users.size()<=0){
            return MyResult.build(ErrorConstants.GET_STU_BYCOURSEID_FAILURE_CODE, ErrorConstants.GET_STU_BYCOURSEID_FAILURE_MSG);
        }else{
            return MyResult.ok(users);
        }

    }

    public MyResult getOneStuAllCourses(String userId) {
        List<Course> courses=new ArrayList<Course>();
        List<UserCourse> userCourses=courseDao.getOneStuAllCourses(userId);
        if(userCourses.size()<=0){
            return  MyResult.build(ErrorConstants.STU_NO_COURSE_CODE, ErrorConstants.STU_NO_COURSE_MSG);
        }
        for(int i=0;i<userCourses.size();i++){
         Course course=courseDao.selectCourseByCourseId(userCourses.get(i).getCourseId());
         courses.add(course);
        }
        return MyResult.ok(courses);
    }

    public MyResult countScoreWithTeaByQuestionType(String userId, Long courseId,String questionType) {
        //1、先获得这个课程下的yoghurt所有作业，指定题型的
        List<Userjob> userjobs= jobDao.getJobSoreByJobUserCourseQuesType(userId,courseId,questionType);
        //2.然后，拿出作业次数名字
        List<String> numberNames=courseDao.getCourseNumberName(courseId);
        List<NumberAndScore> numberAndScores=new ArrayList<NumberAndScore>();

        for(int i=0;i<numberNames.size();i++){
            NumberAndScore numberAndScore=new NumberAndScore();
          String numberName=  numberNames.get(i);
            numberAndScore.setNumberName(numberName);
            int score=0;
            for(int j=0;j<userjobs.size();j++){
                if(userjobs.get(j).getNumberName().equals(numberName)){
                    score=score+userjobs.get(j).getScore();
                }
            }
            numberAndScore.setScore(score);
            numberAndScores.add(numberAndScore);
        }

        return MyResult.ok(numberAndScores);
    }


}
