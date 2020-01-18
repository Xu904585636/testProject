package com.kingleadsw.ysm.caches.config;

import org.springframework.util.StringUtils;

public class CharacterUtils {


    public static String isNotNull(String value1, String value2, String value3) {
        StringBuilder result = new StringBuilder();
        if (!StringUtils.isEmpty(value1)) {
            result.append(value1);
        }
        if (!StringUtils.isEmpty(value2)) {
            result.append(value2);
        }
        if (!StringUtils.isEmpty(value3)) {
            result.append(value3);
        }
        if(StringUtils.isEmpty(result.toString())){
            result.append("无当前详情地址");
        }
        return result.toString();
    }

}
