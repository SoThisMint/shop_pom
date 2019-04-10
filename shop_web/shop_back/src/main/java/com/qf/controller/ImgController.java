package com.qf.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author ：Tony
 * @date ：Created in 2019/4/9 17:09
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Controller
@RequestMapping("/img")
public class ImgController {

    private static final String UPLOAD_PATH = "C:\\Users\\Administrator\\Desktop\\imgs\\";

    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/upload")
    @ResponseBody
    public String upload(MultipartFile file) {
//        System.out.println(file.getOriginalFilename());
        /*jdk1.8特性：
         * try()里支持关闭资源
         * */
//        try (
//                InputStream in = file.getInputStream();
//                OutputStream out = new FileOutputStream(UPLOAD_PATH + UUID.randomUUID().toString().substring(0, (int) (Math.random() * 5) + 5) + file.getOriginalFilename())
//        ) {
//            IOUtils.copy(in, out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int index = file.getOriginalFilename().lastIndexOf(".");
        String houzhui = file.getOriginalFilename().substring(index + 1);
        System.out.println("截取到的后缀==>" + houzhui);
        try {
            StorePath storePath = fastFileStorageClient.uploadImageAndCrtThumbImage(file.getInputStream(), file.getSize(), houzhui, null);
            String storeURL = storePath.getFullPath();
            System.out.println("图片地址==>" + storeURL);
            return "{\"uploadPath\":\"" + storeURL + "\"}";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
