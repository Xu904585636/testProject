package com.kingleadsw.ysm.caches;

import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author  zhoujie
 *
 * 缓存设计
 */
//todo 暂时弃用
public abstract interface Cache
{
    public abstract Object get(String paramString);

    public abstract Set<Object> getAll(String paramString);

    public abstract void set(String paramString, Serializable paramSerializable, int paramInt);

    public abstract void set(String paramString, Serializable paramSerializable);

    public abstract Boolean exists(String paramString);

    public abstract void del(String paramString);

    public abstract void delAll(String paramString);

    public abstract String type(String paramString);

    public abstract Boolean expire(String paramString, int paramInt);

    public abstract Boolean expireAt(String paramString, long paramLong);

    public abstract Long ttl(String paramString);

    public abstract Object getSet(String paramString, Serializable paramSerializable);

    public abstract boolean lock(String paramString);

    public abstract void unlock(String paramString);

    public abstract void hset(String paramString, Serializable paramSerializable1, Serializable paramSerializable2);

    public abstract Serializable hget(String paramString, Serializable paramSerializable);

    public abstract void hdel(String paramString, Serializable paramSerializable);

    public abstract boolean setnx(String paramString, Serializable paramSerializable);

    public abstract Long incr(String paramString);

    public abstract void setrange(String paramString1, long paramLong, String paramString2);

    public abstract String getrange(String paramString, long paramLong1, long paramLong2);

    public abstract void sadd(String paramString, Serializable paramSerializable);

    public abstract Set<?> sall(String paramString);

    public abstract boolean sdel(String paramString, Serializable paramSerializable);

    public abstract void sendTopicMessage(String paramString, Serializable paramSerializable);
}
