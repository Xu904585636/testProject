package com.kingleadsw.ysm.constants;

import java.util.Arrays;

public class Status
{
    private Integer code;
    private String[] params;
    private String desc;

    public static class StatusBuilder
    {
        private Integer code;
        private String[] params;
        private String desc;

        public String toString()
        {
            return "Status.StatusBuilder(code=" + this.code + ", params=" + Arrays.deepToString(this.params) + ", desc=" + this.desc + ")";
        }

        public Status build()
        {
            return new Status(this.code, this.params, this.desc);
        }

        public StatusBuilder desc(String desc)
        {
            this.desc = desc;return this;
        }

        public StatusBuilder params(String[] params)
        {
            this.params = params;return this;
        }

        public StatusBuilder code(Integer code)
        {
            this.code = code;return this;
        }
    }

    public static StatusBuilder builder()
    {
        return new StatusBuilder();
    }

    public String toString()
    {
        return "Status(code=" + getCode() + ", params=" + Arrays.deepToString(getParams()) + ", desc=" + getDesc() + ")";
    }

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $code = getCode();result = result * 59 + ($code == null ? 43 : $code.hashCode());result = result * 59 + Arrays.deepHashCode(getParams());Object $desc = getDesc();result = result * 59 + ($desc == null ? 43 : $desc.hashCode());return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof Status;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Status)) {
            return false;
        }
        Status other = (Status)o;
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

    public Status(Integer code, String[] params, String desc)
    {
        this.code = code;this.params = params;this.desc = desc;
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

    public void setParams(String... params)
    {
        this.params = params;
    }

    public Status() {}
}
