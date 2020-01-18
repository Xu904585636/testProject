package com.kingleadsw.ysm.api.activity;

import com.kingleadsw.ysm.dto.activity.GroupDTO;

import java.util.List;

public interface IGroupService {

    /**
     * 通过活动id查询分组列表
     * @param activityId
     * @return
     */
    List<GroupDTO> getGroupList(Long activityId);

    /**
     * 插入小组信息
     * @param groupDTO
     */
    void insertGroup(GroupDTO groupDTO);

    /**
     * 修改小组信息
     * @param groupDTO
     */
    void updateGroup(GroupDTO groupDTO);

    /**
     * 删除小组
     * @param groupId
     */
    void delGroup(Long groupId);
}
