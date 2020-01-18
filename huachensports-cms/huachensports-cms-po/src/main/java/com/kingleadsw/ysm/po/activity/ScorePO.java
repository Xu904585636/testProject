package com.kingleadsw.ysm.po.activity;

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
@TableName("cms_score")
public class ScorePO extends BasePO {

    private String scoreUrl;

    private Long activityId;

}
