package com.kingleadsw.ysm.exception;


import com.kingleadsw.ysm.constants.Status;
import com.kingleadsw.ysm.constants.StatusCodeType;

public class IllegalParameterException
        extends BaseException
{
    public IllegalParameterException(Integer code)
    {
        super(code);
    }

    public IllegalParameterException(Integer code, String desc)
    {
        super(code, desc);
    }

    public IllegalParameterException(Status status)
    {
        super(status);
    }

    public IllegalParameterException(Integer code, String desc, String... params)
    {
        super(code, desc, params);
    }

    protected StatusCodeType getStatusCodeType()
    {
        return StatusCodeType.BAD_REQUEST;
    }
}

