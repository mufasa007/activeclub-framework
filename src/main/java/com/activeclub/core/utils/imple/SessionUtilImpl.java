package com.activeclub.core.utils.imple;

import com.activeclub.core.utils.SessionUtil;

/**
 * @Author 59456
 * @Date 2021/9/11
 * @Descrip
 * @Version 1.0
 */
public class SessionUtilImpl implements SessionUtil {
    /**
     * 获取默认账户code
     *
     * @return
     */
    @Override
    public String getUserCode() {
        return "admin";
    }

    /**
     * 获取租户code
     *
     * @return
     */
    @Override
    public String getTenantCode() {
        return "0";
    }
}
