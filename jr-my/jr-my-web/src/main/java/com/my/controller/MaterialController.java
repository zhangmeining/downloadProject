package com.my.controller;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.ExceptionUtil;
import com.my.commom.utils.JsonUtils;
import com.my.domain.Material;
import com.my.service.MaterialService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/4/1719:45
 */
@Controller
@RequestMapping(value = "material")
public class MaterialController {
    private final static Logger logger = (Logger) LoggerFactory.getLogger(MaterialController.class);
@Autowired
private MaterialService materialService;


   // http://localhost:8080/material/create
    @RequestMapping(value="/create", method= RequestMethod.POST)
    @ResponseBody
    public MyResult createMaterialController(Material material) {
        /*
         /* @author zhangmeining
         * @date 2018/5/8 12:26
         * @Description: 创建资料
         * @param [material]
         * @return com.my.commom.pojo.MyResult
         */
        logger.info("入参是："+ JsonUtils.objectToJson(material));
        MyResult myResult;
        try {
            myResult = materialService.addMaterial(material);
        }catch (Exception e){
            logger.error("createMaterialController"+"创建资料失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.MATERIAL_CREATE_FAILURE_CODE,ErrorConstants.MATERIAL_CREATE_FAILURE__MSG);
        }
        return myResult;

    }



    // http://localhost:8080/material/delete
    @RequestMapping(value="/delete", method= RequestMethod.POST)
    @ResponseBody
    public MyResult deleteMaterialController(Long materialId) {
        /*
         /* @author zhangmeining
         * @date 2018/5/8 12:26
         * @Description: 删除资料
         * @param [materialId]
         * @return com.my.commom.pojo.MyResult
         */
        logger.info("入参是："+ JsonUtils.objectToJson(materialId));
        MyResult myResult;
        try {
            myResult = materialService.deleteMaterial(materialId);
        }catch (Exception e){
            logger.error("createMaterialController"+"删除资料失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.MATERIAL_DELETE_FAILURE_CODE,ErrorConstants.MATERIAL_DELETE_FAILURE_MSG);
        }
        return myResult ;

    }



    // http://localhost:8080/material/getMaterial
    @RequestMapping(value="/getMaterial", method= RequestMethod.POST)
    @ResponseBody
    public MyResult getMaterialController(Long cid) {
      /*
       /* @author zhangmeining
       * @date 2018/5/9 23:24
       * @Description: 每次点击一个没目录的时候就重新获取一次新的目录情况
       * @param [cid]
       * @return com.my.commom.pojo.MyResult
       */
        logger.info("MaterialController  getMaterialController 入参是：cid："+ cid);
        MyResult myResult;
        try {
            myResult = materialService.getMaterial(cid);
            logger.info("MaterialController  getMaterialController  返回结果是"+JsonUtils.objectToJson(myResult));
        }catch (Exception e){
            logger.error("aterialController  getMaterialController"+"获取目录书籍失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.MATERIAL_NOCAT_OR_MATERIAL_CODE,ErrorConstants.MATERIAL_NOCAT_OR_MATERIAL_MSG);
        }
        return myResult ;

    }

}
