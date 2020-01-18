package com.kingleadsw.ysm.wechat;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhoujie
 * @Date: 2018/11/24 09:02
 * @Description:
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "微信用户认证对象")
public class WechatUserAuthDTO extends BaseDTO {

    private  String access_token;

    private  String  refresh_token;

    private  String  scope;

    private  String openid;

    private  String  expires_in;
    private  WechatUserInfoDTO userInfo;

}
