package com.kingleadsw.ysm.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.httpclient.HttpClient;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.methods.HttpPost;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class ShortUtils {

    //@Value("${short.url}")
    private static final String URL = "http://smssh1.253.com/msg/send/json";

    //@Value("${short.account}")
    private static final String ACCOUNT = "N234202_N4203465";

    //@Value("${short.password}")
    private static final String PASSWORD = "hq9M5eJftB1c34";


    public static JSONObject HttpClientPostMethod(String phone) throws UnsupportedEncodingException {
        JSONObject jsonObject = null;
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        SmsSendRequest build = SmsSendRequest.builder().account(ACCOUNT).msg("您好，您的验证码为:" + code + "，请保存好不要随意给其他人，有效期为3分钟。")
                .password(PASSWORD).phone(phone).build();

        HttpClient httpClient = new HttpClient();
        RequestEntity se = new StringRequestEntity(JSONObject.toJSON(build).toString(), "application/json", "UTF-8");
        PostMethod postMethod = new PostMethod(URL);

        postMethod.setRequestEntity(se);

        try {
            httpClient.executeMethod(postMethod);
            String responseMsg = postMethod.getResponseBodyAsString().trim();
            jsonObject = JSONObject.parseObject(responseMsg);

            jsonObject.put("verificationCode", code);
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();
        }
        return jsonObject;

    }


    public static String sendMsg(){
        HttpClient httpClient = new HttpClient();
        HttpPost httpPost = new HttpPost("");

        httpPost.setHeader("domain","pms");

       // RequestEntity se = new StringRequestEntity(JSONObject.toJSON(build).toString(), "application/json", "UTF-8");



        return null;
    }



}