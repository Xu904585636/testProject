package com.kingleadsw.ysm.http;

public enum HttpHead
{
    TOKEN("reqno", "令牌"),  SERIAL_NO("SerialNO", "流水号（防止重复提交）"),  DEVICE_TYPE("DeviceType", "设备类型 （1:PC 2:安卓 3: IOS 4: 微信安卓端 5：微信IOS端 ） "),  DEVICE_NO("DeviceNO", "设备号（唯一标示设备的编号，IMEI，MAC）"),  TRACE_ID("traceId", "请求跟踪编号，从接口返回的traceId获取，第一次请求请使用外网IP加时间搓毫秒：192.168.1.88_154333322211223"),  CHANNEL_SOURCE("channelSource", "推广来源");

    String code;
    String desc;

    private HttpHead(String code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    public String getCode()
    {
        return this.code;
    }

    public String getDesc()
    {
        return this.desc;
    }
}
