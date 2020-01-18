package com.kingleadsw.ysm.lang;

import com.esotericsoftware.reflectasm.MethodAccess;
import com.kingleadsw.ysm.exception.ConversionException;
import com.kingleadsw.ysm.utils.Strings;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;



public final class Instances
{

    private static Map<String, Class<?>> clazzMap = new HashMap();
    private static Map<String, MethodAccess> methodMap = new HashMap();

    public static void transMap2Bean(Map<String, Object> map, Object obj)
    {
        try
        {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors)
            {
                String key = property.getName();
                if (map.containsKey(key))
                {
                    Object value = map.get(key);

                    Method setter = property.getWriteMethod();
                    setter.invoke(obj, new Object[] { value });
                }
            }
        }
        catch (IntrospectionException e)
        {

        }
        catch (IllegalAccessException e)
        {

        }
        catch (IllegalArgumentException e)
        {

        }
        catch (InvocationTargetException e)
        {
            //todo 以上需要记录日志
        }
    }

    public static Map<String, Object> transBean2Map(Object obj)
    {
        Map<String, Object> map = newHashMap();
        if (obj == null) {
            return map;
        }
        try
        {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors)
            {
                String key = property.getName();
                if (!key.equals("class"))
                {
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj, new Object[0]);
                    map.put(key, value);
                }
            }
        }
        catch (IntrospectionException e)
        {

        }
        catch (IllegalAccessException e)
        {

        }
        catch (IllegalArgumentException e)
        {
            // todo  以上需要记录日志
        }
        catch (InvocationTargetException e)
        {

        }
        return map;
    }

    public static <T> T getDiff(T oldBean, T newBean)
    {
        if ((oldBean == null) && (newBean != null)) {
            return newBean;
        }
        if (newBean == null) {
            return null;
        }
        Class<?> cls1 = oldBean.getClass();
        try
        {
            T object = (T) cls1.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(cls1);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors)
            {
                String key = property.getName();
                if (!key.equals("class"))
                {
                    Method getter = property.getReadMethod();

                    Method setter = property.getWriteMethod();
                    Object oldValue = getter.invoke(oldBean, new Object[0]);
                    Object newValue = getter.invoke(newBean, new Object[0]);
                    if (newValue != null) {
                        if (oldValue == null) {
                            setter.invoke(object, new Object[] { newValue });
                        } else if ((oldValue != null) && (!newValue.equals(oldValue))) {
                            setter.invoke(object, new Object[] { newValue });
                        }
                    }
                }
            }
            return object;
        }
        catch (InstantiationException|IllegalAccessException e)
        {

            throw new ConversionException(Integer.valueOf(500005));
        }
        catch (IntrospectionException e)
        {

            throw new ConversionException(Integer.valueOf(500005));
        }
        catch (IllegalArgumentException e)
        {
            throw new ConversionException(Integer.valueOf(500005));
        }
        catch (InvocationTargetException e)
        {

            throw new ConversionException(Integer.valueOf(500005));
        }
    }

    public static Class<?> getClass(String clazz)
    {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try
        {
            if (loader != null) {
                return Class.forName(clazz, true, loader);
            }
            return Class.forName(clazz);
        }
        catch (ClassNotFoundException e)
        {
            throw new ConversionException(Integer.valueOf(500005));
        }
    }

    public static <E> List<E> getInstanceList(Class<E> cls, List<?> list)
    {
        List<E> resultList = newArrayList();
        E object = null;
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();)
        {
            Map<?, ?> map = (Map)iterator.next();
            object = newInstance(cls, new Object[] { map });
            resultList.add(object);
        }
        return resultList;
    }

    public static <K> K newInstance(Class<K> clazz)
    {
        try
        {
            return clazz.newInstance();
        }
        catch (InstantiationException|IllegalAccessException e)
        {

        }
        return null;
    }

    public static Object newInstance(String clazz)
    {
        try
        {
            return getClass(clazz).newInstance();
        }
        catch (InstantiationException|IllegalAccessException e)
        {

            throw new ConversionException(Integer.valueOf(500005));
        }
    }

    public static <K> K newInstance(Class<K> cls, Object... args)
    {
        Class<?>[] argsClass = null;
        if (args != null)
        {
            argsClass = new Class[args.length];
            int i = 0;
            for (int j = args.length; i < j; i++) {
                argsClass[i] = args[i].getClass();
            }
        }
        try
        {
            Constructor<K> cons = cls.getConstructor(argsClass);
            return cons.newInstance(args);
        }
        catch (InstantiationException|IllegalAccessException|IllegalArgumentException|InvocationTargetException e)
        {

            throw new ConversionException(Integer.valueOf(500005));
        }
        catch (NoSuchMethodException e)
        {

            throw new ConversionException(Integer.valueOf(500005));
        }
        catch (SecurityException e)
        {

            throw new ConversionException(Integer.valueOf(500005));
        }
    }

    public static Object newInstance(String className, Object... args)
    {
        Class<?> newoneClass = (Class)clazzMap.get(className);
        if (newoneClass == null)
        {
            try
            {
                newoneClass = Class.forName(className);
            }
            catch (ClassNotFoundException e)
            {
                throw new ConversionException(Integer.valueOf(500005));
            }
            clazzMap.put(className, newoneClass);
        }
        return newInstance(newoneClass, args);
    }

    public static Object invokeMethod(Object owner, String methodName, Object... args)
    {
        Class<?> ownerClass = owner.getClass();
        String key = null;
        if (args != null)
        {
            Class<?>[] argsClass = new Class[args.length];
            int i = 0;
            for (int j = args.length; i < j; i++) {
                if (args[i] != null) {
                    argsClass[i] = args[i].getClass();
                }
            }
            key = ownerClass + "_" + methodName + "_" + Strings.join(argsClass, ",");
        }
        else
        {
            key = ownerClass + "_" + methodName;
        }
        MethodAccess methodAccess = (MethodAccess)methodMap.get(key);
        if (methodAccess == null)
        {
            methodAccess = MethodAccess.get(ownerClass);
            methodMap.put(key, methodAccess);
        }
        return methodAccess.invoke(owner, methodName, args);
    }

    public static <E> ArrayList<E> newArrayList()
    {
        return new ArrayList();
    }

    public static <E> ArrayList<E> newArrayList(E... e)
    {
        ArrayList<E> list = new ArrayList();
        Collections.addAll(list, e);
        return list;
    }

    public static <k, v> HashMap<k, v> newHashMap()
    {
        return new HashMap();
    }

    public static <E> HashSet<E> newHashSet()
    {
        return new HashSet();
    }

    public static <k, v> LinkedHashMap<k, v> newLinkedHashMap()
    {
        return new LinkedHashMap();
    }

    public static <E> LinkedHashSet<E> newLinkedHashSet()
    {
        return new LinkedHashSet();
    }

    public static <E> LinkedList<E> newLinkedList()
    {
        return new LinkedList();
    }

    public static <k, v> TreeMap<k, v> newTreeMap()
    {
        return new TreeMap();
    }

    public static <E> TreeSet<E> newTreeSet()
    {
        return new TreeSet();
    }

    public static <E> Vector<E> newVector()
    {
        return new Vector();
    }

    public static <k, v> WeakHashMap<k, v> newWeakHashMap()
    {
        return new WeakHashMap();
    }

    public static <k, v> Map<k, v> newHashMap(k key, v value)
    {
        Map<k, v> map = newHashMap();
        map.put(key, value);
        return map;
    }

    public static <k, v> ConcurrentHashMap<k, v> newConcurrentHashMap()
    {
        return new ConcurrentHashMap();
    }
}
