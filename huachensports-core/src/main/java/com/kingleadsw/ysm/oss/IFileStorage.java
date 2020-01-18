package com.kingleadsw.ysm.oss;

import com.aliyun.oss.model.ObjectMetadata;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/27 14:10
 * @Description:

public  interface IFileStorage {

      OssResult   ossUpload(String key, InputStream inputStream, ObjectMetadata metadata);
      
      OssResult upload(MultipartFile paramMultipartFile);

      OssResult upload(File paramFile);
}
 */