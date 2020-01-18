package com.kingleadsw.ysm.odp.facade.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingleadsw.ysm.api.user.IUserService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.dto.user.UserDTO;
import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.need.token.TokenService;
import com.kingleadsw.ysm.odp.vo.user.LoginReqVO;
import com.kingleadsw.ysm.odp.vo.user.LoginVO;
import com.kingleadsw.ysm.odp.vo.user.UserVO;
import com.kingleadsw.ysm.qiniucloud.MD5Util;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;

@Service
public class UserFacade {

	@Autowired
	private IUserService userService;
	
    @Autowired
    TokenService tokenService;

	
	public LoginVO login(LoginReqVO reqVo) {
		UserDTO result=userService.getByLoginName(reqVo.getLoginName());
		if(result==null) {
			throw new UnknowException(ServerCode.Unauthorized.ACCOUNT_INVALID,"账号或密码错误");
		}else if(!result.getPassword().equals(reqVo.getPassword())){
			throw new UnknowException(ServerCode.Unauthorized.ACCOUNT_INVALID,"账号或密码错误");
		}
	    LoginVO loginVO= ObjectCopys.maping(result,LoginVO.class);
        loginVO.setUserId(result.getId());
        loginVO.setReqno(tokenService.addToken(result.getId()));
        RequestCache.get().setUserId(result.getId());
        return loginVO;
	}


	/**
	 * 获取用户列表
	 * @param paginationDTO
	 * @return
	 */
	public PageDTO<UserDTO> getUserList(PaginationDTO<UserDTO, VoidEnum> paginationDTO) {
		return userService.getUserList(paginationDTO);
	}
	
	public void resetPwd(UserVO user) {
		String password=MD5Util.MD5Encode(user.getPassword(), "utf-8");
		Long userId=RequestCache.userId();
		UserDTO userDTO=userService.getById(userId);
		if(!userDTO.getPassword().equals(password)) {
			throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID, "登录秘密错误");
		}
		UserDTO exist=userService.getByLoginName(user.getLoginName());
		if(!exist.getId().equals(userId)) {
			throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID, "登录名已经存在");
		}
		String newPassword=MD5Util.MD5Encode(user.getNewPassword(), "utf-8");
		UserDTO editData=UserDTO.builder().loginName(user.getLoginName()).password(newPassword).id(userId).build();
		userService.edit(editData);
	}
}
