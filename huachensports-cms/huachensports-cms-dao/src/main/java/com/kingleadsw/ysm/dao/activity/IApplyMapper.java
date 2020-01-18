package com.kingleadsw.ysm.dao.activity;

import com.baomidou.mybatisplus.plugins.Page;
import com.kingleadsw.ysm.base.mapper.IBaseMapper;
import com.kingleadsw.ysm.po.activity.ActivityPO;
import com.kingleadsw.ysm.po.activity.ApplyPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface IApplyMapper extends IBaseMapper<ApplyPO> {

    /**
     * 查询活动报名列表
     * @param applyPO
     * @return
     */
    List<ApplyPO> selectApplyList(ApplyPO applyPO);

    /**
     * 分页查询报名信息
     * @return
     */
    List<ApplyPO> getApplyList(Page<ApplyPO> poPage, @Param("cm") Map<String, Object> condition);
}
