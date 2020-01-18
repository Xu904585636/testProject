package com.kingleadsw.ysm.po.activity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.kingleadsw.ysm.base.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("cms_group")
public class GroupPO extends BasePO {

    private String groupName;

    private Long activityId;

    private Integer sort;

    @TableField(exist = false)
    private Integer maxNumber;

    @TableField(exist = false)
    private Integer applyNumber;
    
    @TableField(exist = false)
    private String activityName;
}
