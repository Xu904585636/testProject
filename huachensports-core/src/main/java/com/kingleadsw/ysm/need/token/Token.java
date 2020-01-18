package com.kingleadsw.ysm.need.token;

import lombok.Data;

/**
 * @Auther: zhoujie
 * @Description:
 */
@Data
public class Token
{
    private Long userId;
    private String ip;
    private Long createdTime;
    private byte deviceType;
    private String deviceNo;




    public static class TokenBuilder
    {
        private Long userId;
        private String ip;
        private Long createdTime;
        private byte deviceType;
        private String deviceNo;

        public String toString()
        {
            return "Token.TokenBuilder(userId=" + this.userId + ", ip=" + this.ip + ", createdTime=" + this.createdTime + ", deviceType=" + this.deviceType + ", deviceNo=" + this.deviceNo + ")";
        }

        public Token build()
        {
            return new Token(this.userId, this.ip, this.createdTime, this.deviceType, this.deviceNo);
        }

        public TokenBuilder deviceNo(String deviceNo)
        {
            this.deviceNo = deviceNo;return this;
        }

        public TokenBuilder deviceType(byte deviceType)
        {
            this.deviceType = deviceType;return this;
        }

        public TokenBuilder createdTime(Long createdTime)
        {
            this.createdTime = createdTime;return this;
        }

        public TokenBuilder ip(String ip)
        {
            this.ip = ip;return this;
        }

        public TokenBuilder userId(Long userId)
        {
            this.userId = userId;return this;
        }
    }

    public static TokenBuilder builder()
    {
        return new TokenBuilder();
    }

    public Token(Long userId, String ip, Long createdTime, byte deviceType, String deviceNo)
    {
        this.userId = userId;this.ip = ip;this.createdTime = createdTime;this.deviceType = deviceType;this.deviceNo = deviceNo;
    }


    public Token() {}
}
