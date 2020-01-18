package com.kingleadsw.ysm.utils;


import com.kingleadsw.ysm.base.LogEntry;

/**
 * @Auther: zhoujie
 * @Description:
 */
public class Elogs
{
    public static String getName(Integer code)
    {
        return (String) LogEntry.entry().get(code);
    }
}
