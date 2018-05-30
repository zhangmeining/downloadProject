package com.my.service;

import com.my.commom.pojo.MyResult;
import com.my.domain.Material;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/919:36
 */
public interface MaterialService {



    public MyResult addMaterial(Material material);

    public MyResult  deleteMaterial(Long materialId);

    public MyResult  getMaterial(Long cid);
}
