package com.kingleadsw.ysm.filters;



import com.kingleadsw.ysm.exception.UnknowException;
import com.kingleadsw.ysm.lang.Converts;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

public final class Request2Lists
{
    private static final Integer paramSize(Set<Method> methodSet, Map<String, String[]> stringMap)
    {
        Integer size = Integer.valueOf(0);
        for (Method method : methodSet)
        {
            String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
            Integer tempSize = Integer.valueOf(0);
            if (stringMap.containsKey(key)) {
                tempSize = Integer.valueOf(((String[])stringMap.get(key)).length);
            }
            if (tempSize.intValue() > size.intValue()) {
                size = tempSize;
            }
        }
        return size;
    }

    public static final <K> List<K> covert(Class<K> T, HttpServletRequest request)
    {
        try
        {
            List<K> objectList = new LinkedList();

            Set<Method> methodSet = getDeclaredMethods(T);
            Map<String, String[]> stringMap = request.getParameterMap();
            Integer valueSize = paramSize(methodSet, stringMap);
            System.out.println(T.getName() + " Max Length:" + valueSize);
            for (int i = 0; i < valueSize.intValue(); i++)
            {
                K object = T.newInstance();
                for (Method method : methodSet)
                {
                    String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                    String[] value = (String[])stringMap.get(key);
                    if ((value != null) && (i < value.length))
                    {
                        Class<?>[] type = method.getParameterTypes();
                        Object[] param_value = { Converts.convert(value[i], type[0], null) };
                        method.invoke(object, param_value);
                    }
                }
                objectList.add(object);
            }
            return objectList;
        }
        catch (Exception ex)
        {
            throw  new UnknowException(50001,"发生异常");
        }
    }

    private static final <T> Set<Method> getDeclaredMethods(Class<T> T)
    {
        Method[] methods = T.getDeclaredMethods();
        Set<Method> methodSet = new HashSet();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                methodSet.add(method);
            }
        }
        return methodSet;
    }
}
