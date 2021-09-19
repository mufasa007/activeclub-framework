package com.activeclub.core.utils;

/**
 * @Author 59456
 * @Date 2021/8/14
 * @Descrip
 * @Version 1.0
 */
public interface CacheUtil {

    /**
     * 设置k-v
     * @param key
     * @param obj
     */
    void put(String key, Object obj);

    void put(String key, Object obj,long time);

//    void put(String key, Object obj);

    /**
     * 获取k
     * @param key
     * @return
     */
    Object get(String key);
}
