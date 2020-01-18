package com.kingleadsw.ysm.lang;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


import com.kingleadsw.ysm.exception.ConversionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class NumberConverter
        extends AbstractConverter
{
    private static final Logger log = LogManager.getLogger(NumberConverter.class);
    private static final Integer ZERO = new Integer(0);
    private static final Integer ONE = new Integer(1);
    private String pattern;
    private final boolean allowDecimals;
    private boolean useLocaleFormat;
    private Locale locale;

    public NumberConverter(boolean allowDecimals)
    {
        this.allowDecimals = allowDecimals;
    }

    public NumberConverter(boolean allowDecimals, Object defaultValue)
    {
        this.allowDecimals = allowDecimals;
        setDefaultValue(defaultValue);
    }

    public boolean isAllowDecimals()
    {
        return this.allowDecimals;
    }

    public void setUseLocaleFormat(boolean useLocaleFormat)
    {
        this.useLocaleFormat = useLocaleFormat;
    }

    public String getPattern()
    {
        return this.pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
        setUseLocaleFormat(true);
    }

    public Locale getLocale()
    {
        return this.locale;
    }

    public void setLocale(Locale locale)
    {
        this.locale = locale;
        setUseLocaleFormat(true);
    }

    protected String convertToString(Object value)
            throws Throwable
    {
        String result = null;
        if ((this.useLocaleFormat) && ((value instanceof Number)))
        {
            NumberFormat format = getFormat();
            format.setGroupingUsed(false);
            result = format.format(value);
            if (log.isDebugEnabled()) {
                log.debug("    Converted  to String using format '" + result + "'");
            }
        }
        else
        {
            result = value.toString();
            if (log.isDebugEnabled()) {
                log.debug("    Converted  to String using toString() '" + result + "'");
            }
        }
        return result;
    }

    protected <T> T convertToType(Class<T> targetType, Object value)
            throws Throwable
    {
        Class<?> sourceType = value.getClass();
        if ((value instanceof Number)) {
            return toNumber(sourceType, targetType, (Number)value);
        }
        if ((value instanceof Boolean)) {
            return toNumber(sourceType, targetType, ((Boolean)value).booleanValue() ? ONE : ZERO);
        }
        if (((value instanceof Date)) && (Long.class.equals(targetType))) {
            return targetType.cast(new Long(((Date)value).getTime()));
        }
        if (((value instanceof Calendar)) && (Long.class.equals(targetType))) {
            return targetType.cast(new Long(((Calendar)value).getTime().getTime()));
        }
        String stringValue = value.toString().trim();
        if (stringValue.length() == 0) {
            return handleMissing(targetType);
        }
        Number number = null;
        if (this.useLocaleFormat)
        {
            NumberFormat format = getFormat();
            number = parse(sourceType, targetType, stringValue, format);
        }
        else
        {
            if (log.isDebugEnabled()) {
                log.debug("    No NumberFormat, using default conversion");
            }
            number = toNumber(sourceType, targetType, stringValue);
        }
        return toNumber(sourceType, targetType, number);
    }

    private <T> T toNumber(Class<?> sourceType, Class<T> targetType, Number value)
    {
        if (targetType.equals(value.getClass())) {
            return targetType.cast(value);
        }
        if (targetType.equals(Byte.class))
        {
            long longValue = value.longValue();
            if (longValue > 127L)
            {
                log.error(toString(sourceType) + " value '" + value + "' is too large for " +
                        toString(targetType));
                throw new ConversionException(Integer.valueOf(500002));
            }
            if (longValue < -128L)
            {
                log.error(toString(sourceType) + " value '" + value + "' is too small " +
                        toString(targetType));
                throw new ConversionException(Integer.valueOf(500002));
            }
            return targetType.cast(new Byte(value.byteValue()));
        }
        if (targetType.equals(Short.class))
        {
            long longValue = value.longValue();
            if (longValue > 32767L)
            {
                log.error(toString(sourceType) + " value '" + value + "' is too large for " +
                        toString(targetType));
                throw new ConversionException(Integer.valueOf(500002));
            }
            if (longValue < -32768L)
            {
                log.error(toString(sourceType) + " value '" + value + "' is too small " +
                        toString(targetType));
                throw new ConversionException(Integer.valueOf(500002));
            }
            return targetType.cast(new Short(value.shortValue()));
        }
        if (targetType.equals(Integer.class))
        {
            long longValue = value.longValue();
            if (longValue > 2147483647L)
            {
                log.error(toString(sourceType) + " value '" + value + "' is too large for " +
                        toString(targetType));
                throw new ConversionException(Integer.valueOf(500002));
            }
            if (longValue < -2147483648L)
            {
                log.error(toString(sourceType) + " value '" + value + "' is too small " +
                        toString(targetType));
                throw new ConversionException(Integer.valueOf(500002));
            }
            return targetType.cast(new Integer(value.intValue()));
        }
        if (targetType.equals(Long.class)) {
            return targetType.cast(new Long(value.longValue()));
        }
        if (targetType.equals(Float.class))
        {
            if (value.doubleValue() > 3.402823466385289E+038D)
            {
                log.error(toString(sourceType) + " value '" + value + "' is too large for " +
                        toString(targetType));
                throw new ConversionException(Integer.valueOf(500002));
            }
            return targetType.cast(new Float(value.floatValue()));
        }
        if (targetType.equals(Double.class)) {
            return targetType.cast(new Double(value.doubleValue()));
        }
        if (targetType.equals(BigDecimal.class))
        {
            if (((value instanceof Float)) || ((value instanceof Double))) {
                return targetType.cast(new BigDecimal(value.toString()));
            }
            if ((value instanceof BigInteger)) {
                return targetType.cast(new BigDecimal((BigInteger)value));
            }
            if ((value instanceof BigDecimal)) {
                return targetType.cast(new BigDecimal(value.toString()));
            }
            return targetType.cast(BigDecimal.valueOf(value.longValue()));
        }
        if (targetType.equals(BigInteger.class))
        {
            if ((value instanceof BigDecimal)) {
                return targetType.cast(((BigDecimal)value).toBigInteger());
            }
            return targetType.cast(BigInteger.valueOf(value.longValue()));
        }
        String msg = toString(getClass()) + " cannot handle conversion to '" + toString(targetType) + "'";
        log.error(msg);
        throw new ConversionException(Integer.valueOf(500002));
    }

    private Number toNumber(Class<?> sourceType, Class<?> targetType, String value)
    {
        if (targetType.equals(Byte.class)) {
            return new Byte(value);
        }
        if (targetType.equals(Short.class)) {
            return new Short(value);
        }
        if (targetType.equals(Integer.class)) {
            return new Integer(value);
        }
        if (targetType.equals(Long.class)) {
            return new Long(value);
        }
        if (targetType.equals(Float.class)) {
            return new Float(value);
        }
        if (targetType.equals(Double.class)) {
            return new Double(value);
        }
        if (targetType.equals(BigDecimal.class)) {
            return new BigDecimal(value);
        }
        if (targetType.equals(BigInteger.class)) {
            return new BigInteger(value);
        }
        String msg = toString(getClass()) + " cannot handle conversion from '" + toString(sourceType) + "' to '" + toString(targetType) + "'";
        log.error(msg);
        throw new ConversionException(Integer.valueOf(500002));
    }

    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        buffer.append(toString(getClass()));
        buffer.append("[UseDefault=");
        buffer.append(isUseDefault());
        buffer.append(", UseLocaleFormat=");
        buffer.append(this.useLocaleFormat);
        if (this.pattern != null)
        {
            buffer.append(", Pattern=");
            buffer.append(this.pattern);
        }
        if (this.locale != null)
        {
            buffer.append(", Locale=");
            buffer.append(this.locale);
        }
        buffer.append(']');
        return buffer.toString();
    }

    private NumberFormat getFormat()
    {
        NumberFormat format = null;
        if (this.pattern != null)
        {
            if (this.locale == null)
            {
                if (log.isDebugEnabled()) {
                    log.debug("    Using pattern '" + this.pattern + "'");
                }
                format = new DecimalFormat(this.pattern);
            }
            else
            {
                if (log.isDebugEnabled()) {
                    log.debug("    Using pattern '" + this.pattern + "' with Locale[" + this.locale + "]");
                }
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(this.locale);
                format = new DecimalFormat(this.pattern, symbols);
            }
        }
        else if (this.locale == null)
        {
            if (log.isDebugEnabled()) {
                log.debug("    Using default Locale format");
            }
            format = NumberFormat.getInstance();
        }
        else
        {
            if (log.isDebugEnabled()) {
                log.debug("    Using Locale[" + this.locale + "] format");
            }
            format = NumberFormat.getInstance(this.locale);
        }
        if (!this.allowDecimals) {
            format.setParseIntegerOnly(true);
        }
        return format;
    }

    private Number parse(Class<?> sourceType, Class<?> targetType, String value, NumberFormat format)
    {
        ParsePosition pos = new ParsePosition(0);
        Number parsedNumber = format.parse(value, pos);
        if ((pos.getErrorIndex() >= 0) || (pos.getIndex() != value.length()) || (parsedNumber == null))
        {
            String msg = "Error converting from '" + toString(sourceType) + "' to '" + toString(targetType) + "'";
            if ((format instanceof DecimalFormat)) {
                msg = msg + " using pattern '" + ((DecimalFormat)format).toPattern() + "'";
            }
            if (this.locale != null) {
                msg = msg + " for locale=[" + this.locale + "]";
            }
            log.error(msg);
            throw new ConversionException(Integer.valueOf(500002));
        }
        return parsedNumber;
    }
}
