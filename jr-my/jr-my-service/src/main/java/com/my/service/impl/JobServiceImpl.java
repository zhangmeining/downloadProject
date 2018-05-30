package com.my.service.impl;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.ExceptionUtil;
import com.my.dao.CourseDao;
import com.my.dao.JobDao;
import com.my.dao.UserDao;
import com.my.domain.*;
import com.my.service.JobService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/815:24
 */
@Service
public class JobServiceImpl implements JobService {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(JobServiceImpl.class);
    @Autowired
    private JobDao jobDao;

    @Autowired
    private UserDao userDao;


    @Autowired
    private CourseDao courseDao;

    /*
         /* @author zhangmeining
         * @date 2018/1/9 14:09
         * @Description: 一般创建一个作业是对应的是一个课程来讲的，当创建了一个作业后肯定是还要插入userjob表数据就是，哪些同学需要有这份作业
         * @param [job]
         * @return com.my.commom.pojo.MyResult
         */
    public MyResult createJob(Job job) {


        User user1 = null;
        user1 = userDao.getUserById(job.getJobCreator());
        //如果没有此用户id
        if (user1 == null) {
            return MyResult.build(ErrorConstants.USER_NOTEXISTS_CODE, ErrorConstants.USER_NOTEXISTS_MSG);
        }
        //创建作业时对应的是课程来讲的
        int change = jobDao.createJob(job);

//        if (change >= 1) {
//            try {
//                //同步数据到userjob表,
//                Userjob userjob = new Userjob();
//                //job.getJobId()返回的是自增的主键值
//                userjob.setJobId(job.getJobId());
//                //获取这个课程写的所有的学员。并将数据插入到userjob对应的表中，在加入一个课程的时候也插入数据
//                List<User> users = courseDao.getAllUsersByCourseId(job.getCourseId());
//                for (int i = 0; i < users.size(); i++) {
//                    userjob.setJobUser(users.get(i).getUserId());
//                    //这里对作业的分数做初始化处理，未做是-1
//                    userjob.setScore(-1);
//                    jobDao.createUserJob(userjob);
//                }
//            } catch (Exception e) {
//                //感觉这里若是出错了应该进行回滚事务
//                logger.error(" createJob中的插入数据到相关表userjob中的错误" + ExceptionUtil.getStackTrace(e));
//            }
//            return MyResult.ok(true);
//        } else {
//            return MyResult.build(ErrorConstants.JOB_CREATE_FAILURE_CODE, ErrorConstants.JOB_CREATE_FAILURE_MSG);
//        }


        if (change > 0) {
            return MyResult.ok(true);
        } else {
            return MyResult.build(ErrorConstants.JOB_CREATE_FAILURE_CODE, ErrorConstants.JOB_CREATE_FAILURE_MSG);
        }


    }


    /*
            /* @author zhangmeining
            * @date 2018/1/9 14:11
            * @Description: 只限于教师删除作业，学生那边自动删除当删除一份作业时是不是还要删除，userjob表中的相关数据删除加入此课程的所有同学的这个作业.
            * @param [jobId]
            * @return com.my.commom.pojo.MyResult
            */
    public MyResult deleteJob(Long jobId) {

        //1.先判断是否有这个作业号
        Job job = jobDao.getJob(jobId);
        //如果没有此作业id
        if (job == null) {
            return MyResult.build(ErrorConstants.JOB_DELETE_FAILURE_CODE, ErrorConstants.JOB_DELETE_FAILURE_MSG);
        }

        int change = jobDao.deleteJob(jobId);
        if (change >= 1) {
            //删除userjob表中的数据
            int c = jobDao.deleteUserJob(jobId);
            if (c >= 1) {
                return MyResult.ok(true);
            } else {
                return MyResult.build(ErrorConstants.JOB_DELETE_FAILURE_CODE, ErrorConstants.JOB_DELETE_FAILURE_MSG);
            }

        }
        return MyResult.build(ErrorConstants.JOB_DELETE_FAILURE_CODE, ErrorConstants.JOB_DELETE_FAILURE_MSG);
    }

    public MyResult getALLJobsAndScoreByCourseId(Long courseId) {
        //在这里做一次组装，将作业信息和分数的信息进行组装
        List<Job> jobs = jobDao.getALLJobsByCourseId(courseId);
        List<JobAndScore> jobAndScores = new ArrayList<JobAndScore>();
        for (int i = 0; i < jobs.size(); i++) {
            Job job = jobs.get(i);
            Userjob userjob = jobDao.getJobSoreByJobId(job.getJobId());
            JobAndScore jobAndScore = new JobAndScore();
            jobAndScore.setJobId(job.getJobId());
            jobAndScore.setTitle(job.getTitle());
            jobAndScore.setContent(job.getContent());
            jobAndScore.setIllustration(job.getIllustration());
            jobAndScore.setTimeLimit(job.getTimeLimit());
            jobAndScore.setJobCreator(job.getJobCreator());
            jobAndScore.setCourseId(job.getCourseId());
            jobAndScore.setScore(userjob.getScore());
            jobAndScore.setJobUser(userjob.getJobUser());
            jobAndScores.add(jobAndScore);
        }
        return MyResult.ok(jobAndScores);
    }

    public MyResult getALLJobsAndScoreByCourseIdWithStu(String jobUser) {
        List<Userjob> userjobs = jobDao.getJobSoreByJobUser(jobUser);
        List<JobAndScore> jobAndScores = new ArrayList<JobAndScore>();
        for (int i = 0; i < userjobs.size(); i++) {
            Userjob userjob = userjobs.get(i);
            Job job = jobDao.getJob(userjob.getJobId());
            JobAndScore jobAndScore = new JobAndScore();
            jobAndScore.setJobId(job.getJobId());
            jobAndScore.setTitle(job.getTitle());
            jobAndScore.setContent(job.getContent());
            jobAndScore.setIllustration(job.getIllustration());
            jobAndScore.setTimeLimit(job.getTimeLimit());
            jobAndScore.setJobCreator(job.getJobCreator());
            jobAndScore.setCourseId(job.getCourseId());
            jobAndScore.setScore(userjob.getScore());
            jobAndScore.setJobUser(userjob.getJobUser());
            jobAndScores.add(jobAndScore);
        }
        return MyResult.ok(jobAndScores);
    }

    public MyResult getJobWithCourseAndQuestionType(Long courseId, String questionType) {
        List<Job> jobs = jobDao.getJobWithCourseAndQuestionTyp(courseId, questionType);
        if (jobs.size() <= 0) {
            return MyResult.build(ErrorConstants.JOB_NOHAVA_CODE, ErrorConstants.JOB_NOHAVA__MSG);
        }
        return MyResult.ok(jobs);
    }

    public MyResult doJob(String userId,Long jobId, String answer) {
        //1.应该先判断答案是否正确，同时录入分数
        //判断答案是否正确,根据jobId获得job信息
        Job job=  jobDao.getJob(jobId);
        int scoreValue=job.getScoreValue();
        int score=0;
        if(job.getAnswer().equals(answer)){
            score=scoreValue;
        }
        int change = jobDao.doJob(userId,jobId, answer,score);
        //同步学生这次作业的成绩。

        if (change < 0) {
            return MyResult.build(ErrorConstants.JOB_SET_SCORE_FAILURE_CODE, ErrorConstants.JOB_SET_SCORE_FAILURE_MSG);
        }
        return MyResult.ok(true);
    }

    public MyResult teacherCorrectJob(Userjob userjob) {
        int change=  jobDao.teacherCorrectJob(userjob);
        if (change < 0) {
            return MyResult.build(ErrorConstants.JOB_TEACHER_CORRECT_FAILURE_CODE, ErrorConstants.JOB_TEACHER_CORRECT_FAILURE_MSG);
        }
        return MyResult.ok(true);
    }


    //**
    public MyResult setQuestion(Question question) {
        String numberName = question.getNumberName();
        List<Long> jobs = question.getJobs();
        //1..循环拿出所有的作业,插入到numberjob表中
        for (int i = 0; i < jobs.size(); i++) {
            NumberJob numberJob = new NumberJob();
            numberJob.setNumberName(numberName);
            numberJob.setJobId(jobs.get(i));
            numberJob.setCourseId(question.getCourseId());
            int change = jobDao.setQuestion(numberJob);
            if (change < 0) {
//              throw new RuntimeException();
                return MyResult.build(ErrorConstants.QUESTION_SET__FAILURE_CODE, ErrorConstants.QUESTION_SET__FAILURE_MSG);

            }
        }
        //2.。一个课程出了题就需要，将这次作业同步给这个课程的所有同学
        //获得这个课程的所有学生
        List<User> users = courseDao.getAllStuByCourseId(question.getCourseId());
        for (int i = 0; i < users.size(); i++) {
            Userjob userjob = new Userjob();
            userjob.setJobUser(users.get(i).getUserId());
            for (int j = 0; j < jobs.size(); j++) {
                userjob.setJobId(jobs.get(j));
                userjob.setNumberName(numberName);
                userjob.setMyAnswer("空");
            }
            int change = jobDao.createUserJob(userjob);
            if (change < 0) {
                throw new RuntimeException();
//                return MyResult.build(ErrorConstants.QUESTION_SET_USERJOB_FAILURE_CODE, ErrorConstants.QUESTION_SET_USERJOB_FAILURE_MSG);
            }

        }

        return MyResult.ok(true);
    }


    public MyResult getCurrentJobsByQuesType(Long courseId,String questionType) {
        //1.先获得这个课程下 当前作业的id再去拿作业信息
        List<Job> jobs = new ArrayList<Job>();
        List<NumberJob> numberJobs = jobDao.getCurrentJobIdWithTea(courseId);




        //2.然后获得作业的信息
        for (int i = 0; i < numberJobs.size(); i++) {
            Job job = jobDao.getJobByJobIdQuesType(numberJobs.get(i).getJobId(),questionType);
            jobs.add(job);
        }

        return MyResult.ok(jobs);


    }

    public MyResult getCurrentJob(Long courseId) {
       String numberName=jobDao.getCurrentNumberName(courseId);
       HashMap<String,String> name=new HashMap<String, String>();
       name.put("numberName",numberName);
       if(numberName==null){
           return MyResult.build(ErrorConstants.NO_CURRENT_JOB_CODE, ErrorConstants.NO_CURRENT_JOB_MSG);
       }
        return MyResult.ok(name);
    }
}
