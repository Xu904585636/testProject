package com.kingleadsw.ysm.service.activity;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Lists;
import com.kingleadsw.ysm.api.activity.IActivityService;
import com.kingleadsw.ysm.api.activity.IGroupService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dao.activity.IActivityMapper;
import com.kingleadsw.ysm.dao.activity.IApplyMapper;
import com.kingleadsw.ysm.dao.activity.IGroupMapper;
import com.kingleadsw.ysm.dto.activity.ActivityDTO;
import com.kingleadsw.ysm.dto.activity.ApplyDTO;
import com.kingleadsw.ysm.dto.activity.GroupDTO;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import com.kingleadsw.ysm.po.activity.ApplyPO;
import com.kingleadsw.ysm.po.activity.GroupPO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;

@Service
public class ActivityService extends BaseService<ActivityPO> implements IActivityService {

    @Autowired
    private IActivityMapper activityMapper;

    @Autowired
    private IApplyMapper applyMapper;

    @Autowired
    private IGroupService groupService;
    
    @Autowired
    private IGroupMapper groupMapper;

    @Override
    public PageDTO<ActivityDTO> getByPage(PaginationDTO<ActivityDTO, VoidEnum> paginationDTO) {

        Page<ActivityPO> poPage = this.getPage(paginationDTO);

        List<ActivityPO> list = activityMapper.getByPages(poPage, poPage.getCondition());

        List<ActivityDTO> activityList = ObjectCopys.mappingAll(list, ActivityDTO.class);

        Long nowTime = System.currentTimeMillis();

        if (activityList != null && activityList.size() > 0){
            //判断活动状态
            activityList.forEach(activity->{
            long applyStartTime = activity.getApplyStartTime();
            long applyEndTime = activity.getApplyEndTime();
            if(applyStartTime > nowTime){
                activity.setStatus(1);      //未开始
            }else if(nowTime > applyStartTime && nowTime < applyEndTime){
                activity.setStatus(2);      //进行中
            }else{
                activity.setStatus(3);      //已结束
            }
            });
        }
        return this.getPage(poPage, ObjectCopys.mappingAll(activityList, ActivityDTO.class));
    }


    @Override
    public ActivityDTO getActivityInfo(Long id) {
        ActivityPO activityPO = activityMapper.getActivityInfo(id);
        ActivityDTO activityDTO = ObjectCopys.maping(activityPO, ActivityDTO.class);
        //查询报名列表
        List<ApplyPO> applyList = applyMapper.selectApplyList(ApplyPO.builder().activityId(id).build());
        activityDTO.setApplyList(ObjectCopys.maping(applyList, ApplyDTO.class));

        //计算可报名总人数
        if (activityDTO.getGroupNumber() != null && activityDTO.getGroupNumber() > 0 && activityDTO.getIsLimit() != 2) {
            List<GroupDTO> groupList = groupService.getGroupList(id);
            Integer totalNumber = groupList.size() * activityDTO.getGroupNumber();
            activityDTO.setTotalNumber(totalNumber);
        } else {
            activityDTO.setTotalNumber(0);              //无限制
        }

        //判断活动状态
        Long nowTime = System.currentTimeMillis();
        long applyStartTime = activityDTO.getApplyStartTime();
        long applyEndTime = activityDTO.getApplyEndTime();
        if(applyStartTime > nowTime){
            activityDTO.setStatus(1);      //未开始
        }else if(nowTime > applyStartTime && nowTime < applyEndTime){
            activityDTO.setStatus(2);      //进行中
        }else{
            activityDTO.setStatus(3);      //已结束
        }

        return activityDTO;
    }


    @Override
    public ActivityDTO getActivity(Long id) {
        ActivityPO activityPO = activityMapper.selectById(id);
        return ObjectCopys.maping(activityPO,ActivityDTO.class);
    }

    @Override
    public PageDTO<ActivityDTO> getApplyActivity(PaginationDTO<ActivityDTO, VoidEnum> paginationDTO) {
        Page<ActivityPO> poPage = this.getPage(paginationDTO);

        List<ActivityPO> list = activityMapper.getApplyActivity(poPage, poPage.getCondition());

        return this.getPage(poPage, ObjectCopys.mappingAll(ObjectCopys.mappingAll(list, ActivityDTO.class), ActivityDTO.class));
    }

    @Override
    public PageDTO<ActivityDTO> getCollectionActivity(PaginationDTO<ActivityDTO, VoidEnum> paginationDTO) {
        Page<ActivityPO> poPage = this.getPage(paginationDTO);

        List<ActivityPO> list = activityMapper.getCollectionActivity(poPage, poPage.getCondition());

        List<ActivityDTO> activityList = ObjectCopys.mappingAll(list, ActivityDTO.class);

        Long nowTime = System.currentTimeMillis();

        if (activityList != null && activityList.size() > 0){
            //判断活动状态
            activityList.forEach(activity->{
                long applyStartTime = activity.getApplyStartTime();
                long applyEndTime = activity.getApplyEndTime();
                if(applyStartTime > nowTime){
                    activity.setStatus(1);      //未开始
                }else if(nowTime > applyStartTime && nowTime < applyEndTime){
                    activity.setStatus(2);      //进行中
                }else{
                    activity.setStatus(3);      //已结束
                }
            });
        }

        return this.getPage(poPage,activityList);
    }


    @Override
    public Integer getHasApply(ApplyDTO applyDTO) {
        ApplyPO applyPO = applyMapper.selectOne(ObjectCopys.maping(applyDTO,ApplyPO.class));
        if(applyPO != null && applyPO.getId()!= null){
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public void updateActivity(ActivityDTO activityDTO) {
        ActivityPO activityPO = ObjectCopys.maping(activityDTO, ActivityPO.class);
        activityMapper.update(activityPO,this.wrapper().eq("id", activityPO.getId()));
    }

    @Override
    public Long insertActivity(ActivityDTO activityDTO) {
        ActivityPO activityPO = ObjectCopys.maping(activityDTO, ActivityPO.class);
        activityPO.allEx(RequestCache.userId());
        activityMapper.insert(activityPO);
        return activityPO.getId();
    }

    @Override
    public void delActivity(Long activityId) {
        activityMapper.deleteById(activityId);
    }

    @Override
    public List<ActivityDTO> getAllList() {
        List<GroupPO> allList = groupMapper.getAllList();
        Map<Long,List<GroupPO>> map=allList.stream().collect(Collectors.groupingBy(GroupPO::getActivityId));
        List<ActivityDTO> list=Lists.newArrayList();
        
        ActivityDTO dto=null;
        for(Entry<Long,List<GroupPO>> entry:map.entrySet()) {
        	
        	List<GroupPO> childrens=entry.getValue();
        	GroupPO groupPo=childrens.get(0);
        	List<GroupDTO>groupList=ObjectCopys.maping(childrens, GroupDTO.class);
        	dto=ActivityDTO.builder().id(entry.getKey()).activityName(groupPo.getActivityName()).groupList(groupList).build();
        	list.add(dto);
        	
        }
        
        return list;
    }
}
