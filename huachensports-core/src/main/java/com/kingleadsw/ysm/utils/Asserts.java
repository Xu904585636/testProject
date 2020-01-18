package com.kingleadsw.ysm.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
/**
 * @Auther: zhoujie
 * @Description:
 */
public class Asserts
{
    public static boolean isNull(String str)
    {
        return (str == null) || (str.isEmpty());
    }

    public static boolean isEmpty(String str)
    {
        return (str != null) && (str.isEmpty());
    }

    public static boolean isNull(Collection<?> v)
    {
        return (v == null) || (v.isEmpty());
    }

    public static boolean isNull(Map<?, ?> v)
    {
        return (v == null) || (v.isEmpty());
    }

    public static boolean isNull(Object[] v)
    {
        return (v == null) || (v.length == 0);
    }

    public static boolean notNull(String str)
    {
        return !isNull(str);
    }

    public static boolean isNull(Number v)
    {
        return (v == null) || (v.intValue() == 0);
    }

    public static boolean notNull(BigDecimal v)
    {
        return (v != null) && (v.compareTo(new BigDecimal(0)) > 0);
    }

    public static boolean isNull(BigDecimal v)
    {
        return !notNull(v);
    }

    public static boolean notNull(Number n)
    {
        return !isNull(n);
    }

    public static boolean notNull(Collection<?> v)
    {
        return !isNull(v);
    }

    public static boolean notNull(Map<?, ?> v)
    {
        return !isNull(v);
    }

    public static boolean notNull(Object[] v)
    {
        return !isNull(v);
    }

    public static boolean notPram(Method m)
    {
        return m.getParameterTypes().length == 0;
    }

    public static boolean notStatic(Method m)
    {
        int mo = m.getModifiers();
        return !Modifier.isStatic(mo);
    }

    public static boolean isNull(byte[] data)
    {
        return (data == null) || (data.length == 0);
    }

    public static boolean isNull(Object v)
    {
        return v == null;
    }

    public static final boolean notNull(Object v)
    {
        return !isNull(v);
    }

    public static final boolean gt(Long v)
    {
        return gt(v, Long.valueOf(0L));
    }

    public static final boolean gt(Long v, Long t)
    {
        if ((v == null) || (t == null)) {
            return false;
        }
        return v.longValue() > t.longValue();
    }

    public static boolean equalsTrue(String value)
    {
        return "true".equals(value);
    }

    public static boolean isBlank(CharSequence cs)
    {
        int strLen;
        if ((cs == null) || ((strLen = cs.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNullOrEmpty(Object value)
    {
        if (null == value) {
            return true;
        }
        if ((value instanceof CharSequence)) {
            return isBlank((CharSequence)value);
        }
        if (isCollectionsSupportType(value)) {
            return Collections.sizeIsEmpty(value);
        }
        return false;
    }

    public static boolean isNotNullOrEmpty(Object value)
    {
        return !isNullOrEmpty(value);
    }

    private static boolean isCollectionsSupportType(Object value)
    {
        boolean isCollectionOrMap = ((value instanceof Collection)) || ((value instanceof Map));


        boolean isEnumerationOrIterator = ((value instanceof Enumeration)) || ((value instanceof Iterator));

        return (isCollectionOrMap) || (isEnumerationOrIterator) ||

                (value.getClass().isArray());
    }
}