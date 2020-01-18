package com.kingleadsw.ysm.service.user;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dao.user.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingleadsw.ysm.api.user.IUserService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.dto.user.UserDTO;
import com.kingleadsw.ysm.po.user.UserPO;
import com.kingleadsw.ysm.utils.ObjectCopys;

@Service
public class UserService extends BaseService<UserPO> implements IUserService {

	@Autowired
	private IUserMapper userMapper;

	@Override
	public UserDTO getById(Long id) {
		UserPO user=this.getMapper().selectById(id);
		return ObjectCopys.maping(user, UserDTO.class);
	}
	
	@Override
	public UserDTO getByLoginName(String loginName) {
		
		List<UserPO> users=this.getMapper().selectList(wrapper().eq("login_name", loginName));
		if(users.isEmpty()) {
			return null;
		}else {
			return ObjectCopys.maping(users.get(0), UserDTO.class);
		}
		 
	}
	
	@Override
	public UserDTO getByParams(UserDTO user) {
		 
		return null;
	}

    @Override
    public PageDTO<UserDTO> getUserList(PaginationDTO<UserDTO, VoidEnum> paginationDTO) {
		Page<UserPO> poPage = this.getPage(paginationDTO);

		List<UserPO> list = userMapper.getUserList(poPage, poPage.getCondition());

		return this.getPage(poPage, ObjectCopys.mappingAll(ObjectCopys.mappingAll(list, UserDTO.class), UserDTO.class));
    }

    
    @Override
    public Integer edit(UserDTO user) {
    	UserPO po=ObjectCopys.maping(user, UserPO.class);
    	return  this.getMapper().updateById(po);
    }
}
