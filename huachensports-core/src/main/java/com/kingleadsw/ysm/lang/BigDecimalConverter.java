package com.kingleadsw.ysm.lang;

import java.math.BigDecimal;

public final class BigDecimalConverter
        extends NumberConverter
{
    public BigDecimalConverter()
    {
        super(true);
    }

    public BigDecimalConverter(Object defaultValue)
    {
        super(true, defaultValue);
    }

    protected Class<BigDecimal> getDefaultType()
    {
        return BigDecimal.class;
    }
}
