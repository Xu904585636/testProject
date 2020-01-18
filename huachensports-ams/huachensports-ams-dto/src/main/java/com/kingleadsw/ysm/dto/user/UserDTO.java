package com.kingleadsw.ysm.dto.user;

import com.kingleadsw.ysm.base.dto.BaseDTO;

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
@ApiModel(value = "管理端用户DTO")
public class UserDTO  extends BaseDTO{
	
	@ApiModelProperty("用户id")
	private Long id;
	
	@ApiModelProperty("用户名")
	private String userName;
	
	@ApiModelProperty("头像")
	private String avatar;
	
	@ApiModelProperty("手机号码")
	private String mobile;
	
	@ApiModelProperty("登录名")
	private String loginName;
	
	@ApiModelProperty("密码")
	private String password;
	
	
}
