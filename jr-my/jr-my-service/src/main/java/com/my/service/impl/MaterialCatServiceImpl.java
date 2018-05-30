package com.my.service.impl;

import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.JsonUtils;
import com.my.dao.MaterialCatDao;
import com.my.domain.MaterialCat;
import com.my.service.MaterialCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/918:42
 */
@Service
public class MaterialCatServiceImpl implements MaterialCatService {
    @Autowired
    private MaterialCatDao materialCatDao;



    public MyResult createMaterialCat(MaterialCat materialCat) {

//1.先判断这个文件名字是否已经存在 了
       MaterialCat materialCat1= materialCatDao.getMaterialCatByName(materialCat.getName());
       if(materialCat!=null){
           return MyResult.build(ErrorConstants.MATERIAL_CAT_HAVED_CODE,ErrorConstants.MMATERIAL_CAT_HAVED_MSG);
       }
       int change=materialCatDao.createMaterialCat(materialCat);
        Long cid=materialCat.getCid();
        Map map=new HashMap();
        map.put("cid",cid);
       if(change>=1){
           return MyResult.ok(map);
       }else{
           return MyResult.build(ErrorConstants.MATERIAL_CAT_CREATE_FAILURE_CODE,ErrorConstants.MATERIAL_CAT_CREATE_FAILURE__MSG);
       }

    }

    public MyResult deleteMaterialCat(Long cid) {
        int change=materialCatDao.deleteMaterialCat(cid);
        if(change>=1){
            return MyResult.ok(true);
        }else{
            return MyResult.build(ErrorConstants.MATERIAL_CAT_DELETE_FAILURE_CODE,ErrorConstants.MATERIAL_CAT_DELETE_FAILURE_MSG);
        }

    }
}
