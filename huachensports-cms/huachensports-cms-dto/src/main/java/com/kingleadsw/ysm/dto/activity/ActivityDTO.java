package com.kingleadsw.ysm.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.security.acl.Group;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "活动DTO")
public class ActivityDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private  Long id;

    @ApiModelProperty(value = "活动名称")
    private String activityName;

    @ApiModelProperty(value = "活动图片")
    private String image;

    @ApiModelProperty(value = "活动banner图,多个用','隔开")
    private String bannerImages;

    @ApiModelProperty(value = "活动介绍")
    private String introduce;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    @ApiModelProperty(value = "分类名字")
    private String categoryName;

    @ApiModelProperty(value = "分享人数")
    private Integer shareNumber;

    @ApiModelProperty(value = "报名人数")
    private Integer applyCount;

    @ApiModelProperty(value = "收藏人数")
    private Integer collectionCount;

    @ApiModelProperty(value = "可报名人数,0为无限制")
    private Integer totalNumber;

    @ApiModelProperty(value = "是否已报名,1-已报名,0未报名")
    private Integer hasApply;

    @ApiModelProperty(value = "活动地址")
    private String address;

    @ApiModelProperty(value = "地区所在的区")
    private String area;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "维度")
    private String latitude;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "活动状态,1-未开始,2-进行中,3-已结束")
    private Integer status;

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

    private List<ApplyDTO> applyList;

    @ApiModelProperty(value = "用户id,用于查询我报名的活动时使用")
    private Long customerId;

    @ApiModelProperty(value = "活动状态,1-未结束，2-已结束,用于查询我报名的活动时使用")
    private Integer activityType;

    @ApiModelProperty(value = "报名人电话")
    private String applyName;

    @ApiModelProperty(value = "报名人电话")
    private String applyMobile;

    @ApiModelProperty(value = "队伍名字")
    private String applyGroupName;

    @ApiModelProperty(value = "是否限制人数,1-限制,2-不限制")
    private Integer isLimit;
    
    @ApiModelProperty(value = "活动下的分组信息")
    private  List<GroupDTO> groupList;
}
