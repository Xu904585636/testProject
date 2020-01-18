package com.kingleadsw.ysm.base.po;

import com.baomidou.mybatisplus.annotations.TableLogic;

import com.kingleadsw.ysm.utils.Dates;
import lombok.Data;

@Data
public class Entity {


    private  Long createId;

    private  Long  updateId;

    private  Long  createTime;

    private  Long  updateTime;

    @TableLogic
    private  Long  enabled;


    public Entity allEx()
    {
        Long times= Dates.now();

        setUpdateTime(times);
        setCreateTime(times);
        this.createId = Long.valueOf(1L);
        this.updateId = Long.valueOf(1L);

        this.enabled =1L;
        return this;
    }

    public Entity allEx(Long userId)
    {
        Long times=Dates.now();

        setUpdateTime(times);
        setCreateTime(times);
        setCreateId(userId);
        setUpdateId(userId);

        this.enabled = 1L;
        return this;
    }

    public Entity allTime()
    {
        Long times=Dates.now();
        setUpdateTime(times);
        setCreateTime(times);
        return this;
    }

    public Entity updated(Long userId)
    {
        setUpdateTime(Dates.now());
        setUpdateId(userId);
        return this;
    }

    public Entity disable(Long userId)
    {
        setUpdateTime(Dates.now());
        setUpdateId(userId);
        this.enabled = 2L;
        return this;
    }

}
