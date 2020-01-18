package com.kingleadsw.ysm.need.token;

import com.kingleadsw.ysm.caches.RedisUtil;
import com.kingleadsw.ysm.exception.NotfoundException;
import com.kingleadsw.ysm.security.AESCoder;
import com.kingleadsw.ysm.security.Securitys;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.Dates;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;

/**
 *
 * @author   zhoujie
 *
 * 开发人员调用此类进行加密解密
 */

@Log4j2
@Component
public class Tokens {

    public static final Key ENCRYPT_KEY = AESCoder.getAESKey("mekou");

    private static final String keyPrefix = "delUserId-";

    private static final String delMap = "delUserIdMap";

    private static final String tokenPrefix = "token-";

    @Autowired
    private RedisUtil redisUtils;

    public static RedisUtil redisUtil;

    @PostConstruct
    public void Init(){
        redisUtil = redisUtils;
    }

    public static String enToken(Long userId, String ip)
    {
        StringBuilder token = new StringBuilder();
        token.append(userId).append(":");
        token.append(Dates.now()).append(":");
        token.append(ip);
        String result = Securitys.encryptAES(token.toString(), ENCRYPT_KEY);
        return  result;
    }

    public static void main(String[] args) {
        StringBuilder token = new StringBuilder();
        token.append(11).append(":");
        token.append(Dates.now()).append(":");
        token.append(123);
         String encode =Securitys.encryptAES(token.toString(), ENCRYPT_KEY);
        log.info(encode);
         String decode= Securitys.decryptAES(encode,ENCRYPT_KEY);
        log.info(decode);


    }

    public  static  Token  deToken(String token){
        if(Asserts.isNull(token)){
            log.error("token 为空");
            throw new NotfoundException(Integer.valueOf(403003),"Token为空");
        }

        String value= Securitys.decryptAES(token,ENCRYPT_KEY);

        String[] arr = value.split(":");


        return   Token.builder().userId(Long.valueOf(arr[0])).createdTime(Long.valueOf(arr[1])).ip(arr[2]).build();
    }


}
