package com.kingleadsw.ysm.base.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/29 18:52
 * @Description:
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SmsDetailQueryDTO {


    private String bizId;

    private String phone;

    private Long sendTime;

}
