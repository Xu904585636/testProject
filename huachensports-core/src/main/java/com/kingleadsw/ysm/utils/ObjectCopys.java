package com.kingleadsw.ysm.utils;


import com.google.common.collect.Lists;
import com.kingleadsw.ysm.base.dto.PageDTO;
import com.kingleadsw.ysm.base.dto.PaginationDTO;
import com.kingleadsw.ysm.exception.ConversionException;
import lombok.extern.log4j.Log4j2;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author  zhoujie
 *
 * 层级实体类转换， 数据结构转换
 */
@Log4j2
public class ObjectCopys {

    private static final ConcurrentMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap();


    private static final Map<Class<?>, Class<?>> primitiveMap = new HashMap(10);

    static
    {
        primitiveMap.put(String.class, String.class);
        primitiveMap.put(Boolean.class, Boolean.TYPE);
        primitiveMap.put(Byte.class, Byte.TYPE);
        primitiveMap.put(Character.class, Character.TYPE);
        primitiveMap.put(Double.class, Double.TYPE);
        primitiveMap.put(Float.class, Float.TYPE);
        primitiveMap.put(Integer.class, Integer.TYPE);
        primitiveMap.put(Long.class, Long.TYPE);
        primitiveMap.put(Short.class, Short.TYPE);
        primitiveMap.put(BigDecimal.class, BigDecimal.class);
    }

    public static void maping(Object source, Object target) {
        if (source != null) {
            BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            copier.copy(source, target, (Converter)null);
        }
    }

    public static <S, D> List<D> maping(List<S> sources, Class<D> destType)
    {
        if (Asserts.isNull(sources)) {
            return null;
        }
        List<D> targets = (List<D>) Lists.newArrayList();
        for (S source : sources)
        {
            D target = maping(source, destType);
            targets.add(target);
        }
        return targets;
    }

    public static Map<String, Object> toMap(Object source)
    {
        if (source == null) {
            return null;
        }
        Map<String, Object> map = new HashMap();
        BeanInfo beanInfo = null;
        try
        {
            beanInfo = Introspector.getBeanInfo(source.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors)
            {
                String key = property.getName();
                if (key.compareToIgnoreCase("class") != 0)
                {
                    Method getter = property.getReadMethod();
                    Object value = getter != null ? getter.invoke(source, new Object[0]) : null;
                    map.put(key, value);
                }
            }
        }
        catch (IntrospectionException |IllegalAccessException|IllegalArgumentException| InvocationTargetException e)
        {
            throw new ConversionException(Integer.valueOf(500002));
        }
        return map;
    }


    public static <D> D toObj(Map<String, Object> map, Class<D> clazz)
    {
        if (map == null) {
            return null;
        }
        D obj = null;
        try
        {
            obj = clazz.newInstance();
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors)
            {
                Method method = property.getWriteMethod();
                if (method != null) {
                    method.invoke(obj, new Object[] { map.get(property.getName()) });
                }
            }
        }
        catch (InstantiationException|IllegalAccessException|IntrospectionException|IllegalArgumentException|InvocationTargetException e)
        {
            log.error(e.getStackTrace());

            throw new ConversionException(Integer.valueOf(500002));
        }
        return obj;
    }
    public static <S, D> D maping(S s, Class<D> descType) {
        if (s == null) {
            return null;
        } else {
            BeanCopier copier = BeanCopier.create(s.getClass(), descType, false);
            D target = newInstance(descType);
            copier.copy(s, target,null);
            return target;
        }
    }

    public static BeanCopier getBeanCopier(Class<?> source, Class<?> target)
    {
        String beanCopierKey = generateBeanKey(source, target);
        if (beanCopierMap.containsKey(beanCopierKey)) {
            return beanCopierMap.get(beanCopierKey);
        }
        BeanCopier beanCopier = BeanCopier.create(source, target, true);
        beanCopierMap.putIfAbsent(beanCopierKey, beanCopier);

        return beanCopierMap.get(beanCopierKey);
    }


    public static String generateBeanKey(Class<?> source, Class<?> target)
    {
        return source.getName() + "@" + target.getName();
    }


    public static Class<?> getElementType(Class<?> tartget, String fieldName) {
        Class<?> elementTypeClass;
        try {
            Type type = tartget.getDeclaredField(fieldName).getGenericType();
            ParameterizedType t = (ParameterizedType) type;
            String classStr = t.getActualTypeArguments()[0].toString().replace("class ", "");
            elementTypeClass = Thread.currentThread().getContextClassLoader().loadClass(classStr);
        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException e) {
            throw new RuntimeException("get fieldName[" + fieldName + "] error", e);
        }
        return elementTypeClass;
    }

    public static boolean isPrimitive(Class<?> clazz) {
        if (primitiveMap.containsKey(clazz)) {
            return true;
        }
        return clazz.isPrimitive();
    }

    public static class DeepCopyConverter
            implements Converter {
        private Class<?> target;

        public DeepCopyConverter(Class<?> target) {
            this.target = target;
        }

        public Object convert(Object value, Class targetClazz, Object methodName) {
            if ((value instanceof List)) {
                List values = (List) value;
                List retList = Lists.newArrayList();
                for (Object source : values) {
                    Class clazz = ObjectCopys.getElementType(this.target, methodName.toString().replace("set", "").toLowerCase());
                    retList.add(ObjectCopys.mappingAll(source, clazz));
                }
                return retList;
            }
            if (!(value instanceof Map)) {
                if (!ObjectCopys.isPrimitive(targetClazz)) {
                    return ObjectCopys.mappingAll(value, targetClazz);
                }
            }
            return value;
        }
    }


    public static <S, T> T mappingAll(S source, Class<T> target)
    {
        if (source == null) {
            return null;
        }
        T ret = newInstance(target);
        BeanCopier beanCopier = getBeanCopier(source.getClass(), target);
        beanCopier.copy(source, ret, new DeepCopyConverter(target));
        return ret;
    }
    public static <S, D> List<D> mappingAll(List<S> sources, Class<D> destType)
    {
        if (Asserts.isNull(sources)) {
            return null;
        }
        List<D> targets = Lists.newArrayList();
        for (S source : sources)
        {
            D target = mappingAll(source, destType);
            targets.add(target);
        }
        return targets;
    }

    public static <T> T newInstance(Class<T> klass) {


        try {
            return klass.newInstance();
        } catch (Exception e) {
            log.error(e.getStackTrace());
            throw new ConversionException(Integer.valueOf(500002));

        }
    }


    /**
     * 分页参数转换
     * @param pagination
     * @param destType
     * @return
     */
    public static <K, E,D> PaginationDTO<D,E> transferPagination(PaginationDTO<K, E> pagination, Class<D> destType)
    {
        PaginationDTO<D,E> target=new  PaginationDTO<D,E>();
        target.setCurrent(pagination.getCurrent().intValue());
        target.setSize(pagination.getSize().intValue());
        K condition = pagination.getCondition();
        if(condition!=null) {
            D d=ObjectCopys.maping(condition, destType);
            target.setCondition(d);
        }
        target.setTotal(pagination.getTotal());
        target.setAscs(pagination.getAscs());
        target.setDescs(pagination.getDescs());
        target.setAsc(pagination.isAsc());
        return target;
    }


    /**
     * 分页参数转换
     * @param pagination
     * @param destType
     * @return
     */
    public static <E,D> PageDTO<D> transferPageDTO(PageDTO<E> pageDTO, Class<D> destType)
    {
        PageDTO<D> target=new  PageDTO<D>();
        List<E> list = pageDTO.getRecords();
        if(list!=null) {
            List<D> records=ObjectCopys.maping(list, destType);
            target.setRecords(records);
        }
        target.setTotal(pageDTO.getTotal());
        target.setAscs(pageDTO.getAscs());
        target.setDescs(pageDTO.getDescs());
        target.setAsc(pageDTO.isAsc());
        target.setPages(pageDTO.getPages());
        target.setCurrent(pageDTO.getCurrent().intValue());
        target.setSize(pageDTO.getSize().intValue());
        target.setOrderByField(pageDTO.getOrderByField());
        return target;
    }
}
