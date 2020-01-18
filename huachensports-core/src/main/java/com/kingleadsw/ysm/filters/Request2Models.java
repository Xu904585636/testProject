package com.kingleadsw.ysm.filters;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import com.kingleadsw.ysm.lang.Converts;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Request2Models
{
    private static final Logger logger = LogManager.getLogger(Request2Models.class); ;

    public static final <K> K covert(Class<K> T, HttpServletRequest request)
    {
        try
        {
            K obj = T.newInstance();

            Set<Method> methodSet = get_methods(T);
            Iterator<Method> methodIterator = methodSet.iterator();
            while (methodIterator.hasNext())
            {
                Method method = (Method)methodIterator.next();
                String key = method.getName().substring(3, 4).toLowerCase() + method.getName().substring(4);
                String value = request.getParameter(key);
                Class<?>[] type = method.getParameterTypes();
                Object[] param_value = { Converts.convert(value, type[0], null) };
                method.invoke(obj, param_value);
            }
            return obj;
        }
        catch (Exception ex)
        {
            logger.error("", ex);
        }
        return null;
    }

    public static final Set<Method> get_methods(Class<?> T)
    {
        Method[] methods = T.getMethods();
        Set<Method> methodSet = new HashSet();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                methodSet.add(method);
            }
        }
        return methodSet;
    }

    public static final Set<Method> get_declared_methods(Class<?> T)
    {
        Method[] methods = T.getMethods();
        Set<Method> methodSet = new HashSet();
        for (Method method : methods) {
            if (method.getName().startsWith("set")) {
                methodSet.add(method);
            }
        }
        return methodSet;
    }

    public static final Set<Method> get_getDeclared_methods(Class<?> T)
    {
        Method[] methods = T.getDeclaredMethods();
        Set<Method> methodSet = new HashSet();
        for (Method method : methods) {
            if (method.getName().startsWith("get")) {
                methodSet.add(method);
            }
        }
        return methodSet;
    }

    public static final void covertObj(Object o, Map<String, String[]> parameterMap)
    {
        Class<?> clazz = o.getClass();
        Iterator<Map.Entry<String, String[]>> iterator = parameterMap.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String[]> entry = (Map.Entry)iterator.next();
            String key = ((String)entry.getKey()).trim();
            String value = ((String[])entry.getValue())[0].trim();
            try
            {
                Method method = setMethod(key, clazz);
                if (method != null)
                {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (method != null)
                    {
                        Object[] param_value = { Converts.convert(value, parameterTypes[0], null) };
                        method.invoke(o, param_value);
                    }
                }
            }
            catch (Exception e)
            {
                logger.error("", e);
            }
        }
    }

    public static final void covertObjWithMap(Object o, Map<String, String> parameterMap)
    {
        Class<?> clazz = o.getClass();
        Iterator<Map.Entry<String, String>> iterator = parameterMap.entrySet().iterator();
        while (iterator.hasNext())
        {
            Map.Entry<String, String> entry = (Map.Entry)iterator.next();
            String key = ((String)entry.getKey()).trim();
            String value = ((String)entry.getValue()).trim();
            try
            {
                Method method = setMethod(key, clazz);
                if (method != null)
                {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (method != null)
                    {
                        Object[] param_value = { Converts.convert(value, parameterTypes[0], null) };
                        method.invoke(o, param_value);
                    }
                }
            }
            catch (Exception e)
            {
                logger.error("", e);
            }
        }
    }

    public static final void covertObj(Object o, Object paramObj)
    {
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try
            {
                Field field = fields[i];
                Method getMethod = getMethod(field.getName(), paramObj.getClass());
                if (getMethod != null)
                {
                    Object value = getMethod.invoke(paramObj, new Object[0]);
                    Method setMethod = setMethod(field.getName(), o.getClass());
                    if ((setMethod != null) &&
                            (value != null) && (!value.toString().equals(""))) {
                        setMethod.invoke(o, new Object[] { value });
                    }
                }
            }
            catch (Exception e)
            {
                logger.error("", e);
            }
        }
    }

    public static final Object init(Object obj, Object obiExtend)
    {
        Class<?> clazz = obj.getClass();
        Set<Method> getMethods = get_getDeclared_methods(clazz);
        Iterator<Method> ite = getMethods.iterator();
        while (ite.hasNext()) {
            try
            {
                Method method = (Method)ite.next();
                String name = method.getName();
                String fileName = name.substring(3, 4).toLowerCase() + name.substring(4, name.length());
                Object o = method.invoke(obj, new Object[0]);
                Method setMethod = setMethod(fileName, clazz);
                setMethod.invoke(obiExtend, new Object[] { o });
            }
            catch (Exception e)
            {
                logger.error("", e);
            }
        }
        return obiExtend;
    }

    public static final Method setMethod(String fieldName, Class<?> clazz)
    {
        try
        {
            Class<?>[] parameterTypes = new Class[1];
            Field field = clazz.getDeclaredField(fieldName);
            parameterTypes[0] = field.getType();
            StringBuffer sb = new StringBuffer();
            sb.append("set");
            sb.append(fieldName.substring(0, 1).toUpperCase());
            sb.append(fieldName.substring(1));
            return clazz.getMethod(sb.toString(), parameterTypes);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return null;
    }

    public static final Method getMethod(String fieldName, Class<?> clazz)
    {
        StringBuffer sb = new StringBuffer();
        sb.append("get");
        sb.append(fieldName.substring(0, 1).toUpperCase());
        sb.append(fieldName.substring(1));
        try
        {
            return clazz.getMethod(sb.toString(), new Class[0]);
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
        return null;
    }
}
