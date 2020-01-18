package com.kingleadsw.ysm.base.sms;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/29 19:56
 * @Description: sms config

@Configuration
@Log4j2
public class SmsConfig {

    private static final String PRODUCT = "Dysmsapi";
    private static final String DOMAIN = "dysmsapi.aliyuncs.com";

    @Value("${oss.accessKeyId}")
    private  String  accessKeyId;

    @Value("${oss.accessKeySecret}")
    private  String   accessKeySecret;

    @Bean
    public IAcsClient smsClient()
    {


        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");


        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", accessKeyId, accessKeySecret);
        try
        {
            DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", PRODUCT, DOMAIN);
        }
        catch (ClientException e)
        {
            log.error("Failed to smsClient", e);
        }
        return new DefaultAcsClient(profile);
    }
}
 */