package com.kingleadsw.ysm.odp.vo.activity;

import com.kingleadsw.ysm.dto.activity.GroupDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "活动VO")
public class ActivityVO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "分类名字")
    private String categoryName;

    @ApiModelProperty(value = "活动图片")
    private String image;

    @ApiModelProperty(value = "活动banner图,多个用','隔开")
    private String bannerImages;

    @ApiModelProperty(value = "活动介绍")
    private String introduce;

    @ApiModelProperty(value = "活动地址")
    private String address;

    @ApiModelProperty(value = "地区所在的区")
    private String area;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "维度")
    private String latitude;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "活动码")
    private String activityCode;

    @ApiModelProperty(value = "活动开始时间")
    private Long activityStartTime;

    @ApiModelProperty(value = "活动结束时间")
    private Long activityEndTime;

    @ApiModelProperty(value = "报名开始时间")
    private Long applyStartTime;

    @ApiModelProperty(value = "报名结束时间")
    private Long applyEndTime;

    @ApiModelProperty(value = "组织人名字")
    private String organizerName;

    @ApiModelProperty(value = "组织人头像")
    private String organizerAvatar;

    @ApiModelProperty(value = "组织人电话")
    private String organizerMobile;

    @ApiModelProperty(value = "每组最大人数")
    private Integer groupNumber;

    @ApiModelProperty(value = "是否限制人数,1-限制,2-不限制")
    private Integer isLimit;

    private List<GroupDTO> groupList;

    private List<Map<String,Object>> groupInfoList;

}
