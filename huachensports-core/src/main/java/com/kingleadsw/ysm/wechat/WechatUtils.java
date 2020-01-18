package com.kingleadsw.ysm.wechat;

import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.exception.IllegalParameterException;
import com.kingleadsw.ysm.sequence.Sequences;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.Dates;
import com.kingleadsw.ysm.utils.Jsons;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zhoujie
 * @Date: 2018/11/23 11:32
 * @Description: 微信公众号相关操作
*/
@Component
public class WechatUtils {

    @Value("${wechat.appid}")
    private  String appId;

    @Value("${wechat.secret}")
    private  String secret;

    private  String devappId;


    private  String devsecret;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 小程序端获取
     * @param code
     */


    /**
     * 根据code获取当前用户的openid(小程序)
     * @param code
     * @return
     */
    public String getProgramUserInfo(String code){

        String url=String.format("https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code",appId, secret, code);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url,String.class);

        String userAuthJson=  responseEntity.getBody();


        WechatUserAuthDTO wechatUserAuthDTO = Jsons.toObj(userAuthJson,WechatUserAuthDTO.class);

        if(Asserts.isNull(wechatUserAuthDTO) || Asserts.isNull(wechatUserAuthDTO.getOpenid())){
             throw  new IllegalParameterException(ServerCode.BussinessError.APTITUDED_FAILED,userAuthJson);
        }

        redisTemplate.opsForValue().set("user_token"+wechatUserAuthDTO.getOpenid(),
                wechatUserAuthDTO.getAccess_token(),
                259200 * 1000,
                TimeUnit.SECONDS);

        return  wechatUserAuthDTO.getOpenid();
    }

    public  WechatUserAuthDTO getAccessToken(){
        String tokenurl=String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",appId,secret);

        ResponseEntity<String>  tokens =  restTemplate.getForEntity(tokenurl,String.class);

        String tokenJson=tokens.getBody();

        System.out.println("微信获取access_token......"+tokenJson);
        WechatUserAuthDTO wechatUserAuthDTO=  Jsons.toObj(tokenJson,WechatUserAuthDTO.class);

        return wechatUserAuthDTO;
    }

//    public WechatConfigDTO getWchatConfig(HttpServletRequest request, String pageUrl) throws NoSuchAlgorithmException {
//
//
//        //todo  这里后期待优化，可以获取一次后缓存access_token到redis 失效后再去请求微信 2018-12-18
//        String tokenurl=String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",appId,secret);
//
//        ResponseEntity<String>  tokens =  restTemplate.getForEntity(tokenurl,String.class);
//
//       String tokenJson=tokens.getBody();
//
//        System.out.println("微信获取access_token......"+tokenJson);
//        WechatUserAuthDTO wechatUserAuthDTO=  Jsons.toObj(tokenJson,WechatUserAuthDTO.class);
//
//
//        String url=String.format("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=%s&type=jsapi",wechatUserAuthDTO.getAccess_token());
//
//        ResponseEntity<String>  responseEntity = restTemplate.getForEntity(url,String.class);
//        String json=responseEntity.getBody();
//
//        System.out.println("微信配置....."+json);
//        WechatCodeDTO wechatCodeDTO =  Jsons.toObj(json,WechatCodeDTO.class);
//
//        String noncestr= Sequences.get32UUID().substring(0,8);
//        String timestamp=String.valueOf(Dates.now()/1000);
//        String str = "jsapi_ticket="+wechatCodeDTO.getTicket()+"&noncestr="+noncestr+"&timestamp="+ timestamp +"&url="+pageUrl;
//
//        String signatrue = DigestUtils.sha1Hex(str);
//
//
//        return WechatConfigDTO.builder().signature(signatrue).accessToken(wechatUserAuthDTO.getAccess_token()).nonceStr(noncestr).ticket(wechatCodeDTO.getTicket()).timestap(timestamp).build();
//    }


//    public String downloadMedia(String accessToken, String mediaId) {
//
//        final String APPLICATION_PDF = "image/png";
//        HttpHeaders headers = new HttpHeaders();
//
//        List list = new ArrayList<>();
//        list.add(MediaType.valueOf(APPLICATION_PDF));
//        headers.setAccept(list);
//
//        String downloadUrl=String.format("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=%s&media_id=%s",accessToken,mediaId);
//
//        ResponseEntity<byte[]> response = restTemplate.exchange(downloadUrl, HttpMethod.GET, new HttpEntity<byte[]>(headers), byte[].class);
//        byte[] result = response.getBody();
//
//        InputStream inputStream = new ByteArrayInputStream(result);
//
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType("image/png");
//
//        OssResult ossResult= fileStorage.ossUpload(Sequences.get32UUID().substring(0,8)+".png",inputStream,metadata);
//
//        return  ossResult.getUri();
//    }
}
