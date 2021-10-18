package com.activeclub.core.utils.LoggerUtil;

import com.activeclub.core.bean.BaseException;
import com.activeclub.core.constants.ErrorCode;
import org.slf4j.Logger;

/**
 * @Author 59456
 * @Date 2021/9/19
 * @Descrip
 * @Version 1.0
 */
public class LoggerUtil {

    /**
     *
     * @param logger
     * @param errorCode
     * @param msg
     */
    public static void error(Logger logger, String errorCode, String msg){
        error( logger,  errorCode,  msg, null);
    }

    /**
     *
     * @param logger
     * @param errorCode
     * @param msg
     * @param e
     */
    public static void error(Logger logger, String errorCode, String msg, Exception e){
        String msgSum = String.format("errorCode:%s;\nmessage:%s;",errorCode,msg);
        if(e != null){
            logger.error(msgSum + e.getMessage());
        }else {
            logger.error(msgSum);
        }
        throw new BaseException(errorCode,msg);
    }

}
