package com.kingleadsw.ysm.dto.collection;

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
@ApiModel(value = "收藏DTO")
public class CollectionDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "微信用户id")
    private Long customerId;

    @ApiModelProperty(value = "活动id")
    private Long activityId;

}
