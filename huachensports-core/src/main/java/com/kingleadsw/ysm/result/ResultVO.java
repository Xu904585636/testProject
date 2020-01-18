package com.kingleadsw.ysm.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
/**
 * @Auther: zhoujie
 * @Description:
 */
@ApiModel(value="Result", description="公共返回实体对象")
public class ResultVO<T> implements Serializable
{
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("内容")
    private T data;
    @ApiModelProperty("系统当前时间")
    private Long currentTime;
    @ApiModelProperty("错误时的提示信息")
    private String msg;
    @ApiModelProperty("请求跟踪ID,用于填入到下次请求头的traceId")
    private String traceId;

    public void setData(T data)
    {
        this.data = data;
    }

    public int hashCode()
    {
        int PRIME = 59;int result = 1;Object $code = getCode();result = result * 59 + ($code == null ? 43 : $code.hashCode());Object $data = getData();result = result * 59 + ($data == null ? 43 : $data.hashCode());Object $currentTime = getCurrentTime();result = result * 59 + ($currentTime == null ? 43 : $currentTime.hashCode());Object $msg = getMsg();result = result * 59 + ($msg == null ? 43 : $msg.hashCode());Object $traceId = getTraceId();result = result * 59 + ($traceId == null ? 43 : $traceId.hashCode());return result;
    }

    protected boolean canEqual(Object other)
    {
        return other instanceof ResultVO;
    }

    public boolean equals(Object o)
    {
        if (o == this) {
            return true;
        }
        if (!(o instanceof ResultVO)) {
            return false;
        }
        ResultVO<?> other = (ResultVO)o;
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
        Object this$currentTime = getCurrentTime();Object other$currentTime = other.getCurrentTime();
        if (this$currentTime == null ? other$currentTime != null : !this$currentTime.equals(other$currentTime)) {
            return false;
        }
        Object this$msg = getMsg();Object other$msg = other.getMsg();
        if (this$msg == null ? other$msg != null : !this$msg.equals(other$msg)) {
            return false;
        }
        Object this$traceId = getTraceId();Object other$traceId = other.getTraceId();return this$traceId == null ? other$traceId == null : this$traceId.equals(other$traceId);
    }

    public void setTraceId(String traceId)
    {
        this.traceId = traceId;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public void setCurrentTime(Long currentTime)
    {
        this.currentTime = currentTime;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String toString()
    {
        return "ResultVO(code=" + getCode() + ", data=" + getData() + ", currentTime=" + getCurrentTime() + ", msg=" + getMsg() + ", traceId=" + getTraceId() + ")";
    }

    public static class ResultVOBuilder<T>
    {
        private Integer code;
        private T data;
        private Long currentTime;
        private String msg;
        private String traceId;

        public String toString()
        {
            return "ResultVO.ResultVOBuilder(code=" + this.code + ", data=" + this.data + ", currentTime=" + this.currentTime + ", msg=" + this.msg + ", traceId=" + this.traceId + ")";
        }

        public ResultVO<T> build()
        {
            return new ResultVO(this.code, this.data, this.currentTime, this.msg, this.traceId);
        }

        public ResultVOBuilder<T> traceId(String traceId)
        {
            this.traceId = traceId;return this;
        }

        public ResultVOBuilder<T> msg(String msg)
        {
            this.msg = msg;return this;
        }

        public ResultVOBuilder<T> currentTime(Long currentTime)
        {
            this.currentTime = currentTime;return this;
        }

        public ResultVOBuilder<T> data(T data)
        {
            this.data = data;return this;
        }

        public ResultVOBuilder<T> code(Integer code)
        {
            this.code = code;return this;
        }
    }

    public static <T> ResultVOBuilder<T> builder()
    {
        return new ResultVOBuilder();
    }

    ResultVO(Integer code, T data, Long currentTime, String msg, String traceId)
    {
        this.code = code;this.data = data;this.currentTime = currentTime;this.msg = msg;this.traceId = traceId;
    }

    public Integer getCode()
    {
        return this.code;
    }

    public T getData()
    {
        return this.data;
    }

    public Long getCurrentTime()
    {
        return this.currentTime;
    }

    public String getMsg()
    {
        return this.msg;
    }

    public String getTraceId()
    {
        return this.traceId;
    }
}