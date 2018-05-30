package com.my.service.impl;

import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.dao.MaterialDao;
import com.my.domain.Material;
import com.my.domain.MaterialCat;
import com.my.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/919:37
 */
@Service
public class MaterialServiceImpl implements MaterialService {
    @Autowired
    private MaterialDao materialDao;



    public MyResult addMaterial(Material material) {
        Map map=new HashMap();

        int change=materialDao.addMaterial(material);
        map.put("materialId",material.getMaterialId());
        if(change>=1){
            return MyResult.ok(map);
        }else{
            return MyResult.build(ErrorConstants.MATERIAL_CREATE_FAILURE_CODE,ErrorConstants.MATERIAL_CREATE_FAILURE__MSG);
        }

    }

    public MyResult deleteMaterial(Long materialId) {
        int change=materialDao.deleteMaterial(materialId);
        if(change>=1){
            return MyResult.ok(true);
        }else{
            return MyResult.build(ErrorConstants.MATERIAL_DELETE_FAILURE_CODE,ErrorConstants.MATERIAL_DELETE_FAILURE_MSG);
        }

    }

    public MyResult getMaterial(Long cid) {
        //遍历materialCat表看判断parentId==cid，若有全部返回，若是没有看这个cid下有没有书籍，若是有返回书籍，若是没有返回信息提示客户端
        //1.先判断是否有文件夹
        List<MaterialCat > materialCats=materialDao.getAllMaterialCat(cid);
       if(materialCats.size()<=0){
           //2.没有文件夹判断是否有书籍
           List<Material > materials=materialDao.getAllMaterial(cid);
           if(materials.size()<=0){
               return MyResult.build(ErrorConstants.MATERIAL_NOCAT_OR_MATERIAL_CODE,ErrorConstants.MATERIAL_NOCAT_OR_MATERIAL_MSG);
           }
           else{
               return MyResult.ok(materials);
           }
       }else{
           return MyResult.ok(materialCats);
       }
    }
}
