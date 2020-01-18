package com.kingleadsw.ysm.caches;

import com.google.common.collect.Maps;

import java.util.Map;
//todo 暂时弃用
public class Caches
{
    private static Cache cacheManager;
    private static Cache redissonManager;
    public static final Map<Class<?>, String> CACHE_KEY_MAP = Maps.newHashMap();

    public static Cache getCache()
    {
        return cacheManager;
    }

    public static void setCache(Cache cacheManager)
    {
        cacheManager = cacheManager;
    }

    public static void setRedissonManager(Cache cacheManager)
    {
        redissonManager = cacheManager;
    }

    public static Cache getRedissonManager()
    {
        return redissonManager;
    }

    public static boolean tryLock(String key, int expires)
    {
        return redissonManager.setnx(key, Integer.valueOf(expires));
    }

    public static boolean getLock(String key)
    {
        return redissonManager.lock(key);
    }

    public static void unlock(String key)
    {
        redissonManager.unlock(key);
    }
}
