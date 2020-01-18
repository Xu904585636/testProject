package com.kingleadsw.ysm.mtp.controller.customer;

import com.kingleadsw.ysm.base.controller.BaseController;
import com.kingleadsw.ysm.caches.RedisUtil;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.dto.customer.CustomerDTO;
import com.kingleadsw.ysm.mtp.facade.customer.CustomerFacade;
import com.kingleadsw.ysm.need.token.Tokens;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.result.ResultVO;
import com.kingleadsw.ysm.service.customer.CustomerService;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.wechat.WechatUtils;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@Api(tags = "微信用户相关")
public class CustomerController extends BaseController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    WechatUtils wechatUtils;

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerFacade customerFacade;

    @PostMapping("customer/login")
    @ResponseBody
    public ResultVO<String> login(@RequestBody String code) {
        try {
            String openid  = wechatUtils.getProgramUserInfo(code);
            if (Asserts.isNotNullOrEmpty(openid)) {
                return success(openid);
            } else {
                return success(ServerCode.BadRequest.BAD_REQUEST_INVALID, "微信授权获取用户openid失败");
            }
        } catch (Exception e) {
            log.error("微信授权失败，系统异常,error=" + e);
            return success(ServerCode.BadRequest.BAD_REQUEST_INVALID, "微信授权失败，系统异常");
        }
    }


    /**
     * 前端获取用户个人信息，并存储服务器
     * @return
     */
    @PostMapping("customer/register")
    @ResponseBody
    public ResultVO<CustomerDTO> register(@RequestBody @Validated CustomerDTO customerDTO) {
        CustomerDTO register = customerService.register(customerDTO);
        // 创建访问token
        String token = Tokens.enToken(register.getId(), RequestCache.get().getIp());
        register.setToken(token);
        return success(register);
    }


    /**
     * 解密用户小程序传过来的手机号码并更新用户手机号码
     *
     * @return
     */
    @PostMapping("/decryptPhone")
    @ResponseBody
    public ResultVO<String> decryptPhone(String openId, String encryptedData, String iv) {
        try {
            String key = "user_token" + openId;
            String sessionKey = redisTemplate.opsForValue().get(key).toString();
            if (Asserts.isEmpty(sessionKey)) {
                return success(ServerCode.BadRequest.BAD_REQUEST_INVALID, "微信授权失败，系统异常");
            }
            String mobile = customerFacade.updateCustomerMobileIfNeed(sessionKey, encryptedData, iv);
            return success(mobile);
        } catch (Exception e) {
            log.error("微信授权失败，系统异常,error=" + e);
            return success(ServerCode.BadRequest.BAD_REQUEST_INVALID, "微信授权失败，系统异常");
        }
    }


}
