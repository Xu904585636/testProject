package com.kingleadsw.ysm.api.user;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.user.UserDTO;

public interface IUserService {
	
	UserDTO getById(Long id);
	
	UserDTO getByLoginName(String loginName);
	
	UserDTO getByParams(UserDTO user);

	/**
	 * 获取用户列表
	 * @param paginationDTO
	 * @return
	 */
	PageDTO<UserDTO> getUserList(PaginationDTO<UserDTO, VoidEnum> paginationDTO);
	
	Integer edit(UserDTO user);
}
