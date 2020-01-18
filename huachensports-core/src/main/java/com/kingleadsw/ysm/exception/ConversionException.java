package com.kingleadsw.ysm.exception;

import com.kingleadsw.ysm.constants.Status;
import com.kingleadsw.ysm.constants.StatusCodeType;

public class ConversionException
        extends BaseException
{
    public ConversionException(Integer code)
    {
        super(code);
    }

    public ConversionException(Integer code, String desc)
    {
        super(code, desc);
    }

    public ConversionException(Integer code, String desc, String... params)
    {
        super(code, desc, params);
    }

    public ConversionException(Status status)
    {
        super(status);
    }

    protected StatusCodeType getStatusCodeType()
    {
        return StatusCodeType.SERVER_ERROR;
    }
}
