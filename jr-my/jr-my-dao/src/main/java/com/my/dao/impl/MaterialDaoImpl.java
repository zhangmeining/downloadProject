package com.my.dao.impl;

import ch.qos.logback.classic.Logger;
import com.my.dao.MaterialDao;
import com.my.domain.Material;
import com.my.domain.MaterialCat;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/918:21
 */
@Repository
public class MaterialDaoImpl implements MaterialDao {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(MaterialDaoImpl.class);
    @Autowired
    private SqlSessionFactory sqlSessionFactory;



    public int addMaterial(Material material) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.insert("materiDao.insertMaterial",material);
        sqlSession.close();
        return change;
    }

    public int deleteMaterial(Long materialId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        int  change=sqlSession.delete("materiDao.deleteMaterial",materialId);
        sqlSession.close();
        return change;
    }

    public List<MaterialCat > getAllMaterialCat(Long parentId) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<MaterialCat > materialCats;
        try {
            materialCats = sqlSession.selectList("materiDao.getAllMaterialCat",parentId);
        }finally {
            sqlSession.close();
        }

        return materialCats;
    }

    public List<Material> getAllMaterial(Long cid) {
        SqlSession sqlSession=sqlSessionFactory.openSession();
        List<Material > materials;
        try {
            materials = sqlSession.selectList("materiDao.getAllMaterial",cid);
        }finally {
            sqlSession.close();
        }

        return materials;
    }
}
