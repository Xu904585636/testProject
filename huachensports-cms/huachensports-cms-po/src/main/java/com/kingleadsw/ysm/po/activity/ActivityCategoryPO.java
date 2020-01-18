package com.kingleadsw.ysm.po.activity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.kingleadsw.ysm.base.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("cms_activity_category")
public class ActivityCategoryPO extends BasePO {

    private  String categoryName;

    private  Integer sort;

}
