package com.kingleadsw.ysm.po.activity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.kingleadsw.ysm.base.po.BasePO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("cms_apply")
public class ApplyPO extends BasePO {

    private String applyName;

    private String mobile;

    private Long activityId;

    private Long groupId;

    private Long customerId;

    @TableField(exist = false)
    private String avatar;

    @TableField(exist = false)
    private String activityName;

    @TableField(exist = false)
    private String groupName;

}
