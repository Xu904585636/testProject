package com.kingleadsw.ysm.po.activity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.kingleadsw.ysm.base.po.BasePO;
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
@TableName("cms_activity")
public class ActivityPO extends BasePO {

    private String activityName;

    private Long categoryId;

    private String image;

    private String bannerImages;

    private String introduce;

    private Integer shareNumber;

    private String address;

    private String area;

    private String longitude;

    private String latitude;

    private BigDecimal price;

    private Integer sort;

    private String activityCode;

    private Long activityStartTime;

    private Long activityEndTime;

    private Long applyStartTime;

    private Long applyEndTime;

    private String organizerName;

    private String organizerAvatar;

    private String organizerMobile;

    private Integer groupNumber;

    @TableField(exist = false)
    private List<Map<String,Object>> groupInfoList;

    @TableField(exist = false)
    private Integer applyCount;

    @TableField(exist = false)
    private Integer collectionCount;

    @TableField(exist = false)
    private Integer activityType;

    @TableField(exist = false)
    private String applyName;

    @TableField(exist = false)
    private String applyMobile;

    @TableField(exist = false)
    private String applyGroupName;

    @TableField(exist = false)
    private Long customerId;

    @TableField(exist = false)
    private Integer hasApply;

    @TableField(exist = false)
    private String categoryName;

    private Integer isLimit;
}
