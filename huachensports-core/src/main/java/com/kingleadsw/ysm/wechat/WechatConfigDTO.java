package com.kingleadsw.ysm.wechat;


import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author: zhoujie
 * @Date: 2018/12/17
 * @Version 1.0
 */@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "微信配置对象")
public class WechatConfigDTO  extends BaseDTO {

     private  String timestap;

     private String nonceStr;

     private  String signature;

     private  String ticket;

     private  String  accessToken;


}
