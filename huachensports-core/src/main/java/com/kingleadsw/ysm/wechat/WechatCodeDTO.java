package com.kingleadsw.ysm.wechat;


import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhoujie
 * @Date: 2018/11/24 10:23
 * @Description:
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "微信用户认证对象")
public class WechatCodeDTO extends BaseDTO {

   @ApiModelProperty(value = "用户凭证")
   private  String code;

   private  String state;

   private  String  errcode;

   private  String  errmsg;

   private  String ticket;

   private  String expires_in;

   @ApiModelProperty(value = "id")
   private  Long  id;

   @ApiModelProperty(value = "昵称")
   private  String nickName;

   @ApiModelProperty(value = "微信openId")
   private  String openId;

   @ApiModelProperty(value = "机构id")
   private  Long  shopId;

   @ApiModelProperty(value = "酒店id")
   private  Long  hotelId;

   @ApiModelProperty(value = "手机号")
   private  String mobile;

   @ApiModelProperty(value = "头像")
   private  String headUrl;

   @ApiModelProperty(value = "类型 1.普通用户 2.平台用户 3.商家用户")
   private  Integer type;

   @ApiModelProperty(value = "登录账号(预留字段)")
   private  String userName;

   @ApiModelProperty(value = "登录密码(预留字段)")
   private  String password;

   @ApiModelProperty(value = "性别")
   private  Integer sex;


}
