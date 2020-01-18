package com.kingleadsw.ysm.dao.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.po.activity.ActivityCategoryPO;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IActivityCategoryMapper extends IBaseMapper<ActivityCategoryPO> {

    List<ActivityCategoryPO> getByPages(Page<ActivityCategoryPO> poPage, @Param("cm") Map<String, Object> condition);
}
