package com.kingleadsw.ysm.caches;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Set;

@Service
public class RedisCache   implements Cache
{
    @Resource
    RedisTemplate<Serializable,Serializable> redisTemplate;


    @Override
    public Object get(String paramString) {
        return null;
    }

    @Override
    public Set<Object> getAll(String paramString) {
        return null;
    }

    @Override
    public void set(String paramString, Serializable paramSerializable, int paramInt) {

    }

    @Override
    public void set(String paramString, Serializable paramSerializable) {

    }

    @Override
    public Boolean exists(String paramString) {
        return null;
    }

    @Override
    public void del(String paramString) {

    }

    @Override
    public void delAll(String paramString) {

    }

    @Override
    public String type(String paramString) {
        return null;
    }

    @Override
    public Boolean expire(String paramString, int paramInt) {
        return null;
    }

    @Override
    public Boolean expireAt(String paramString, long paramLong) {
        return null;
    }

    @Override
    public Long ttl(String paramString) {
        return null;
    }

    @Override
    public Object getSet(String paramString, Serializable paramSerializable) {
        return null;
    }

    @Override
    public boolean lock(String paramString) {
        return false;
    }

    @Override
    public void unlock(String paramString) {

    }

    @Override
    public void hset(String paramString, Serializable paramSerializable1, Serializable paramSerializable2) {

    }

    @Override
    public Serializable hget(String paramString, Serializable paramSerializable) {
        return null;
    }

    @Override
    public void hdel(String paramString, Serializable paramSerializable) {

    }

    @Override
    public boolean setnx(String paramString, Serializable paramSerializable) {
        return false;
    }

    @Override
    public Long incr(String paramString) {
        return null;
    }

    @Override
    public void setrange(String paramString1, long paramLong, String paramString2) {

    }

    @Override
    public String getrange(String paramString, long paramLong1, long paramLong2) {
        return null;
    }

    @Override
    public void sadd(String paramString, Serializable paramSerializable) {

    }

    @Override
    public Set<?> sall(String paramString) {
        return null;
    }

    @Override
    public boolean sdel(String paramString, Serializable paramSerializable) {
        return false;
    }

    @Override
    public void sendTopicMessage(String paramString, Serializable paramSerializable) {

    }
}
