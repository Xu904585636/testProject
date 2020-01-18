package com.kingleadsw.ysm.base;

import java.util.HashMap;
import java.util.Map;

public class LogEntry
{
    private static final Map<Integer, String> ENTRY = new HashMap();

    static
    {
        ENTRY.put(Integer.valueOf(10001), "登陆");
        ENTRY.put(Integer.valueOf(14002), "新增文章");
        ENTRY.put(Integer.valueOf(14003), "删除文章");
        ENTRY.put(Integer.valueOf(14004), "修改文章");
        ENTRY.put(Integer.valueOf(14001), "新增文章分类");
        ENTRY.put(Integer.valueOf(14006), "修改文章分类");
        ENTRY.put(Integer.valueOf(14005), "删除文章分类");
    }

    public static Map<Integer, String> entry()
    {
        return ENTRY;
    }
}
