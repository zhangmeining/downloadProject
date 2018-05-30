package com.my.controller;

import com.my.commom.utils.JsonUtils;
import com.my.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 上传图片后，会返回图片的URL，然后app再提交，然后保存到数据库
 * @date 2017/12/2922:16
 */
@Controller
public class PictureController {
    @Autowired
    private PictureService pictureService;

    //http://localhost:8080/pic/upload
    @RequestMapping("pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile) {
        //System.out.println("8888888888上传了图片88888888");
        /**
         * map中的值
         * error 0：成功  1:失败
         * message：上传失败的提示信息
         * url:保存到图片服务器后的URL
         */

        Map result = pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把Result转换成json格式的字符串。
        String json = JsonUtils.objectToJson(result);
        return json;
        //return result ;
    }
}
