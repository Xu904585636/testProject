package com.kingleadsw.ysm.po.collection;

import com.baomidou.mybatisplus.annotations.TableName;
import com.kingleadsw.ysm.base.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("cms_collection")
public class CollectionPO extends BasePO {

    private Long customerId;

    private Long activityId;

}
