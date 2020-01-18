package com.kingleadsw.ysm.mtp.vo.activity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "活动VO")
public class ActivityVO {

    @ApiModelProperty(value = "id")
    private  Long id;

    @ApiModelProperty(value = "活动名称")
    private  String activityName;

    @ApiModelProperty(value = "分类id")
    private  Long categoryId;

    @ApiModelProperty(value = "活动状态,1-未结束，2-已结束,用于查询我报名的活动时使用")
    private Integer activityType;

    @ApiModelProperty(value = "是否已经报名该活动,1-已报名,0未报名")
    private Integer hasApply;

}
