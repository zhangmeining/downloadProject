package com.my.dao;

import com.my.domain.Material;
import com.my.domain.MaterialCat;

import java.util.List;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/1/918:19
 */
public interface MaterialDao {
    public int  addMaterial(Material material);

    public int  deleteMaterial(Long materialId);

    public List<MaterialCat > getAllMaterialCat(Long parentId);
    public List<Material > getAllMaterial(Long cid);
}
