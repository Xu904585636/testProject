package com.kingleadsw.ysm.base.sms;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/29 18:30
 * @Description:
 */


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.text.SimpleDateFormat;
import java.util.Date;


import com.kingleadsw.ysm.utils.Jsons;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SmsDemo
{
    private static final Logger log = LogManager.getLogger(SmsDemo.class);
    static final String product = "Dysmsapi";
    static final String domain = "dysmsapi.aliyuncs.com";
    static final String accessKeyId = "LTAINVsKL4cjrhId";
    static final String accessKeySecret = "97WKZEk08jCReH7uncMdvFt3sVTja3";
    static final String phone = "15218771665";
    static final String signName = "觅扣";

    public static SendSmsResponse sendSms()
            throws ClientException
    {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");


        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", "LTAINVsKL4cjrhId", "97WKZEk08jCReH7uncMdvFt3sVTja3");
        DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);


        SendSmsRequest request = new SendSmsRequest();

        request.setPhoneNumbers("15218771665");

        request.setSignName("觅扣");

        request.setTemplateCode("SMS_149420371");

        request.setTemplateParam("{\"code\":\"668555\"}");





        request.setOutId("hsdOutId");


        SendSmsResponse sendSmsResponse = null;
        try
        {
            sendSmsResponse = (SendSmsResponse)acsClient.getAcsResponse(request);
        }
        catch (Exception e)
        {
            log.error("send err", e);
        }
        return sendSmsResponse;
    }

    public static QuerySendDetailsResponse querySendDetails(String bizId)
            throws ClientException
    {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");


        IClientProfile profile = DefaultProfile.getProfile("cn-beijing", "LTAINVsKL4cjrhId", "97WKZEk08jCReH7uncMdvFt3sVTja3");
        DefaultProfile.addEndpoint("cn-beijing", "cn-beijing", "Dysmsapi", "dysmsapi.aliyuncs.com");
        IAcsClient acsClient = new DefaultAcsClient(profile);


        QuerySendDetailsRequest request = new QuerySendDetailsRequest();

        request.setPhoneNumber("15218771665");

        request.setBizId(bizId);

        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        request.setSendDate(ft.format(new Date()));

        request.setPageSize(Long.valueOf(10L));

        request.setCurrentPage(Long.valueOf(1L));


        QuerySendDetailsResponse querySendDetailsResponse = acsClient.getAcsResponse(request);

        return querySendDetailsResponse;
    }

    public static void main(String[] args)
            throws ClientException, InterruptedException
    {
        SendSmsResponse response = sendSms();
        System.out.println("短信接口返回的数据----------------" + Jsons.toJson(response));
        System.out.println("Code=" + response.getCode());
        System.out.println("Message=" + response.getMessage());
        System.out.println("RequestId=" + response.getRequestId());
        System.out.println("BizId=" + response.getBizId());

        Thread.sleep(3000L);
        if ((response.getCode() != null) && (response.getCode().equals("OK")))
        {
            QuerySendDetailsResponse querySendDetailsResponse = querySendDetails(response.getBizId());
            System.out.println("短信明细查询接口返回数据----------------");
            System.out.println("Code=" + querySendDetailsResponse.getCode());
            System.out.println("Message=" + querySendDetailsResponse.getMessage());
            int i = 0;
            for (QuerySendDetailsResponse.SmsSendDetailDTO smsSendDetailDTO : querySendDetailsResponse.getSmsSendDetailDTOs())
            {
                System.out.println("SmsSendDetailDTO[" + i + "]:");
                System.out.println("Content=" + smsSendDetailDTO.getContent());
                System.out.println("ErrCode=" + smsSendDetailDTO.getErrCode());
                System.out.println("OutId=" + smsSendDetailDTO.getOutId());
                System.out.println("PhoneNum=" + smsSendDetailDTO.getPhoneNum());
                System.out.println("ReceiveDate=" + smsSendDetailDTO.getReceiveDate());
                System.out.println("SendDate=" + smsSendDetailDTO.getSendDate());
                System.out.println("SendStatus=" + smsSendDetailDTO.getSendStatus());
                System.out.println("Template=" + smsSendDetailDTO.getTemplateCode());
            }
            System.out.println("TotalCount=" + querySendDetailsResponse.getTotalCount());
            System.out.println("RequestId=" + querySendDetailsResponse.getRequestId());
        }
    }
}
