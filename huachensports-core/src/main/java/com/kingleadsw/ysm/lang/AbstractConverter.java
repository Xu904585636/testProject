package com.kingleadsw.ysm.lang;

import java.lang.reflect.Array;
import java.util.Collection;


import com.kingleadsw.ysm.exception.ConversionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractConverter
        implements Converter
{
    private static final Logger log = LogManager.getLogger(AbstractConverter.class);
    private static final String DEFAULT_CONFIG_MSG = "(N.B. Converters can be configured to use default values to avoid throwing exceptions)";
    private static final String PACKAGE = "org.apache.commons.beanutils.converters.";
    public static final int CONVERT_ERROR_CODE = 500002;
    private boolean useDefault = false;
    private Object defaultValue = null;

    public AbstractConverter() {}

    public AbstractConverter(Object defaultValue)
    {
        setDefaultValue(defaultValue);
    }

    public boolean isUseDefault()
    {
        return this.useDefault;
    }

    public <T> T convert(Class<T> type, Object value)
    {
        if (type == null) {
            return convertToDefaultType(type, value);
        }
        Class<?> sourceType = value == null ? null : value.getClass();
        Class<T> targetType = Converts.primitiveToWrapper(type);

        log.info("Converting" + (value == null ? "" : new StringBuilder().append(" '").append(toString(sourceType)).append("'").toString()) + " value '" + value + "' to type '" +
                toString(targetType) + "'");

        value = convertArray(value);
        if (value == null) {
            return handleMissing(targetType);
        }
        sourceType = value.getClass();
        try
        {
            if (targetType.equals(String.class)) {
                return targetType.cast(convertToString(value));
            }
            if (targetType.equals(sourceType))
            {
                if (log.isInfoEnabled()) {
                    log.info("    No conversion required, value is already a " + toString(targetType));
                }
                return targetType.cast(value);
            }
            Object result = convertToType(targetType, value);
            if (log.isInfoEnabled()) {
                log.info("    Converted to " + toString(targetType) + " value '" + result + "'");
            }
            return targetType.cast(result);
        }
        catch (Throwable t)
        {
            return handleError(targetType, value, t);
        }
    }

    protected String convertToString(Object value)
            throws Throwable
    {
        return value.toString();
    }

    protected abstract <T> T convertToType(Class<T> paramClass, Object paramObject)
            throws Throwable;

    protected Object convertArray(Object value)
    {
        if (value == null) {
            return null;
        }
        if (value.getClass().isArray())
        {
            if (Array.getLength(value) > 0) {
                return Array.get(value, 0);
            }
            return null;
        }
        if ((value instanceof Collection))
        {
            Collection<?> collection = (Collection)value;
            if (collection.size() > 0) {
                return collection.iterator().next();
            }
            return null;
        }
        return value;
    }

    protected <T> T handleError(Class<T> type, Object value, Throwable cause)
    {
        if (log.isInfoEnabled()) {
            if ((cause instanceof ConversionException)) {
                log.info("    Conversion threw ConversionException: " + cause.getMessage());
            } else {
                log.info("    Conversion threw " + cause);
            }
        }
        if (this.useDefault) {
            return handleMissing(type);
        }
        ConversionException cex = null;
        if ((cause instanceof ConversionException))
        {
            cex = (ConversionException)cause;
            if (log.isInfoEnabled())
            {
                log.info("    Re-throwing ConversionException: " + cex.getMessage());
                log.info("    (N.B. Converters can be configured to use default values to avoid throwing exceptions)");
            }
        }
        else
        {
            String msg = "Error converting from '" + toString(value.getClass()) + "' to '" + toString(type) + "' " + cause.getMessage();
            cex = new ConversionException(Integer.valueOf(500002));
            if (log.isInfoEnabled())
            {
                log.info("    Throwing ConversionException: " + msg);
                log.info("    (N.B. Converters can be configured to use default values to avoid throwing exceptions)");
            }
        }
        throw cex;
    }

    protected <T> T handleMissing(Class<T> type)
    {
        if ((this.useDefault) || (type.equals(String.class)))
        {
            Object value = getDefault(type);
            if ((this.useDefault) && (value != null) && (!type.equals(value.getClass()))) {
                try
                {
                    value = convertToType(type, this.defaultValue);
                }
                catch (Throwable t)
                {
                    log.error("Default conversion to " + toString(type) + " failed.", t);
                    throw new ConversionException(Integer.valueOf(500002));
                }
            }
            if (log.isInfoEnabled()) {
                log.info("    Using default " + (value == null ? "" : new StringBuilder().append(toString(value.getClass())).append(" ").toString()) + "value '" + this.defaultValue + "'");
            }
            return type.cast(value);
        }
        ConversionException cex = new ConversionException(Integer.valueOf(500002));
        if (log.isInfoEnabled())
        {
            log.info("    Throwing ConversionException: " + cex.getMessage());
            log.info("    (N.B. Converters can be configured to use default values to avoid throwing exceptions)");
        }
        throw cex;
    }

    protected void setDefaultValue(Object defaultValue)
    {
        this.useDefault = false;
        if (log.isInfoEnabled()) {
            log.info("Setting default value: " + defaultValue);
        }
        if (defaultValue == null) {
            this.defaultValue = null;
        } else {
            this.defaultValue = convert(getDefaultType(), defaultValue);
        }
        this.useDefault = true;
    }

    protected abstract Class<?> getDefaultType();

    protected Object getDefault(Class<?> type)
    {
        if (type.equals(String.class)) {
            return null;
        }
        return this.defaultValue;
    }

    public String toString()
    {
        return toString(getClass()) + "[UseDefault=" + this.useDefault + "]";
    }

    String toString(Class<?> type)
    {
        String typeName = null;
        if (type == null)
        {
            typeName = "null";
        }
        else if (type.isArray())
        {
            Class<?> elementType = type.getComponentType();
            int count = 1;
            while (elementType.isArray())
            {
                elementType = elementType.getComponentType();
                count++;
            }
            typeName = elementType.getName();
            for (int i = 0; i < count; i++) {
                typeName = typeName + "[]";
            }
        }
        else
        {
            typeName = type.getName();
        }
        if ((typeName.startsWith("java.lang.")) || (typeName.startsWith("java.util.")) ||
                (typeName.startsWith("java.math."))) {
            typeName = typeName.substring("java.lang.".length());
        } else if (typeName.startsWith("org.apache.commons.beanutils.converters.")) {
            typeName = typeName.substring("org.apache.commons.beanutils.converters.".length());
        }
        return typeName;
    }

    private <T> T convertToDefaultType(Class<T> targetClass, Object value)
    {
        T result = (T) convert(getDefaultType(), value);
        return result;
    }

    protected ConversionException conversionException(Class<?> type, Object value)
    {
        return new ConversionException(Integer.valueOf(500002));
    }
}