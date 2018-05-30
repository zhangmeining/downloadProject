package com.my.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author zhangmeining
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2017/12/2922:01
 */
public interface PictureService {
    Map uploadPicture(MultipartFile uploadFile);
}
