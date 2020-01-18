package com.kingleadsw.ysm.dao.activity;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import com.kingleadsw.ysm.po.activity.GroupPO;

public interface IActivityMapper extends IBaseMapper<ActivityPO> {

    List<ActivityPO> getByPages(Page<ActivityPO> poPage, @Param("cm") Map<String, Object> condition);

    ActivityPO getActivityInfo(Long id);

    List<ActivityPO> getApplyActivity(Page<ActivityPO> poPage, @Param("cm") Map<String, Object> condition);

    List<ActivityPO> getCollectionActivity(Page<ActivityPO> poPage, @Param("cm") Map<String, Object> condition);

   
}
