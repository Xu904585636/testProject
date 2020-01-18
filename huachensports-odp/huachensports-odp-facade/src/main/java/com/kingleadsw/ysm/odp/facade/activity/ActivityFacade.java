package com.kingleadsw.ysm.odp.facade.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.exception.UnknowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kingleadsw.ysm.api.activity.IActivityCategoryService;
import com.kingleadsw.ysm.api.activity.IActivityService;
import com.kingleadsw.ysm.api.activity.IGroupService;
import com.kingleadsw.ysm.api.activity.IScoreService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.dto.activity.ActivityDTO;
import com.kingleadsw.ysm.dto.activity.GroupDTO;
import com.kingleadsw.ysm.dto.activity.ScoreDTO;
import com.kingleadsw.ysm.odp.vo.activity.ActivityVO;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.ObjectCopys;

@Service
public class ActivityFacade {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IActivityCategoryService activityCategoryService;

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IScoreService scoreService;

    /**
     * 获取活动列表
     * @param paginationVO
     * @return
     */
    public PageDTO<ActivityDTO> getByActivityPage(PaginationDTO<ActivityVO, VoidEnum> paginationVO) {

        PaginationDTO<ActivityDTO, VoidEnum> paginationDTO = ObjectCopys.transferPagination(paginationVO, ActivityDTO.class);

        PageDTO<ActivityDTO> pageDTO = activityService.getByPage(paginationDTO);

        return pageDTO;
    }

    /**
     * 获取sort正序的分类列表
     * @return
     */
    public List<ActivityCategoryDTO> getCategoryList(){
        return activityCategoryService.getCategoryList();
    }

    /**
     * 获取活动详情
     * @param activityId
     * @return
     */
    public ActivityVO getActivityInfo(Long activityId){
        ActivityDTO activity = activityService.getActivity(activityId);
        ActivityVO activityVO = ObjectCopys.maping(activity, ActivityVO.class);
        List<GroupDTO> groupList = groupService.getGroupList(activityId);
        activityVO.setGroupList(groupList);
        return activityVO;
    }


    /**
     * 获取成绩详情
     * @param activityId
     * @return
     */
    public ScoreDTO getScoreInfo(Long activityId){
        ScoreDTO scoreInfo = scoreService.getScoreInfo(activityId);
        ActivityDTO activity = activityService.getActivity(activityId);
        scoreInfo.setActivityName(activity.getActivityName());
        return scoreInfo;
    }

    /**
     * 编辑活动成绩信息
     * @param scoreDTO
     */
    public void editScore(ScoreDTO scoreDTO){
        if(Asserts.isNotNullOrEmpty(scoreDTO.getId())){           //修改
            scoreService.updateScore(scoreDTO);
        }else{                                                    //新增
            scoreService.insertScore(scoreDTO);
        }
    }

    /**
     * 编辑活动信息
     * @param activityVO
     */
    @Transactional
    public void editActivity(ActivityVO activityVO){
        if(Asserts.isNotNullOrEmpty(activityVO.getId())){           //修改
            activityService.updateActivity(ObjectCopys.maping(activityVO,ActivityDTO.class));

            //判断活动是否已经开始,如果已经开始,则无法修改分组数量
            ActivityDTO activity = activityService.getActivity(activityVO.getId());
            if(activity.getActivityStartTime()<System.currentTimeMillis()){
                //查询分组信息,对比是否有新增或者删除小组
                List<GroupDTO> oldGroupList = groupService.getGroupList(activityVO.getId());
                List<GroupDTO> newGroupList = activityVO.getGroupList();
                if (oldGroupList.size() != newGroupList.size()){
                    //不可以新增或删除小组
                    throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID,"活动开始后,不可以新增或删除小组");
                }else{
                    int idCount = 0;
                    for (GroupDTO groupDTO : newGroupList) {
                        Long id = groupDTO.getId();
                        if(Asserts.isNotNullOrEmpty(id)){
                            idCount++;
                        }
                    }
                    if(idCount == oldGroupList.size()){
                        newGroupList.forEach(group->{
                            if(Asserts.isNotNullOrEmpty(group.getId())){
                                groupService.updateGroup(group);
                            }else{
                                group.setActivityId(activityVO.getId());
                                groupService.insertGroup(group);
                            }
                        });
                    }else{
                        //不可以新增或删除小组
                        throw new UnknowException(ServerCode.BadRequest.BAD_REQUEST_INVALID,"活动开始后,不可以新增或删除小组");
                    }
                }
            }else{

                //统计所有新的小组Id的集合
                List<Long> newIdList = new ArrayList<Long>();
                List<GroupDTO> newGroupList = activityVO.getGroupList();
                newGroupList.forEach(newGroup->{
                    Long id = newGroup.getId();
                    newIdList.add(id);
                });
                //如果是否所有的旧的小组Id都在新的小组Id中,将不在的进行删除
                List<GroupDTO> oldGroupList = groupService.getGroupList(activityVO.getId());
                oldGroupList.forEach(oldGroup->{
                    if(Asserts.isNotNullOrEmpty(oldGroup.getId()) && !newIdList.contains(oldGroup.getId())){
                        groupService.delGroup(oldGroup.getId());
                    }
                });
                //更新小组信息(根据id执行新增或者修改)
                newGroupList.forEach(group->{
                    if(Asserts.isNotNullOrEmpty(group.getId())){
                        groupService.updateGroup(group);
                    }else{
                        group.setActivityId(activityVO.getId());
                        groupService.insertGroup(group);
                    }
                });
            }


        }else{                                                      //新增
            Long activityId = activityService.insertActivity(ObjectCopys.maping(activityVO,ActivityDTO.class));
            activityVO.getGroupList().forEach(groupDTO -> {
                groupDTO.setActivityId(activityId);
                groupService.insertGroup(groupDTO);
            });
        }
    }

    /**
     * 删除活动
     * @param activityId
     */
    public void delActivity(Long activityId){
        activityService.delActivity(activityId);
    }


    /**
     * 查询所有活动信息(包含小组信息,用于查询报名信息使用)
     * @return
     */
    public List<ActivityDTO> getAllList(){
        List<ActivityDTO> allList = activityService.getAllList();
        return allList;
    }



}
