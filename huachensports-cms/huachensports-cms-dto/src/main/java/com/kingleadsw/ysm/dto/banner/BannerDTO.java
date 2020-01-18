package com.kingleadsw.ysm.dto.banner;

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
@ApiModel(value = "滚动图DTO")
public class BannerDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "图片url")
    private String imageUrl;

    @ApiModelProperty(value = "排序")
    private Integer sort;


}
