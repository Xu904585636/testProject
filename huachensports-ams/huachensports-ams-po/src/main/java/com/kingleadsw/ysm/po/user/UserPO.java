package com.kingleadsw.ysm.po.user;

import com.baomidou.mybatisplus.annotations.TableName;
import com.kingleadsw.ysm.base.po.BasePO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("ams_user")
public class UserPO extends BasePO {
	
	private String userName;
	
	private String avatar;
	
	private String mobile;
	
	private String loginName;
	
	private String password;
	

}
