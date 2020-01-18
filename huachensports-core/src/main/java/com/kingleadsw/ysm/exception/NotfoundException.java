package com.kingleadsw.ysm.exception;

import com.kingleadsw.ysm.constants.Status;
import com.kingleadsw.ysm.constants.StatusCodeType;

public class NotfoundException
        extends BaseException
{
    public NotfoundException(Integer code)
    {
        super(code);
    }

    public NotfoundException(Integer code, String desc)
    {
        super(code, desc);
    }

    public NotfoundException(Status status)
    {
        super(status);
    }

    public NotfoundException(Integer code, String desc, String... params)
    {
        super(code, desc, params);
    }

    protected StatusCodeType getStatusCodeType()
    {
        return StatusCodeType.NOT_FOUND;
    }
}
