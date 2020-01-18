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
@ApiModel(value = "重置密码")
public class UserVO {

	@ApiModelProperty("当前密码")
	private String password;
	
	@ApiModelProperty("新密码")
	private String newPassword;
	
	@ApiModelProperty("登录账户")
	private String loginName;
}
