package com.kingleadsw.ysm.odp.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "用户登录后返回对象")
public class LoginVO {

    @ApiModelProperty(value = "用户名称")
    private  String reqno;

    @ApiModelProperty(value = "用户姓名")
    private  String  userName;

    @ApiModelProperty(value = "用户ID")
    private  Long userId;

    @ApiModelProperty(value = "登录名")
    private  String  loginName;
    
    @ApiModelProperty(value = "头像")
    private String avatar;
 
}
