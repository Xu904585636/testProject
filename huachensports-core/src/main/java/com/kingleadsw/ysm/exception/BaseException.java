package com.kingleadsw.ysm.exception;


import com.kingleadsw.ysm.constants.Status;
import com.kingleadsw.ysm.constants.StatusCodeType;

import java.util.Arrays;


public abstract class BaseException
        extends RuntimeException
{
    private Integer code;
    private String[] params;
    private String desc;

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $code = getCode();result = result * 59 + ($code == null ? 43 : $code.hashCode());result = result * 59 + Arrays.deepHashCode(getParams());Object $desc = getDesc();result = result * 59 + ($desc == null ? 43 : $desc.hashCode());return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof BaseException;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof BaseException)) {
            return false;
        }
        BaseException other = (BaseException)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$code = getCode();Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) {
            return false;
        }
        if (!Arrays.deepEquals(getParams(), other.getParams())) {
            return false;
        }
        Object this$desc = getDesc();Object other$desc = other.getDesc();return this$desc == null ? other$desc == null : this$desc.equals(other$desc);
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String toString()
    {
        return "StatusException(code=" + getCode() + ", params=" + Arrays.deepToString(getParams()) + ", desc=" + getDesc() + ")";
    }

    public Integer getCode()
    {
        return this.code;
    }

    public String[] getParams()
    {
        return this.params;
    }

    public String getDesc()
    {
        return this.desc;
    }

    public BaseException(Integer code)
    {
        setCode(code);
    }

    public BaseException(Integer code, String desc)
    {
        setCode(code);
        setDesc(desc);
    }

    public BaseException(Integer code, String desc, String... params)
    {
        setCode(code);
        setDesc(desc);
        setParams(params);
    }

    public BaseException(Status status)
    {
        setCode(status.getCode());
        setDesc(status.getDesc());
        setParams(status.getParams());
    }

    public BaseException setParams(String... params)
    {
        this.params = params;
        return this;
    }

    protected abstract StatusCodeType getStatusCodeType();
}