package com.kingleadsw.ysm.dto.customer;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "微信用户DTO")
public class CustomerDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名字")
    private String userName;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "性别1男,2女")
    private Integer sex;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "用户token")
    private String token;

    @NotNull(message = "用户openid不能为空")
    @ApiModelProperty(value = "用户openId")
    private String openid;

    @ApiModelProperty(value = "创建时间")
    private Long createTime;

}
