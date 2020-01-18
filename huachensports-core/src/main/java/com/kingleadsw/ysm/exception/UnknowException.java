package com.kingleadsw.ysm.exception;


import com.kingleadsw.ysm.constants.Status;
import com.kingleadsw.ysm.constants.StatusCodeType;

public class UnknowException
        extends BaseException
{
    public UnknowException(Integer code)
    {
        super(code);
    }

    public UnknowException(Integer code, String desc)
    {
        super(code, desc);
    }

    public UnknowException(Status status)
    {
        super(status);
    }

    public UnknowException(Integer code, String desc, String... params)
    {
        super(code, desc, params);
    }

    public StatusCodeType getStatusCodeType()
    {
        return StatusCodeType.SERVER_ERROR;
    }
}
