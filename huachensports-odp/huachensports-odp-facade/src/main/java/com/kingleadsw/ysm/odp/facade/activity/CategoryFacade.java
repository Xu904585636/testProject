package com.kingleadsw.ysm.odp.facade.activity;

import com.kingleadsw.ysm.api.activity.IActivityCategoryService;
import com.kingleadsw.ysm.api.activity.IActivityService;
import com.kingleadsw.ysm.api.activity.IGroupService;
import com.kingleadsw.ysm.api.activity.IScoreService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.constants.ServerCode;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.dto.activity.ActivityDTO;
import com.kingleadsw.ysm.dto.activity.GroupDTO;
import com.kingleadsw.ysm.dto.activity.ScoreDTO;
import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.odp.vo.activity.ActivityVO;
import com.kingleadsw.ysm.utils.Asserts;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryFacade {

    @Autowired
    private IActivityCategoryService activityCategoryService;



    /**
     * 删除分类
     * @param categoryId
     */
    public void delCategory(Long categoryId){
        activityCategoryService.delCategory(categoryId);
    }


    /**
     * 获取活动分类列表
     * @param paginationVO
     * @return
     */
    public PageDTO<ActivityCategoryDTO> getCategoryList(PaginationDTO<ActivityCategoryDTO, VoidEnum> paginationVO) {
        PageDTO<ActivityCategoryDTO> pageDTO = activityCategoryService.getCategoryList(paginationVO);
        return pageDTO;
    }

    /**
     * 根据是否存在分类Id修改或者新增分类
     * @param activityCategoryDTO
     * @return
     */
    public void editCategory(ActivityCategoryDTO activityCategoryDTO){
        if(Asserts.isNotNullOrEmpty(activityCategoryDTO.getId())){       //修改
            activityCategoryService.updateCategory(activityCategoryDTO);
        }else{                                                           //新增
            activityCategoryService.insertCategory(activityCategoryDTO);
        }
    }

}
