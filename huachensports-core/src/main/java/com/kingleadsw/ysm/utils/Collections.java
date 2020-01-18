package com.kingleadsw.ysm.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

public class Collections
{
    public static boolean sizeIsEmpty(Object object)
    {
        if (object == null) {
            return true;
        }
        if ((object instanceof Collection)) {
            return ((Collection)object).isEmpty();
        }
        if ((object instanceof Map)) {
            return ((Map)object).isEmpty();
        }
        if ((object instanceof Object[])) {
            return ((Object[])object).length == 0;
        }
        if ((object instanceof Iterator)) {
            return !((Iterator)object).hasNext();
        }
        if ((object instanceof Enumeration)) {
            return !((Enumeration)object).hasMoreElements();
        }
        try
        {
            return Array.getLength(object) == 0;
        }
        catch (IllegalArgumentException ex)
        {
            throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
        }
    }
}