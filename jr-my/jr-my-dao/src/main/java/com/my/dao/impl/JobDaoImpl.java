package com.my.dao.impl;

import ch.qos.logback.classic.Logger;
import com.my.dao.JobDao;
import com.my.domain.Job;
import com.my.domain.NumberJob;
import com.my.domain.Question;
import com.my.domain.Userjob;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/815:08
 */
@Repository
public class JobDaoImpl implements JobDao {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(UserDaoImpl.class);
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    public int createJob(Job job) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.insert("JobDao.insertJob",job);
        sqlSession.close();
        return change;
    }

    public int setQuestion(NumberJob numberJob) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.insert("JobDao.insertNumberJob",numberJob);
        sqlSession.close();
        return change;
    }

    public int deleteJob(Long jobId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.delete("JobDao.deleteJob",jobId);
        sqlSession.close();
        return change;

    }

    public Job getJob(Long jobId) {

        SqlSession sqlSession=sqlSessionFactory.openSession();
        Job job=sqlSession.selectOne("JobDao.getJob",jobId);
        sqlSession.close();
        return job;
    }

    public int createUserJob(Userjob userjob) {

        SqlSession sqlSession=sqlSessionFactory.openSession();
        int change=sqlSession.insert("JobDao.insertUserJob",userjob);
        sqlSession.close();
        return change;
    }



    /*
       /* @author zhangmeining
       * @date 2018/1/9 15:57
       * @Description: 根据课程号获得这个课程的所有作业
       * @param [courseId]
       * @return java.util.List<com.my.domain.Job>
       */
    public List<Job> getALLJobsByCourseId(Long courseId) {

        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Job> jobs=sqlSession.selectList("JobDao.getALLJobByCourseId",courseId);
        sqlSession.close();
        return jobs;
    }

    public int deleteUserJob(Long jobId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.delete("JobDao.deleteUserJob",jobId);
        sqlSession.close();
        return change;
    }

    public Userjob getJobSoreByJobId(Long jobId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Userjob> userjobs=sqlSession.selectList("UserJobDao.getJobSoreByJobId",jobId);
        sqlSession.close();
        return userjobs.get(0);
    }

    public List<Userjob> getJobSoreByJobUser(String jobUser) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Userjob> userjobs=sqlSession.selectList("UserJobDao.getJobSoreByJobUser",jobUser);
        sqlSession.close();
        return userjobs;
    }

    public List<Userjob> getJobSoreByJobUserCourse(String jobUser, Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Userjob> userjobs;
        Map<String,Object> parameters=new java.util.HashMap<String,Object>();
        parameters.put("0",jobUser);
        parameters.put("1",courseId);
        try {
            userjobs = sqlSession.selectList("UserJobDao.getJobSoreByJobUserCourse", parameters);
            return userjobs;
        }finally {
            sqlSession.close();
        }

    }

    public int deleteJobByCourseId(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int change;
        try {
            change= sqlSession.delete("JobDao.deleteJobByCourseId", courseId);
        }finally {
            sqlSession.close();
        }
        return change;
    }

    public List<Job> getJobWithCourseAndQuestionTyp(Long courseId, String questionType) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Job> jobs=null;
        Job job=new Job();
        job.setCourseId(courseId);
        job.setQuestionType(questionType);
        try {
           jobs= sqlSession.selectList("JobDao.getJobWithCourseAndQuestionTyp", job);
        }finally {
            sqlSession.close();
        }
        return jobs;
    }

    public int doJob(String userId,Long jobId, String myAnswer,int score) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        Userjob userjob=new Userjob();
        userjob.setJobUser(userId);
        userjob.setJobId(jobId);
        userjob.setMyAnswer(myAnswer);
        userjob.setScore(score);
        int change=-1;
        try {
            change= sqlSession.update("JobDao.doJob", userjob);
        }finally {
            sqlSession.close();
        }
        return change;
    }

    public int deleteNumberJob(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int change=-1;
        try {
             change=sqlSession.delete("JobDao.deleteNumberJobByCourseId",courseId);
        }finally {
            sqlSession.close();
        }
        return change;
    }

    public List<NumberJob> getCurrentJobIdWithTea(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<NumberJob> numberJobs=null;
       ;
        try {
            numberJobs= sqlSession.selectList("JobDao.getCurrentJobIdWithTea", courseId);
        }finally {
            sqlSession.close();
        }
        return numberJobs;
    }

    public List<Userjob> getJobSoreByJobUserCourseQuesType(String jobUser, Long courseId, String questionType) {

        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Userjob> userjobs;
        Map<String,Object> parameters=new java.util.HashMap<String,Object>();
        parameters.put("0",jobUser);
        parameters.put("1",courseId);
        parameters.put("2",questionType);
        try {
            userjobs = sqlSession.selectList("UserJobDao.getJobSoreByJobUserCourseQuesType", parameters);
            return userjobs;
        }finally {
            sqlSession.close();
        }
    }

    public Job getJobByJobIdQuesType(Long jobId, String questionType) {
        Job job=new Job();
        job.setJobId(jobId);
        job.setQuestionType(questionType);
        SqlSession sqlSession=sqlSessionFactory.openSession();
        Job job1;
        try {
            job1= sqlSession.selectOne("JobDao.getJobByJobIdQuesType", job);
        }finally {
            sqlSession.close();
        }
        return job1;
    }

    public String getCurrentNumberName(Long courseId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        String name=null;
        ;
        try {
            name= sqlSession.selectOne("JobDao.getCurrentNumberName", courseId);
        }finally {
            sqlSession.close();
        }
        return name;
    }

    public int  teacherCorrectJob(Userjob userjob) {
        SqlSession sqlSession=sqlSessionFactory.openSession();

        int change=-1;
        try {
            change= sqlSession.update("JobDao.teacherCorrect", userjob);
        }finally {
            sqlSession.close();
        }
        return change;
    }


}
