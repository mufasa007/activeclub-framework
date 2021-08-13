package com.activeclub.core.service.MqService;

/**
 * @Author 59456
 * @Date 2021/8/14
 * @Descrip 还可以控制开关
 * @Version 1.0
 */
public interface MqService {

    /**
     * 系统初始化
     */
    void initMq();

    /**
     * 消息发生
     */
    void sendMq();

    /**
     * 消息接受
     */
    Object listeningMq();

}
