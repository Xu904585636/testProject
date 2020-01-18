package com.kingleadsw.ysm.base.sms;

/**
 * @Auther: zhoujie
 * @Date: 2018/10/29 19:36
 * @Description:

@Service
@Log4j2
public class DefaultSmsSender implements  ISmsSender  {

    @Autowired
    IAcsClient smsClient;

    private  static String signName="觅扣";

    @Override
    public SmsSendResultDTO send(SmsSendDTO sms) {
        SendSmsRequest request = new SendSmsRequest();

        request.setPhoneNumbers(sms.getPhone());
        if (Asserts.isNull(sms.getSignName())) {
            request.setSignName(signName);
        }
        request.setTemplateCode(sms.getTemplateCode());

        request.setTemplateParam(sms.getTemplateParam());

        SmsSendResultDTO.SmsSendResultDTOBuilder rs = SmsSendResultDTO.builder();
        try
        {
            SendSmsResponse res = this.smsClient.getAcsResponse(request);
            Integer succeed = 2;
            if (Objects.equal(res.getCode(), "OK")) {
                succeed =1;
            }
            return rs.succeed(succeed).bizId(res.getBizId()).responseMsg(Jsons.toJson(res)).build();
        }
        catch (Exception e)
        {
            log.error("send err", e);
            rs.responseMsg(Strings.substring(e.getMessage(), 0, 250));
        }
        return rs.succeed(2).build();
    }

    @Override
    public QuerySendDetailsResponse querySendDetails(PaginationDTO<SmsDetailQueryDTO, VoidEnum> page)
    {
        SmsDetailQueryDTO sdq = (SmsDetailQueryDTO)page.getCondition();

        QuerySendDetailsRequest request = new QuerySendDetailsRequest();

        request.setPhoneNumber(sdq.getPhone());

        request.setBizId(sdq.getBizId());

        request.setSendDate(Dates.format(sdq.getSendTime(), "yyyyMMdd"));

        request.setPageSize(Long.valueOf(page.getSize().intValue()));

        request.setCurrentPage(Long.valueOf(page.getCurrent().intValue()));
        try
        {
            this.smsClient.getAcsResponse(request);
        }
        catch (Exception e)
        {
            log.error("querySendDetails err" + e);
            throw new UnknowException(Integer.valueOf(500007), "查询短信详情失败");
        }
        return null;
    }
}
 */