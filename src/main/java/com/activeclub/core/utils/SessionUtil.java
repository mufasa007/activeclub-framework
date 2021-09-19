package com.activeclub.core.utils;

/**
 * @Author 59456
 * @Date 2021/8/14
 * @Descrip
 * @Version 1.0
 */
public interface SessionUtil {

    /**
     * 获取默认账户code
     * @return
     */
    String getUserCode();

    /**
     * 获取租户code
     * @return
     */
    String getTenantCode();

}
