package com.my.controller;

import ch.qos.logback.classic.Logger;
import com.my.commom.pojo.MyResult;
import com.my.commom.utils.ErrorConstants;
import com.my.commom.utils.ExceptionUtil;
import com.my.commom.utils.JsonUtils;
import com.my.domain.MaterialCat;
import com.my.service.MaterialCatService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/4/1718:49
 */
@Controller
@RequestMapping(value="materialCat")
public class MaterialCatController {
@Autowired
private MaterialCatService materialCatService;

    private final static Logger logger = (Logger) LoggerFactory.getLogger(MaterialCatController.class);
//http://localhost:8080/materialCat/create
    @RequestMapping(value="/create", method= RequestMethod.POST)
    @ResponseBody
    public MyResult createMaterialCatController(MaterialCat materialCat) {
        logger.info("createMaterialCatController 请求入参是："+JsonUtils.objectToJson(materialCat));
        MyResult myResult=null;
        materialCat.setCreated(new Date() );
        try {
            myResult = materialCatService.createMaterialCat(materialCat);
        }catch (Exception e){
            logger.error("在createMaterialCatController"+"创建类目失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.MATERIAL_CAT_CREATE_FAILURE_CODE,ErrorConstants.MATERIAL_CAT_CREATE_FAILURE__MSG);
        }
        return myResult;
    }





    //http://localhost:8080/materialCat/delete
    @RequestMapping(value="/delete", method= RequestMethod.POST)
    @ResponseBody
    public MyResult deleteMaterialCatController(Long cid) {
        MyResult myResult;

        try {
            myResult = materialCatService.deleteMaterialCat(cid);
        }catch (Exception e){
            logger.error("deleteMaterialCatController"+"删除类目失败"+ ExceptionUtil.getStackTrace(e));
            return MyResult.build(ErrorConstants.MATERIAL_CAT_DELETE_FAILURE_CODE,ErrorConstants.MATERIAL_CAT_DELETE_FAILURE_MSG);
        }
        return myResult;
    }
}
