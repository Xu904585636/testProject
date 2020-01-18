package com.kingleadsw.ysm.need.token;


import com.kingleadsw.ysm.caches.RedisUtil;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.request.RequestPayload;
import com.kingleadsw.ysm.sequence.Sequences;
import com.kingleadsw.ysm.utils.Dates;
import com.kingleadsw.ysm.utils.Jsons;
import com.kingleadsw.ysm.utils.ObjectCopys;
import com.qiniu.util.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: zhoujie
 * @Description:
 */
// todo 暂时不加密
@Service
@Log4j2
public class TokenService {

//    private  static  String STORAGE_TYPE="hash";       //存储token

    public static String LOGIN_NAME = "login";       //存储管理员登录

    @Autowired
    RedisUtil redisUtil;

    public void addToken(String tokenStr, Token token, Long userId) {
        //将token存储在缓存中key为tokenStr字符串,值为用户信息的json字符串
        redisUtil.set(tokenStr, Jsons.toJson(token), 60 * 60 * 24 * 7);

        if (redisUtil.hasKey(LOGIN_NAME)) {
            //判断是否该用户已经登陆存在tokenStr
            if (redisUtil.hHasKey(LOGIN_NAME, LOGIN_NAME + userId)) {
                String string = (String) redisUtil.hget(LOGIN_NAME, LOGIN_NAME + userId);
                if (!tokenStr.equals(string)) {
                    //如果已登录,删除之前的token信息
                    redisUtil.del(string);
                    //将新的登录tokenStr字符串写入缓存中,用于下次查询用户登录hash缓存中的key
                    redisUtil.hset(LOGIN_NAME, LOGIN_NAME + userId, tokenStr);
                }
            } else {
                redisUtil.hset(LOGIN_NAME, LOGIN_NAME + userId, tokenStr);
            }
        } else {
            //将用户id为key,tokenStr字符串为value,写入登录信息的缓存map中
            redisUtil.hset(LOGIN_NAME, LOGIN_NAME + userId, tokenStr);
        }

    }

    public  Token  getToken(String tokenStr){
        Object json =  redisUtil.get(tokenStr);
        //初始化失效时间一周
        if(json != null && !"".equals(json)){
            redisUtil.expire(tokenStr,60*60*24*7);
        }

        Token token= Jsons.toObj((String) json,Token.class);


        return  token;

    }

    public String addToken(Long userId)
    {
        RequestPayload payload = RequestCache.get();
        payload.setUserId(userId);
        Token token = ObjectCopys.maping(payload, Token.class);
        token.setCreatedTime(Long.valueOf(Dates.now()));
        String tokenStr = userId.toString() + Sequences.getId();

        addToken(tokenStr, token,userId);
        return tokenStr;
    }
}
