package com.kingleadsw.ysm.odp.facade.system;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.kingleadsw.ysm.qiniucloud.ImageUpload;
import com.kingleadsw.ysm.utils.Dates;

import sun.misc.BASE64Decoder;

/**
 * 文件相关操作
 */
@Service
public class FileFacade {

    public  String updateFile(MultipartFile file) throws IOException {
        String originalFilename=file.getOriginalFilename();
        String[] strs=originalFilename.split("\\.");
        String fileType=null;
        if(strs.length>1) {
            fileType=strs[1];
        }
        String fileName= Dates.now() +"."+fileType;
       String url = ImageUpload.simpleUpload(file.getBytes(), fileName);

        return  url;
    }
    

    public List<String> updateFile(List<MultipartFile> files){
        List<String> list= Lists.newArrayList();

        files.forEach(file->{

            String originalFilename=file.getOriginalFilename();
            String[] strs=originalFilename.split("\\.");
            String fileType=null;
            if(strs.length>1) {
                fileType=strs[1];
            }
            String fileName= Dates.now() +"."+fileType;

            try {
                list.add(ImageUpload.simpleUpload(file.getBytes(), fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

      return  list;
    }

    public String updateBaseStr(String file) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();

        String[] str = file.split(",");

            // Base64解码
        byte[] bytes = decoder.decodeBuffer(str[1]);
            for (int i = 0; i < bytes.length; ++i) {
                if (bytes[i] < 0) {// 调整异常数据
                    bytes[i] += 256;
                }
            }

            String url = ImageUpload.simpleUpload(bytes, Dates.now()+".png");
            return url;

    }
}
