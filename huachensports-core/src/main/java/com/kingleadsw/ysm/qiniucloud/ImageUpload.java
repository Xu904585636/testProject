package com.kingleadsw.ysm.qiniucloud;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import  com.qiniu.storage.*;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * author szx
 * date 2016-08-23 16:51
 * 7牛文件上传下载
 **/
@Log4j2
public class ImageUpload {
  
    //设置好账号的ACCESS_KEY和SECRET_KEY
    //密钥配置
    private static Auth auth = Auth.create(WebConstants.QINIU_ACCESS_KEY, WebConstants.QINIU_SECRET_KEY);
    //创建上传对象
    private static Configuration configuration = new Configuration(Zone.zone2());

    private static UploadManager uploadManager = new UploadManager(configuration);
    //上传文件的路径
    private static String filePath = WebConstants.UPLOAD_FILE_PATH+ File.separator + WebConstants.STORE_IMG_PATH;

    //简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getUpToken(String fileName){
        System.out.println(WebConstants.QINIU_CLOUD_KEY);
        return auth.uploadToken(WebConstants.QINIU_CLOUD_KEY, fileName);
    }

    /**
     * 为系统业务 上传图片使用方法
     * @param fileName
     * @return
     */
    public static String simpleUpload(String fileName){
        try {
            log.info("开始上传图片："+fileName);
            //调用put方法上传
            Response res = uploadManager.put(filePath + File.separator + fileName,fileName , getUpToken(fileName));
            String bodyString = res.bodyString();
            log.info("上传图片结束："+bodyString);
            return WebConstants.QINIU_LINK + fileName;
        } catch (QiniuException e) {
            e.printStackTrace();
            try {
                Response response = e.response;
                log.error("七牛返回信息："+response.bodyString());
            }catch (Exception et){
            	log.error("七牛返回信息异常：",et);
            }
            log.error("上传图片失败：",e);
           return "";
        }
    }
    
    /**
     * 为系统业务 上传图片使用方法
     * @param fileName
     * @return
     */
    public static String simpleUpload(byte[] data,String fileName){
        try {
        	log.info("开始上传图片："+fileName);
            //调用put方法上传
            Response res = uploadManager.put(data,fileName , getUpToken(fileName));
            String bodyString = res.bodyString();
            log.info("上传图片结束："+bodyString);
            return WebConstants.QINIU_LINK + fileName;
        } catch (QiniuException e) {
            e.printStackTrace();
            try {
                Response response = e.response;
                log.error("七牛返回信息："+response.bodyString());
            }catch (Exception et){
            	log.error("七牛返回信息异常：",et);
            }
            log.error("上传图片失败：",e);
           return "";
        }
    }

    /**
     * 普通上传文件
     * @param filePath 文件路劲
     * @param key 文件key
     * @return
     */
    public static String qnUpload(String filePath,String key){
        try {
            if(null == key ||  "".equals(key)){
            	 key = UUID.randomUUID().toString().replace("-","");
            }
            log.info("开始上传图片："+filePath);
            long startTime = System.currentTimeMillis();
            //调用put方法上传
            Response res = uploadManager.put(filePath,key , getUpToken(key));
            String bodyString = res.bodyString();
            log.info("上传图片结束："+bodyString+"*消耗时间："+(System.currentTimeMillis() - startTime));
            return WebConstants.QINIU_LINK + key;
        } catch (QiniuException e) {
            e.printStackTrace();
            try {
                Response response = e.response;
                log.error("七牛返回信息："+response.bodyString());
            }catch (Exception et){
                log.error("七牛返回信息异常：",et);
            }
            log.error("上传图片失败：",e);
            return "";
        }
    }
    /**
     * 文件md5校验(下载的图片流md5与本地已存在的md5比较，如果存在则不上传，直接采用已有的七牛图片链接)
     * @param md5
     * @return
     */
    private static Boolean fileMdbCompare(String md5){
        String fileName = md5 + ".file";
        log.info("判断当前imageUrl是否存在本地："+filePath + fileName);
        long startTime = System.currentTimeMillis();
        File file = new File(filePath + fileName);
        boolean flag = false;
        if(file.exists()){
            flag = true;
        }
        log.info("判断结束："+flag+"*消耗时间："+(System.currentTimeMillis() - startTime));
        return flag;
    }

    /**
     *将图片url链接持久化到本地(建立标记文件夹)
     * @param imageUrl
     */
    private static String fileDownUpload(String imageUrl){
    	log.info("准备写入文件："+imageUrl+"到本地");
    	long startTime = System.currentTimeMillis();
        try {
            URL url = new URL(imageUrl);
            URLConnection urlConnection = url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();

            //the filename must equals imageUrl's md5code szx
            String fileName =  MD5Util.MD5Encode(imageUrl,null);
            File file = new File(filePath + File.separator + fileName + ".file");
            FileOutputStream outputStream = new FileOutputStream(file);
            int i;
            byte by[] = new byte[512];
            //这里不建议使用byte by[] = new byte[inputStream.available()]; inputStream.read(by);outputStream.write(by);内存一次开销太大
            while((i = inputStream.read(by)) != -1){
                outputStream.write(by,0,i);
            }
            outputStream.flush();

            inputStream.close();
            outputStream.close();
            log.info("文件："+imageUrl+"写入本地成功,花费时间："+(System.currentTimeMillis() - startTime));
            return fileName + ".file";
        } catch (Exception e) {
            log.error("图片资源访问失败!!!"+imageUrl,e);
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 检查文件并上传
     * @param imageUrl
     * @return
     */
    public static String checkAndUpFile(String imageUrl){
        String urlMd5 = MD5Util.MD5Encode(imageUrl,null);
        //如果文件存在
        if(fileMdbCompare(urlMd5)){
        	log.info("不上传文件直接访问已存在的文件："+imageUrl);
            return WebConstants.QINIU_LINK + urlMd5 + ".file";
        }else{
        	log.info("准备上传到七牛文件："+imageUrl);
           return simpleUpload(fileDownUpload(imageUrl));
        }
    }
}
