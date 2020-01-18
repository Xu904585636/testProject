package com.kingleadsw.ysm.dto.activity;

import com.kingleadsw.ysm.base.dto.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


/*
CREATE TABLE `cms_activity` (
        `id` bigint(20) NOT NULL AUTO_INCREMENT,
        `activity_name` varchar(20) NOT NULL COMMENT '活动名称',
        `image` varchar(500) NOT NULL COMMENT '活动图片',
        `banner_images` varchar(2000) DEFAULT NULL COMMENT '活动轮播图片(多个用'',''隔开)',
        `introduce` varchar(2000) DEFAULT NULL COMMENT '活动介绍',
        `share_number` int(20) DEFAULT NULL COMMENT '分享次数',
        `address` varchar(200) DEFAULT NULL COMMENT '活动地址',
        `area` varchar(50) DEFAULT NULL COMMENT '地区',
        `longitude` varchar(20) DEFAULT NULL COMMENT '经度',
        `latitude` varchar(20) DEFAULT NULL COMMENT '纬度',
        `price` decimal(8,2) DEFAULT NULL COMMENT '价格',
        `sort` int(10) DEFAULT NULL COMMENT '排序',
        `activity_code` varchar(10) DEFAULT NULL COMMENT '活动编码',
        `activity_start_time` datetime DEFAULT NULL COMMENT '活动开始时间',
        `activity_end_time` datetime DEFAULT NULL COMMENT '活动结束时间',
        `apply_start_time` datetime DEFAULT NULL COMMENT '报名开始时间',
        `apply_end_time` datetime DEFAULT NULL COMMENT '报名结束时间',
        `organizer_name` varchar(20) DEFAULT NULL COMMENT '组织人名字',
        `organizer_phone` varchar(20) DEFAULT NULL COMMENT '组织人电话',
        `organizer_photo` varchar(500) DEFAULT NULL COMMENT '组织人头像',
        `group_number` int(10) DEFAULT NULL COMMENT '每组人数',
        `create_id` bigint(20) NOT NULL,
        `update_id` bigint(20) NOT NULL,
        `create_time` bigint(20) NOT NULL,
        `update_time` bigint(20) NOT NULL,
        `enabled` bigint(20) NOT NULL,
        PRIMARY KEY (`id`) USING BTREE
        ) ENGINE=InnoDB AUTO_INCREMENT=130 DEFAULT CHARSET=utf8mb4 COMMENT='活动表';

*/


@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ApiModel(value = "活动分类DTO")
public class ActivityCategoryDTO extends BaseDTO {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "排序")
    private Integer sort;

}
