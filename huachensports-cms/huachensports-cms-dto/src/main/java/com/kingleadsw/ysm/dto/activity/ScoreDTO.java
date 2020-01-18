package com.kingleadsw.ysm.dto.activity;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "活动报名DTO")
public class ScoreDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "成绩图片url")
    private String scoreUrl;

    @ApiModelProperty(value = "活动Id")
    private Long activityId;

    @ApiModelProperty(value = "活动名字")
    private String activityName;

}
