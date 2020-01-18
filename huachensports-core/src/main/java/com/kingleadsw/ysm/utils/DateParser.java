package com.kingleadsw.ysm.utils;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author  zhoujie
 *
 *Date  抽象接口
 */
public abstract interface DateParser
{
    public abstract Date parse(String paramString)
            throws ParseException;

    public abstract Date parse(String paramString, ParsePosition paramParsePosition);

    public abstract boolean parse(String paramString, ParsePosition paramParsePosition, Calendar paramCalendar);

    public abstract String getPattern();

    public abstract TimeZone getTimeZone();

    public abstract Locale getLocale();

    public abstract Object parseObject(String paramString)
            throws ParseException;

    public abstract Object parseObject(String paramString, ParsePosition paramParsePosition);
}

