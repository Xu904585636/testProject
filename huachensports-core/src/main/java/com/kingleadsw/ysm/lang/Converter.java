package com.kingleadsw.ysm.lang;

public abstract interface Converter
{
    public abstract <T> T convert(Class<T> paramClass, Object paramObject);
}
