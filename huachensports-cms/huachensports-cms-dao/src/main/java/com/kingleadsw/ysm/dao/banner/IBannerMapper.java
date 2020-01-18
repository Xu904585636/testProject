package com.kingleadsw.ysm.dao.banner;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.po.activity.ActivityCategoryPO;
import com.kingleadsw.ysm.po.activity.ScorePO;
import com.kingleadsw.ysm.po.banner.BannerPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IBannerMapper extends IBaseMapper<BannerPO> {

    List<BannerPO> getByPages(Page<BannerPO> poPage, @Param("cm") Map<String, Object> condition);
}
