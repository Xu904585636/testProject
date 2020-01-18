package com.kingleadsw.ysm.lang;

import java.lang.reflect.Array;

public class Arrays
{
    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
    public static final String[] EMPTY_STRING_ARRAY = new String[0];
    public static final int INDEX_NOT_FOUND = -1;

    public static <T> T[] add(T[] array, T element)
    {
        Class<?> type;
        if (array != null)
        {
            type = array.getClass().getComponentType();
        }
        else
        {

            if (element != null) {
                type = element.getClass();
            } else {
                throw new IllegalArgumentException("Arguments cannot both be null");
            }
        }

        T[] newArray = (T[]) copyArrayGrow1(array, type);
        newArray[(newArray.length - 1)] = element;
        return newArray;
    }

    private static Object copyArrayGrow1(Object array, Class<?> newArrayComponentType)
    {
        if (array != null)
        {
            int arrayLength = Array.getLength(array);
            Object newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
            return newArray;
        }
        return Array.newInstance(newArrayComponentType, 1);
    }

    public static boolean contains(Object[] array, Object objectToFind)
    {
        return indexOf(array, objectToFind) != -1;
    }

    public static int indexOf(Object[] array, Object objectToFind)
    {
        return indexOf(array, objectToFind, 0);
    }

    public static int indexOf(Object[] array, Object objectToFind, int startIndex)
    {
        if (array == null) {
            return -1;
        }
        if (startIndex < 0) {
            startIndex = 0;
        }
        if (objectToFind == null) {
            for (int i = startIndex; i < array.length; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = startIndex; i < array.length; i++) {
                if (objectToFind.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static <T> T[] newArray(Class<T> componentType, int length)
    {

        return (T[]) Array.newInstance(componentType, length);
    }
}