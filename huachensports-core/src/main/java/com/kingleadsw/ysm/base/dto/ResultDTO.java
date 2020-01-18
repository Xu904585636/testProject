package com.kingleadsw.ysm.base.dto;


import java.io.Serializable;

@Deprecated
public class ResultDTO<T> implements Serializable
{
    private Integer code;
    private T data;

    public void setData(T data)
    {
        this.data = data;
    }

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $code = getCode();result = result * 59 + ($code == null ? 43 : $code.hashCode());Object $data = getData();result = result * 59 + ($data == null ? 43 : $data.hashCode());Object $currentTimes = getCurrentTimes();result = result * 59 + ($currentTimes == null ? 43 : $currentTimes.hashCode());Object $msg = getMsg();result = result * 59 + ($msg == null ? 43 : $msg.hashCode());return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof ResultDTO;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResultDTO)) {
            return false;
        }
        ResultDTO<?> other = (ResultDTO)o;
        if (!other.canEqual(this)) {
            return false;
        }
        Object this$code = getCode();Object other$code = other.getCode();
        if (this$code == null ? other$code != null : !this$code.equals(other$code)) {
            return false;
        }
        Object this$data = getData();Object other$data = other.getData();
        if (this$data == null ? other$data != null : !this$data.equals(other$data)) {
            return false;
        }
        Object this$currentTimes = getCurrentTimes();Object other$currentTimes = other.getCurrentTimes();
        if (this$currentTimes == null ? other$currentTimes != null : !this$currentTimes.equals(other$currentTimes)) {
            return false;
        }
        Object this$msg = getMsg();Object other$msg = other.getMsg();return this$msg == null ? other$msg == null : this$msg.equals(other$msg);
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public void setCurrentTimes(Long currentTimes)
    {
        this.currentTimes = currentTimes;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String toString()
    {
        return "ResultDTO(code=" + getCode() + ", data=" + getData() + ", currentTimes=" + getCurrentTimes() + ", msg=" + getMsg() + ")";
    }

    public static class ResultDTOBuilder<T>
    {
        private Integer code;
        private T data;
        private Long currentTimes;
        private String msg;

        public String toString()
        {
            return "ResultDTO.ResultDTOBuilder(code=" + this.code + ", data=" + this.data + ", currentTimes=" + this.currentTimes + ", msg=" + this.msg + ")";
        }

        public ResultDTO<T> build()
        {
            return new ResultDTO(this.code, this.data, this.currentTimes, this.msg);
        }

        public ResultDTOBuilder<T> msg(String msg)
        {
            this.msg = msg;return this;
        }

        public ResultDTOBuilder<T> currentTimes(Long currentTimes)
        {
            this.currentTimes = currentTimes;return this;
        }

        public ResultDTOBuilder<T> data(T data)
        {
            this.data = data;return this;
        }

        public ResultDTOBuilder<T> code(Integer code)
        {
            this.code = code;return this;
        }
    }

    public static <T> ResultDTOBuilder<T> builder()
    {
        return new ResultDTOBuilder();
    }

    ResultDTO(Integer code, T data, Long currentTimes, String msg)
    {
        this.code = code;this.data = data;this.currentTimes = currentTimes;this.msg = msg;
    }

    public Integer getCode()
    {
        return this.code;
    }

    public T getData()
    {
        return this.data;
    }

    public Long getCurrentTimes()
    {
        return this.currentTimes;
    }

    private Long currentTimes = Long.valueOf(System.currentTimeMillis());
    private String msg;

    public String getMsg()
    {
        return this.msg;
    }
}
