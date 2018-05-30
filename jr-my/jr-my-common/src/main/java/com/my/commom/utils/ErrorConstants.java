package com.my.commom.utils;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2017/12/2913:58
 */
public class ErrorConstants {
    /****************有关用户的信息********************************/
    public static int USER_NOTEXISTS_CODE=10000;
    public static String USER_NOTEXISTS_MSG="用户不存在";

    public static int USER_MODIFY_CODE=10001;
    public static String USER_MODEIY_MSG="修改用户信息失败";

    public static int USER_NAME_PASSWORD_CODE=10002;
    public static String USER_NAME_PASSWORD_MSG="用户名或者密码错误";

    public static int USER_INPUT_PARAM_CODE=10003;
    public static String USER_INPUT_PARAM_MSG="用户注册时的输入参数不能为空";

    public static int USER_EXISTS_CODE=10004;
    public static String USER_EXISTS_MSG="用户已存在";

    public static int USER_REGISTER_FAILURE_CODE=10005;
    public static String USER_REGISTER_FAILURE_MSG="用户注册失败";

    public static int USER_LOGIN_FAILURE_CODE=10006;
    public static String USER_LOGIN_FAILURE_MSG="用户登录失败";

    public static int USER_MODIFY_FAILURE_CODE=10007;
    public static String USER_MODIFY_FAILURE_MSG="用户修改信息失败";

    public static int Student_NOTEXISTS_CODE=10008;
    public static String Student_NOTEXISTS_MSG="还没有学生";
    /*********************有关课程的信息*********************************/
    public static int CREATE_COURSE_FAILURE_CODE=20000;
    public static String CREATE_COURSE_FAILURE_MSG="创建课程失败";

    public static int DELETE_COURSE_FAILURE_CODE=20001;
    public static String DELETE_COURSE_FAILURE_MSG="删除课程失败";


    public static int NOTEXISTS_COURSE_CODE=20002;
    public static String NOTEXISTS_COURSE_MSG="对不起无此课程号";


    public static int COURSE_GET_FAILURE_CODE=20003;
    public static String COURSE_GET_FAILURE_MSG="得到全部的课程失败";


    public static int JOIN_COURSE_FAILURE_CODE=20004;
    public static String JOIN_COURSE_FAILURE_MSG="创建课程失败";


    public static int EXIT_COURSE_FAILURE_CODE=20005;
    public static String EXITCOURSE_FAILURE_MSG="退出课程失败";


    public static int DELETE_COURSE_USERCOURSE_FAILURE_CODE=20006;
    public static String DELETE_COURSE_USERCOURSE_FAILURE_MSG="删除课程失败关联删除usercourse表失败";

    public static int DELETE_COURSE_JOB_FAILURE_CODE=20007;
    public static String DELETE_COURSE_JOB_FAILURE_MSG="删除课程失败关联删除JOB表失败";


    public static int DELETE_COURSE_USERJOB_FAILURE_CODE=20008;
    public static String DELETE_COURSE_USERJOB_FAILURE_MSG="删除课程失败关联删除USERJOB表失败";


    public static int EXIT_COURSE_USERJOB_FAILURE_CODE=20009;
    public static String EXIT_COURSE_USERJOB_FAILURE_MSG="退出课程关联删除userJOB表失败";

    public static int GET_STU_BYCOURSEID_FAILURE_CODE=20010;
    public static String GET_STU_BYCOURSEID_FAILURE_MSG="获得某个课程的所有学生失败";


    public static int STU_NO_COURSE_CODE=20011;
    public static String STU_NO_COURSE_MSG="此学生没有加入任何课程";


    public static int COURSENAME_HAVED_CODE=20012;
    public static String COURSENAME_HAVED__MSG="此课程名已经存在";

    /****************************有关作业的相关信息*********************************************/

    public static int JOB_CREATE_FAILURE_CODE=30000;
    public static String JOB_CREATE_FAILURE_MSG="创建作业失败";


    public static int JOB_DELETE_FAILURE_CODE=30001;
    public static String JOB_DELETE_FAILURE_MSG="删除作业失败";

    public static int JOB_SHOW_FAILURE_CODE=30002;
    public static String JOB_SHOW_FAILURE_MSG="展示一个课程的所有作业失败";

    public static int JOB_COUNT_FAILURE_CODE=30003;
    public static String JOB_COUNT_FAILURE_MSG="统计作业成绩失败";


    public static int JOB_NOHAVA_CODE=30004;
    public static String JOB_NOHAVA__MSG="一个课程的某个题型下没有作业";

    public static int JOB_GET_FAILURE_CODE=30005;
    public static String JOB_GET_FAILURE_MSG="获得作业失败";


    public static int JOB_SET_SCORE_FAILURE_CODE=30005;
    public static String JOB_SET_SCORE_FAILURE_MSG="获得作业失败";


    public static int QUESTION_SET__FAILURE_CODE=30006;
    public static String QUESTION_SET__FAILURE_MSG="出题插入数据库数据失败";


    public static int NO_CURRENT_JOB_CODE=30007;
    public static String NO_CURRENT_JOB_MSG="没有当前作业";


    public static int QUESTION_SET_USERJOB_FAILURE_CODE=30008;
    public static String QUESTION_SET_USERJOB_FAILURE_MSG="出题插入关联插入USERJOB表失败";

    public static int JOB_TEACHER_CORRECT_FAILURE_CODE=30009;
    public static String JOB_TEACHER_CORRECT_FAILURE_MSG="获得作业失败";

    /*****************************************有关资料类目的相关信息**********************************************/
    public static int MATERIAL_CAT_CREATE_FAILURE_CODE=40000;
    public static String MATERIAL_CAT_CREATE_FAILURE__MSG="创建资料的类目失败";


    public static int MATERIAL_CAT_DELETE_FAILURE_CODE=40001;
    public static String MATERIAL_CAT_DELETE_FAILURE_MSG="删除资料的类目失败";


    public static int MATERIAL_CAT_HAVED_CODE=40002;
    public static String MMATERIAL_CAT_HAVED_MSG="此资料 类目已经存在了";


    /********************************************有关资料的相关信息***************************************************************/
    public static int MATERIAL_CREATE_FAILURE_CODE=50000;
    public static String MATERIAL_CREATE_FAILURE__MSG="创建资料失败";


    public static int MATERIAL_DELETE_FAILURE_CODE=50001;
    public static String MATERIAL_DELETE_FAILURE_MSG="删除资料失败";

    public static int MATERIAL_NOCAT_OR_MATERIAL_CODE=50002;
    public static String MATERIAL_NOCAT_OR_MATERIAL_MSG="此目录下无文件夹或者目录";

}
