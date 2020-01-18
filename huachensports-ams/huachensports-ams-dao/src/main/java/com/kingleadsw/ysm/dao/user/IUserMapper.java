package com.kingleadsw.ysm.dao.user;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.po.user.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IUserMapper extends IBaseMapper<UserPO> {

    List<UserPO> getUserList(Page<UserPO> poPage, @Param("cm") Map<String, Object> condition);
}
