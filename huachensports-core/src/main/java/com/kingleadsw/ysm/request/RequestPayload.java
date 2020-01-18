package com.kingleadsw.ysm.request;


import com.kingleadsw.ysm.base.dto.BaseDTO;

/**
 * @Auther: zhoujie
 * @Description:
 */
public class RequestPayload extends BaseDTO
{
    private String traceId;
    private Long userId;
    private String ip;
    private String token;
    private String serialNo;
    private String uri;
    private String className;
    private String method;
    private String reqBody;
    private String resBody;
    private Byte deviceType;
    private Byte platformType;
    private String channelSource;
    private Integer httpCode;
    private Long duration;
    private String deviceNo;
    private String userAgent;
    private String contentType;
    private String queryString;
    private Long startTime;
    private String platformCode;
    private Integer optType;
    private String optName;
    private Byte succeed;
    private String url;

    public void setIp(String ip)
    {
        this.ip = ip;
    }


    public void setUrl(String url)
    {
        this.url = url;
    }

    public void setSucceed(Byte succeed)
    {
        this.succeed = succeed;
    }

    public void setOptName(String optName)
    {
        this.optName = optName;
    }

    public void setOptType(Integer optType)
    {
        this.optType = optType;
    }

    public void setPlatformCode(String platformCode)
    {
        this.platformCode = platformCode;
    }

    public void setStartTime(Long startTime)
    {
        this.startTime = startTime;
    }

    public void setQueryString(String queryString)
    {
        this.queryString = queryString;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public void setUserAgent(String userAgent)
    {
        this.userAgent = userAgent;
    }

    public void setDeviceNo(String deviceNo)
    {
        this.deviceNo = deviceNo;
    }

    public void setDuration(Long duration)
    {
        this.duration = duration;
    }

    public void setHttpCode(Integer httpCode)
    {
        this.httpCode = httpCode;
    }

    public void setChannelSource(String channelSource)
    {
        this.channelSource = channelSource;
    }

    public void setPlatformType(Byte platformType)
    {
        this.platformType = platformType;
    }

    public void setDeviceType(Byte deviceType)
    {
        this.deviceType = deviceType;
    }

    public void setResBody(String resBody)
    {
        this.resBody = resBody;
    }

    public void setReqBody(String reqBody)
    {
        this.reqBody = reqBody;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public void setClassName(String className)
    {
        this.className = className;
    }

    public void setUri(String uri)
    {
        this.uri = uri;
    }

    public void setSerialNo(String serialNo)
    {
        this.serialNo = serialNo;
    }

    public void setTraceId(String traceId)
    {
        this.traceId = traceId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public static class RequestPayloadBuilder
    {
        private String traceId;
        private Long userId;
        private String ip;
        private String token;
        private String serialNo;
        private String uri;
        private String className;
        private String method;
        private String reqBody;
        private String resBody;
        private Byte deviceType;
        private Byte platformType;
        private String channelSource;
        private Integer httpCode;
        private Long duration;
        private String deviceNo;
        private String userAgent;
        private String contentType;
        private String queryString;
        private Long startTime;
        private String platformCode;
        private Integer optType;
        private String optName;
        private Byte succeed;
        private String url;

        public String toString()
        {
            return "RequestPayload.RequestPayloadBuilder(traceId=" + this.traceId + ", userId=" + this.userId + ", ip=" + this.ip + ", token=" + this.token + ", serialNo=" + this.serialNo + ", uri=" + this.uri + ", className=" + this.className + ", method=" + this.method + ", reqBody=" + this.reqBody + ", resBody=" + this.resBody + ", deviceType=" + this.deviceType + ", platformType=" + this.platformType + ", channelSource=" + this.channelSource + ", httpCode=" + this.httpCode + ", duration=" + this.duration + ", deviceNo=" + this.deviceNo + ", userAgent=" + this.userAgent + ", contentType=" + this.contentType + ", queryString=" + this.queryString + ", startTime=" + this.startTime + ", platformCode=" + this.platformCode + ", optType=" + this.optType + ", optName=" + this.optName + ", succeed=" + this.succeed + ", url=" + this.url + ")";
        }

        public RequestPayload build()
        {
            return new RequestPayload(this.traceId, this.userId, this.ip, this.token, this.serialNo, this.uri, this.className, this.method, this.reqBody, this.resBody, this.deviceType, this.platformType, this.channelSource, this.httpCode, this.duration, this.deviceNo, this.userAgent, this.contentType, this.queryString, this.startTime, this.platformCode, this.optType, this.optName, this.succeed, this.url);
        }

        public RequestPayloadBuilder url(String url)
        {
            this.url = url;return this;
        }

        public RequestPayloadBuilder succeed(Byte succeed)
        {
            this.succeed = succeed;return this;
        }

        public RequestPayloadBuilder optName(String optName)
        {
            this.optName = optName;return this;
        }

        public RequestPayloadBuilder optType(Integer optType)
        {
            this.optType = optType;return this;
        }

        public RequestPayloadBuilder platformCode(String platformCode)
        {
            this.platformCode = platformCode;return this;
        }

        public RequestPayloadBuilder startTime(Long startTime)
        {
            this.startTime = startTime;return this;
        }

        public RequestPayloadBuilder queryString(String queryString)
        {
            this.queryString = queryString;return this;
        }

        public RequestPayloadBuilder contentType(String contentType)
        {
            this.contentType = contentType;return this;
        }

        public RequestPayloadBuilder userAgent(String userAgent)
        {
            this.userAgent = userAgent;return this;
        }

        public RequestPayloadBuilder deviceNo(String deviceNo)
        {
            this.deviceNo = deviceNo;return this;
        }

        public RequestPayloadBuilder duration(Long duration)
        {
            this.duration = duration;return this;
        }

        public RequestPayloadBuilder httpCode(Integer httpCode)
        {
            this.httpCode = httpCode;return this;
        }

        public RequestPayloadBuilder channelSource(String channelSource)
        {
            this.channelSource = channelSource;return this;
        }

        public RequestPayloadBuilder platformType(Byte platformType)
        {
            this.platformType = platformType;return this;
        }

        public RequestPayloadBuilder deviceType(Byte deviceType)
        {
            this.deviceType = deviceType;return this;
        }

        public RequestPayloadBuilder resBody(String resBody)
        {
            this.resBody = resBody;return this;
        }

        public RequestPayloadBuilder reqBody(String reqBody)
        {
            this.reqBody = reqBody;return this;
        }

        public RequestPayloadBuilder method(String method)
        {
            this.method = method;return this;
        }

        public RequestPayloadBuilder className(String className)
        {
            this.className = className;return this;
        }

        public RequestPayloadBuilder uri(String uri)
        {
            this.uri = uri;return this;
        }

        public RequestPayloadBuilder serialNo(String serialNo)
        {
            this.serialNo = serialNo;return this;
        }

        public RequestPayloadBuilder token(String token)
        {
            this.token = token;return this;
        }

        public RequestPayloadBuilder ip(String ip)
        {
            this.ip = ip;return this;
        }

        public RequestPayloadBuilder userId(Long userId)
        {
            this.userId = userId;return this;
        }

        public RequestPayloadBuilder traceId(String traceId)
        {
            this.traceId = traceId;return this;
        }
    }

    public static RequestPayloadBuilder builder()
    {
        return new RequestPayloadBuilder();
    }

    public RequestPayload(String traceId, Long userId, String ip, String token, String serialNo, String uri, String className, String method, String reqBody, String resBody, Byte deviceType, Byte platformType, String channelSource, Integer httpCode, Long duration, String deviceNo, String userAgent, String contentType, String queryString, Long startTime, String platformCode, Integer optType, String optName, Byte succeed, String url)
    {
        this.traceId = traceId;this.userId = userId;this.ip = ip;this.token = token;this.serialNo = serialNo;this.uri = uri;this.className = className;this.method = method;this.reqBody = reqBody;this.resBody = resBody;this.deviceType = deviceType;this.platformType = platformType;this.channelSource = channelSource;this.httpCode = httpCode;this.duration = duration;this.deviceNo = deviceNo;this.userAgent = userAgent;this.contentType = contentType;this.queryString = queryString;this.startTime = startTime;this.platformCode = platformCode;this.optType = optType;this.optName = optName;this.succeed = succeed;this.url = url;
    }

    public String getTraceId()
    {
        return this.traceId;
    }

    public Long getUserId()
    {
        return this.userId;
    }

    public String getIp()
    {
        return this.ip;
    }

    public String getToken()
    {
        return this.token;
    }

    public String getSerialNo()
    {
        return this.serialNo;
    }

    public String getUri()
    {
        return this.uri;
    }

    public String getClassName()
    {
        return this.className;
    }

    public String getMethod()
    {
        return this.method;
    }

    public String getReqBody()
    {
        return this.reqBody;
    }

    public String getResBody()
    {
        return this.resBody;
    }

    public Byte getDeviceType()
    {
        return this.deviceType;
    }

    public Byte getPlatformType()
    {
        return this.platformType;
    }

    public String getChannelSource()
    {
        return this.channelSource;
    }

    public Integer getHttpCode()
    {
        return this.httpCode;
    }

    public Long getDuration()
    {
        return this.duration;
    }

    public String getDeviceNo()
    {
        return this.deviceNo;
    }

    public String getUserAgent()
    {
        return this.userAgent;
    }

    public String getContentType()
    {
        return this.contentType;
    }

    public String getQueryString()
    {
        return this.queryString;
    }

    public Long getStartTime()
    {
        return this.startTime;
    }

    public String getPlatformCode()
    {
        return this.platformCode;
    }

    public Integer getOptType()
    {
        return this.optType;
    }

    public String getOptName()
    {
        return this.optName;
    }

    public Byte getSucceed()
    {
        return this.succeed;
    }

    public String getUrl()
    {
        return this.url;
    }

    public RequestPayload() {}
}
