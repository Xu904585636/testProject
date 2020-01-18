package com.kingleadsw.ysm.utils;

public final class DatePattern
{
    public static final String UTC_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    public static final String UTC_XXX = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String UTC_SSS_XXX = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
    public static final String COMMON_DATE = "yyyy-MM-dd";
    public static final String CHINESE_COMMON_DATE = "yyyy年MM月dd日";
    public static final String yyyyMMdd = "yyyyMMdd";
    public static final String TO_STRING_STYLE = "EEE MMM dd HH:mm:ss zzz yyyy";
    public static final String COMMON_DATE_AND_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String CHINESE_COMMON_DATE_AND_TIME = "yyyy年MM月dd日 HH:mm:ss";
    public static final String COMMON_DATE_AND_TIME_WITH_MILLISECOND = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String COMMON_DATE_AND_TIME_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";
    public static final String COMMON_DATE_AND_TIME_WITHOUT_YEAR_AND_SECOND = "MM-dd HH:mm";
    public static final String TIMESTAMP = "yyyyMMddHHmmss";
    public static final String TIMESTAMP_WITH_MILLISECOND = "yyyyMMddHHmmssSSS";
    public static final String TIMESTAMP_WITH_MILLISECOND_SIMPLE = "yyMMddHHmmssSSS";
    public static final String YEAR_AND_MONTH = "yyyy-MM";
    public static final String MONTH_AND_DAY = "MM-dd";
    public static final String MONTH_AND_DAY_WITH_WEEK = "MM-dd(E)";
    public static final String ddMMyyyyHHmmss = "dd/MM/yyyy HH:mm:ss";
    public static final String COMMON_TIME = "HH:mm:ss";
    public static final String COMMON_TIME_WITHOUT_SECOND = "HH:mm";
    public static final String yy = "yy";
    public static final String yyyy = "yyyy";
    public static final String MM = "MM";
    public static final String mmss = "mmss";
    public static final String HH = "HH";

    private DatePattern()
    {
        throw new AssertionError("No " + getClass().getName() + " instances for you!");
    }
}
