package com.kingleadsw.ysm.base.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/29 18:49
 * @Description:
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SmsSendResultDTO {

    private String bizId;

    private String responseMsg;

    private Integer succeed;

}
