package com.kingleadsw.ysm.service.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.api.activity.IActivityCategoryService;
import com.kingleadsw.ysm.api.activity.IActivityService;
import com.kingleadsw.ysm.base.BaseService;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.dao.activity.IActivityCategoryMapper;
import com.kingleadsw.ysm.dto.activity.ActivityCategoryDTO;
import com.kingleadsw.ysm.dto.activity.ActivityDTO;
import com.kingleadsw.ysm.po.activity.ActivityCategoryPO;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import com.kingleadsw.ysm.request.RequestCache;
import com.kingleadsw.ysm.utils.ObjectCopys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityCategoryService extends BaseService<ActivityCategoryPO> implements IActivityCategoryService {

    @Autowired
    private IActivityCategoryMapper activityCategoryMapper;

    @Override
    public List<ActivityCategoryDTO> getCategoryList() {
        List<ActivityCategoryPO> list = activityCategoryMapper.selectList(this.wrapper().orderBy("sort", true));
        return ObjectCopys.mappingAll(list,ActivityCategoryDTO.class);
    }

    @Override
    public PageDTO<ActivityCategoryDTO> getCategoryList(PaginationDTO<ActivityCategoryDTO, VoidEnum> paginationDTO) {
        Page<ActivityCategoryPO> poPage = this.getPage(paginationDTO);
        List<ActivityCategoryPO> list = activityCategoryMapper.getByPages(poPage, poPage.getCondition());
        return this.getPage(poPage, ObjectCopys.mappingAll(list, ActivityCategoryDTO.class));
    }

    @Override
    public void updateCategory(ActivityCategoryDTO activityCategoryDTO) {
        ActivityCategoryPO activityCategoryPO = ObjectCopys.maping(activityCategoryDTO, ActivityCategoryPO.class);
        activityCategoryPO.allEx(RequestCache.userId());
        activityCategoryMapper.update(activityCategoryPO,wrapper().eq("id",activityCategoryPO.getId()));
    }

    @Override
    public void insertCategory(ActivityCategoryDTO activityCategoryDTO) {
        ActivityCategoryPO activityCategoryPO = ObjectCopys.maping(activityCategoryDTO, ActivityCategoryPO.class);
        activityCategoryPO.allEx(RequestCache.userId());
        activityCategoryMapper.insert(activityCategoryPO);
    }

    @Override
    public void delCategory(Long categoryId) {
        activityCategoryMapper.deleteById(categoryId);
    }
}
