package com.kingleadsw.ysm.utils;
import com.kingleadsw.ysm.constants.ServerEntry;

public class Statuss
{
    public static String getMsg(Integer code)
    {
        return (String) ServerEntry.entry().get(code);
    }

    public static String getMsg(Integer code, Object... params)
    {
        return Strings.format(getMsg(code), params);
    }
}
