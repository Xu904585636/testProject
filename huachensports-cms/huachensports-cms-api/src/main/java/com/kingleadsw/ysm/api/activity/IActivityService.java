package com.kingleadsw.ysm.api.activity;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.activity.ActivityDTO;
import com.kingleadsw.ysm.dto.activity.ApplyDTO;
import com.kingleadsw.ysm.dto.collection.CollectionDTO;

import java.util.List;
import java.util.Map;

public interface IActivityService {

    /**
     * 分页获取活动列表
     * @param paginationDTO
     * @return
     */
    PageDTO<ActivityDTO> getByPage(PaginationDTO<ActivityDTO, VoidEnum> paginationDTO);

    /**
     * 获取活动详情包含分享人数收藏人数和报名信息
     * @param id
     * @return
     */
    ActivityDTO getActivityInfo(Long id);

    /**
     * 获取活动基本详情
     * @param id
     * @return
     */
    ActivityDTO getActivity(Long id);


    /**
     * 获取我报名的活动
     * @param paginationDTO
     * @return
     */
    PageDTO<ActivityDTO> getApplyActivity(PaginationDTO<ActivityDTO, VoidEnum> paginationDTO);

    /**
     * 获取我收藏的活动
     * @param paginationDTO
     * @return
     */
    PageDTO<ActivityDTO> getCollectionActivity(PaginationDTO<ActivityDTO, VoidEnum> paginationDTO);

    /**
     * 查询是否已经报名该活动
     * @return
     */
    Integer getHasApply(ApplyDTO applyDTO);

    /**
     * 修改活动信息
     * @param activityDTO
     */
    void updateActivity(ActivityDTO activityDTO);

    /**
     * 新增活动信息
     * @param activityDTO
     * @return
     */
    Long insertActivity(ActivityDTO activityDTO);


    /**
     * 删除活动
     * @param activityId
     */
    void delActivity(Long activityId);


    /**
     * 查询所有包含分组的活动列表
     * @return
     */
    List<ActivityDTO> getAllList();

}
