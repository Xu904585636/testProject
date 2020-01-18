package com.kingleadsw.ysm.dao.activity;

import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.po.activity.GroupPO;

import java.util.List;

public interface IGroupMapper extends IBaseMapper<GroupPO> {

    List<GroupPO> getGroupList(Long activityId);
    
    List<GroupPO> getAllList();
}
