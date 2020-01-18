package com.kingleadsw.ysm.service.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingleadsw.ysm.api.activity.IGroupService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.dao.activity.IGroupMapper;
import com.kingleadsw.ysm.dto.activity.GroupDTO;
import com.kingleadsw.ysm.po.activity.GroupPO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;

@Service
public class GroupService extends BaseService<GroupPO> implements IGroupService {

    @Autowired
    private IGroupMapper groupMapper;

    @Override
    public List<GroupDTO> getGroupList(Long activityId) {
        List<GroupPO> groupList = groupMapper.getGroupList(activityId);
        return ObjectCopys.mappingAll(groupList,GroupDTO.class);
    }

    @Override
    public void insertGroup(GroupDTO groupDTO) {
        GroupPO groupPO = ObjectCopys.maping(groupDTO, GroupPO.class);
        groupPO.allEx(RequestCache.userId());
        groupMapper.insert(groupPO);
    }

    @Override
    public void updateGroup(GroupDTO groupDTO) {
        GroupPO groupPO = ObjectCopys.maping(groupDTO, GroupPO.class);
        groupPO.allEx(RequestCache.userId());
        groupMapper.update(groupPO,wrapper().eq("id",groupDTO.getId()));
    }

    @Override
    public void delGroup(Long groupId) {
        groupMapper.deleteById(groupId);
    }
}
