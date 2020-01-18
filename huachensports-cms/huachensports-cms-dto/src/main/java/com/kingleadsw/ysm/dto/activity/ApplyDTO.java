package com.kingleadsw.ysm.dto.activity;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "活动报名DTO")
public class ApplyDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "名字")
    private String applyName;

    @ApiModelProperty(value = "电话")
    private String mobile;

    @ApiModelProperty(value = "活动Id")
    private Long activityId;

    @ApiModelProperty(value = "活动名字")
    private String activityName;

    @ApiModelProperty(value = "小组id")
    private Long groupId;

    @ApiModelProperty(value = "小组名字")
    private String groupName;

    @ApiModelProperty(value = "用户id")
    private Long customerId;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @NotNull(message = "报名活动码不能为空")
    @ApiModelProperty(value = "活动码")
    private String activityCode;

    @ApiModelProperty(value = "报名时间")
    private Long createTime;
}
