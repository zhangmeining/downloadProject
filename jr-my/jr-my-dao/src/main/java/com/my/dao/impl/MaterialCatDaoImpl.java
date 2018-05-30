package com.my.dao.impl;

import com.my.dao.MaterialCatDao;
import com.my.domain.MaterialCat;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/918:32
 */
@Repository
public class MaterialCatDaoImpl implements MaterialCatDao {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;



    /*
         /* @author zhangmeining
         * @date 2018/1/9 18:33
         * @Description: 创建资料的类目
         * @param [materialCat]
         * @return int
         */
    public int createMaterialCat(MaterialCat materialCat) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.insert("MaterialCatDao.insertMaterialCat",materialCat);
        sqlSession.close();
        return change;
    }



    /*
        /* @author zhangmeining
        * @date 2018/1/9 18:34
        * @Description: 删除资料的类目
        * @param [cid]
        * @return int
        */
    public int deleteMaterialCat(Long cid) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.delete("MaterialCatDao.deleteMaterialCat",cid);
        sqlSession.close();
        return change;
    }

    public MaterialCat getMaterialCatByName(String name) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        MaterialCat materialCat=null;
        try {
            materialCat = sqlSession.selectOne("MaterialCatDao.getMaterialCatByName", name);
        }finally {
            sqlSession.close();
        }
        return materialCat;
    }
}
