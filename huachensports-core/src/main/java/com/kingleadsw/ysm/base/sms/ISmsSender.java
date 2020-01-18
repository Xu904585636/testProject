package com.kingleadsw.ysm.base.sms;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.kingleadsw.ysm.base.VoidEnum;
import com.kingleadsw.ysm.base.dto.PaginationDTO;


/**
 * @Auther: zhoujie
 * @Date: 2018/10/29 19:29
 * @Description:  the Sms sends
 */
public interface ISmsSender {

     SmsSendResultDTO send(SmsSendDTO paramSmsSendDTO);

    QuerySendDetailsResponse querySendDetails(PaginationDTO<SmsDetailQueryDTO, VoidEnum> paramPaginationDTO);


}
