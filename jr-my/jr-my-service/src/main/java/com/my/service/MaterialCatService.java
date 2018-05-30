package com.my.service;

import com.my.commom.pojo.MyResult;
import com.my.domain.MaterialCat;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/918:41
 */
public interface MaterialCatService {
    public MyResult createMaterialCat(MaterialCat materialCat);

    public MyResult deleteMaterialCat(Long cid);

}
