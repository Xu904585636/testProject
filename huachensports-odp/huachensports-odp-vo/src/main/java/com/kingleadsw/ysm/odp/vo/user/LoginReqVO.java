package com.kingleadsw.ysm.odp.vo.user;

import javax.validation.constraints.NotNull;

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
@ApiModel(value = "用户登录请求对象")
public class LoginReqVO {

	@ApiModelProperty("登录名")
	@NotNull(message="登录名不能为空")
	private String loginName;
	
	@ApiModelProperty("密码")
	@NotNull(message="密码不能为空")
	private String password;
}
