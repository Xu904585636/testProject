package com.kingleadsw.ysm.base.sms;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/29 18:47
 * @Description:
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SmsSendDTO {

    private String phone;

    private String templateCode;

    private String templateParam;

    private String signName;

}
