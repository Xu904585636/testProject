package com.kingleadsw.ysm.oss;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/27 14:41
 * @Description:

@Configuration
public class OssConfig {

    @Value("${oss.pubendPoint}")
    private  String  entpoint;

    @Value("${oss.accessKeyId}")
    private  String  accessKeyId;

    @Value("${oss.accessKeySecret}")
    private  String   accessKeySecret;


    @Bean
    public OSSClient ossClient()
    {
        return new OSSClient( this.entpoint, this.accessKeyId,accessKeySecret );
    }
}
 */