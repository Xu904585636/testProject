package com.kingleadsw.ysm.wechat;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Auther: zhoujie
 * @Date: 2018/11/23 20:57
 * @Description:  微信用户实体
 */

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "微信用户对象")
public class WechatUserInfoDTO  extends BaseDTO {

    private  String     openid;

    private  String     nickname;

    private  String    sex;

    private   String  province;

    private  String  city;

    @ApiModelProperty(value = "用户是否注册 1.是 2.否")
    private  Integer sigin;

    private  String  country;

    private  String  headimgurl;

    private List<String>   privilege;

    private  String unionid;

}
