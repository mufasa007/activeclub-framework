package com.activeclub.core.service.MqService.impl;

import com.activeclub.core.constants.Configs;
import com.activeclub.core.service.MqService.MqService;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author 59456
 * @Date 2021/8/14
 * @Descrip
 * @Version 1.0
 */
@Service
public class RabbitMqServiceImpl implements MqService {

    @Autowired
    private Configs configs;

    @Resource
    RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    public void start() {
        if (configs.isMqEnable()) {
            rabbitListenerEndpointRegistry.start();
        } else {
            rabbitListenerEndpointRegistry.stop();
        }
        System.out.println("=================== Rabbitmq:" + configs.isMqEnable() + "===================");
    }


    @Override
    public void initMq() {

    }

    @Override
    public void sendMq() {

    }

    @Override
    public Object listeningMq() {
        return null;
    }

}
