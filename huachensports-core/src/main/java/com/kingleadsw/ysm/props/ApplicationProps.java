package com.kingleadsw.ysm.props;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: zhoujie
 * @Description:
 */
@Configuration
@ConfigurationProperties
public class ApplicationProps
{
    @Value("${application.name:''}")
    private String applicationName;
    @Value("${spring.http.multipart.maxFileSize:1Mb}")
    private String maxFileSize;
    @Value("${cache.namespace:walking}")
    private String cacheNamespace;
    @Value("${token.idle-time:30}")
    private int tokenIdleTime;

    public void setCacheNamespace(String cacheNamespace)
    {
        this.cacheNamespace = cacheNamespace;
    }

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $applicationName = getApplicationName();result = result * 59 + ($applicationName == null ? 43 : $applicationName.hashCode());Object $maxFileSize = getMaxFileSize();result = result * 59 + ($maxFileSize == null ? 43 : $maxFileSize.hashCode());Object $cacheNamespace = getCacheNamespace();result = result * 59 + ($cacheNamespace == null ? 43 : $cacheNamespace.hashCode());result = result * 59 + getTokenIdleTime();result = result * 59 + getTokenLiveTime();return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof ApplicationProps;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ApplicationProps)) {
            return false;
        }
        ApplicationProps other = (ApplicationProps)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$applicationName = getApplicationName();Object other$applicationName = other.getApplicationName();
        if (this$applicationName == null ? other$applicationName != null : !this$applicationName.equals(other$applicationName)) {
            return false;
        }
        Object this$maxFileSize = getMaxFileSize();Object other$maxFileSize = other.getMaxFileSize();
        if (this$maxFileSize == null ? other$maxFileSize != null : !this$maxFileSize.equals(other$maxFileSize)) {
            return false;
        }
        Object this$cacheNamespace = getCacheNamespace();Object other$cacheNamespace = other.getCacheNamespace();
        if (this$cacheNamespace == null ? other$cacheNamespace != null : !this$cacheNamespace.equals(other$cacheNamespace)) {
            return false;
        }
        if (getTokenIdleTime() != other.getTokenIdleTime()) {
            return false;
        }
        return getTokenLiveTime() == other.getTokenLiveTime();
    }

    public void setTokenLiveTime(int tokenLiveTime)
    {
        this.tokenLiveTime = tokenLiveTime;
    }

    public void setApplicationName(String applicationName)
    {
        this.applicationName = applicationName;
    }

    public void setMaxFileSize(String maxFileSize)
    {
        this.maxFileSize = maxFileSize;
    }

    public String toString()
    {
        return "ApplicationProps(applicationName=" + getApplicationName() + ", maxFileSize=" + getMaxFileSize() + ", cacheNamespace=" + getCacheNamespace() + ", tokenIdleTime=" + getTokenIdleTime() + ", tokenLiveTime=" + getTokenLiveTime() + ")";
    }

    public void setTokenIdleTime(int tokenIdleTime)
    {
        this.tokenIdleTime = tokenIdleTime;
    }

    public String getApplicationName()
    {
        return this.applicationName;
    }

    public String getMaxFileSize()
    {
        return this.maxFileSize;
    }

    public String getCacheNamespace()
    {
        return this.cacheNamespace;
    }

    public int getTokenIdleTime()
    {
        return this.tokenIdleTime;
    }

    @Value("${token.live-time:1440}")
    private int tokenLiveTime = 1440;

    public int getTokenLiveTime()
    {
        return this.tokenLiveTime;
    }
}
