package com.activeclub.core.utils.imple;

import com.activeclub.core.utils.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author 59456
 * @Date 2021/9/11
 * @Descrip
 * @Version 1.0
 */
public class CacheUtilImpl implements CacheUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, Object obj,long time) {
        redisTemplate.opsForValue().set(key,obj,time);
    }

    /**
     * 设置k-v
     *
     * @param key
     * @param obj
     */
    @Override
    public void put(String key, Object obj) {

    }

    @Override
    public Object get(String key) {
        return null;
    }
}
