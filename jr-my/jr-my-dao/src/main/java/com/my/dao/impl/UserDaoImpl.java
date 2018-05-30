package com.my.dao.impl;

import ch.qos.logback.classic.Logger;
import com.my.dao.UserDao;
import com.my.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2017/12/2910:41
 */
public class UserDaoImpl  implements UserDao{

    private final static Logger logger = (Logger) LoggerFactory.getLogger(UserDaoImpl.class);
    private SqlSessionFactory sqlSessionFactory;

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    /*
             /* @author zhangmeining
                * @date 2017/12/29 11:02
                * @Description: TODO
                * @param [user]
                * @return int
                */
    public int insert(User user) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.insert("user.insert",user);
        sqlSession.close();
        return change;
    }
    /*
          /* @author zhangmeining
          * @date 2017/12/29 11:03
          * @Description: TODO
          * @param [userId]
          * @return int
          */
    public int delete(String userId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.delete("user.delete", userId);
        sqlSession.close();
        return change;
    }
    /*
            /* @author zhangmeining
            * @date 2017/12/29 11:03
            * @Description: TODO
            * @param [user]
            * @return int
            */
    public int update(User user) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.update("user.update",user);
        sqlSession.close();
        return change;
    }
    /*
           /* @author zhangmeining
           * @date 2017/12/29 11:03
           * @Description: TODO
           * @param []
           * @return java.util.List<com.my.domain.User>
           */
    public List<User> getAllUsers() {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<User>  users=sqlSession.selectList("user.getAllUsers");
        sqlSession.close();
        return users;
    }
    /*
            /* @author zhangmeining
            * @date 2017/12/29 11:03
            * @Description: TODO
            * @param [userId]
            * @return com.my.domain.User
            */
    public User getUserById(String userId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        User user=sqlSession.selectOne("user.getUserById",userId);
        sqlSession.close();
        return user;
    }

    public List<User> getAllStudents() {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<User>  users=sqlSession.selectList("user.getAllStudents");
        sqlSession.close();
        return users;
    }


}
