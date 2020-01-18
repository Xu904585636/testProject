package com.kingleadsw.ysm.api.activity;

import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.dto.activity.ActivityDTO;

import java.util.List;

public interface IActivityCategoryService {

    /**
     * 获取所有分类列表
     * @return
     */
    List<ActivityCategoryDTO> getCategoryList();

    /**
     * 分页获取分类列表
     * @param paginationVO
     * @return
     */
    PageDTO<ActivityCategoryDTO> getCategoryList(PaginationDTO<ActivityCategoryDTO, VoidEnum> paginationVO);

    /**
     * 修改活动信息
     * @param activityCategoryDTO
     */
    void updateCategory(ActivityCategoryDTO activityCategoryDTO);

    /**
     * 新增活动信息
     * @param activityCategoryDTO
     * @return
     */
    void insertCategory(ActivityCategoryDTO activityCategoryDTO);

    /**
     * 删除活动
     * @param categoryId
     */
    void delCategory(Long categoryId);

}
