package com.kingleadsw.ysm.po.customer;

import com.baomidou.mybatisplus.annotations.TableName;
import com.kingleadsw.ysm.base.po.BasePO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("ams_customer")
public class CustomerPO extends BasePO {

    private String userName;

    private String mobile;

    private Integer sex;

    private String openid;

    private String nickname;

    private String avatar;

    private String token;

}
