package com.kingleadsw.ysm.dto.activity;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "活动小组DTO")
public class GroupDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "活动id")
    private Long activityId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "小组最大人数")
    private Integer maxNumber;

    @ApiModelProperty(value = "小组已报名人数")
    private Integer applyNumber;

}
