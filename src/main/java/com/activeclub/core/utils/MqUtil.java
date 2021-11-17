package com.activeclub.core.utils;

import org.springframework.stereotype.Component;

/**
 * @Author 59456
 * @Date 2021/8/14
 * @Descrip 消息中间件工具
 * @Version 1.0
 */
interface MqUtil {

    /**
     * 消息发送
     * @param args
     */
    void send(Object... args);

    /**
     * 消息发送
     * @param args
     */
    void sendMsgDto(Object... args);
}
