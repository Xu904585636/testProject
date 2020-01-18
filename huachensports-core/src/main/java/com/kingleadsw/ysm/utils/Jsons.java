package com.kingleadsw.ysm.utils;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kingleadsw.ysm.exception.ConversionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @Auther: zhoujie
 * @Description: json操作调用此类
 */
public class Jsons
{
    private static final Logger log = LogManager.getLogger(Jsons.class);
    private static ObjectMapper objectMapper = null;

    static
    {
        objectMapper = new ObjectMapper();



        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.enable(new JsonParser.Feature[] { JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES });
        objectMapper.enable(new JsonParser.Feature[] { JsonParser.Feature.ALLOW_SINGLE_QUOTES });

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        objectMapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
    }

    public static void main(String[] args)
    {
        Set<String> s = new HashSet();
        s.add("a");
        s.add("b");
        String json = toJson(s);
        System.out.println(toObjByType(json, new TypeReference() {}));
    }

    public static ObjectMapper getObjectMapper()
    {
        return objectMapper;
    }


    public static String toJson(Object value)
    {
        if (value == null) {
            return null;
        }
        try
        {
            return objectMapper.writeValueAsString(value);
        }
        catch (IOException e)
        {
            log.error("Object to Json deserialize error", e);
            throw new ConversionException(Integer.valueOf(500007));
        }
    }

    public static String getByKey(String json, String key)
    {
        String value = null;
        JsonNode rootNode = null;
        if (Asserts.notNull(json)) {
            try
            {
                rootNode = (JsonNode)objectMapper.readValue(json, JsonNode.class);
            }
            catch (IOException e)
            {
                log.error("Object to Json deserialize error", e);
                throw new ConversionException(Integer.valueOf(500006));
            }
        }
        if (null != rootNode) {
            if ((rootNode.path(key).isArray()) || (rootNode.path(key).isContainerNode())) {
                value = rootNode.path(key).toString();
            } else if (rootNode.path(key).isValueNode()) {
                value = rootNode.path(key).textValue();
            }
        }
        if ("null".equalsIgnoreCase(value)) {
            value = null;
        }
        return value;
    }

    public static String stringify(Object o)
    {
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentArraysWith(new DefaultIndenter());
        try
        {
            return objectMapper.writer(printer).writeValueAsString(o);
        }
        catch (JsonProcessingException e)
        {
            log.error("Object to Json deserialize error", e);
            throw new ConversionException(Integer.valueOf(500007));
        }
    }

    public static <T> T toObj(String json, Class<T> type)
    {
        if (Asserts.isNull(json)) {
            return null;
        }
        try
        {
            return objectMapper.readValue(json, type);
        }
        catch (IOException e)
        {
            log.error("Object to Json deserialize error", e);
            throw new ConversionException(Integer.valueOf(500007));
        }
    }

    public static <T> T DeserializeObject(String str, TypeReference typeReference) {


        try {
            return objectMapper.readValue(str, typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static <T> T toObj(byte[] json, Class<T> type)
    {
        if (Asserts.isNull(json)) {
            return null;
        }
        try
        {
            return objectMapper.readValue(json, type);
        }
        catch (IOException e)
        {
            log.error("Object to Json deserialize error", e);
            throw new ConversionException(Integer.valueOf(500007));
        }
    }

    public static <T> T toObjByType(String json, TypeReference<T> type)
    {
        try
        {
            return objectMapper.readValue(json, type);
        }
        catch (Exception e)
        {
            log.error("Jsons.fromJson ex, json=" + json + ", type=" + type, e);
        }
        return null;
    }

    public static <T> List<T> toList(String json, Class<T> clazz)
    {
        if (Asserts.isNull(json)) {
            return null;
        }
        JavaType javaType = getCollectionType(ArrayList.class, new Class[] { clazz });
        try
        {
            return (List)objectMapper.readValue(json, javaType);
        }
        catch (IOException e)
        {
            log.error("Object to Json deserialize error", e);
            throw new ConversionException(Integer.valueOf(500007));
        }
    }

    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses)
    {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}