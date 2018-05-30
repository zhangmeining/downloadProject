package com.my.dao;

import com.my.domain.MaterialCat;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/918:30
 */
public interface MaterialCatDao {
    public  int createMaterialCat(MaterialCat materialCat);

    public  int deleteMaterialCat(Long cid);
//根据资料类目的名字查看是否已经存在
    public  MaterialCat getMaterialCatByName(String name);
}
