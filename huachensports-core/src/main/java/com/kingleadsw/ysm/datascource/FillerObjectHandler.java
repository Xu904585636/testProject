package com.kingleadsw.ysm.datascource;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * zhoujie
 *
 * @author  mybatisplus自定义填充公共字段 ,即没有传的字段自动填充
 */
@Component
public class FillerObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = metaObject.getValue("createTime");
        Object createId = metaObject.getValue("createId");
        Object updateTime = metaObject.getValue("updateTime");
        Object updateId = metaObject.getValue("updateId");


        if (null == createTime) {
            metaObject.setValue("createTime", new Date().getTime());
        }
        if (null == createId) {
            metaObject.setValue("createId", 0L);
        }
        if (null == updateTime) {
            metaObject.setValue("updateTime", new Date().getTime());
        }
        if (null == updateId) {
            metaObject.setValue("updateId", 0L);
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {


        metaObject.setValue("updateTime", new Date().getTime());

        metaObject.setValue("updateId", 0L);
    }
}
