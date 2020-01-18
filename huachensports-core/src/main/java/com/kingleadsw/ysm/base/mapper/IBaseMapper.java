package com.kingleadsw.ysm.base.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.kingleadsw.ysm.base.po.BasePO;


public abstract interface IBaseMapper<T extends BasePO>
        extends BaseMapper<T>
{

}