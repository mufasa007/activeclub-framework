package com.activeclub.core.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.CommandLineRunner;

/**
 * @Author 59456
 * @Date 2021/11/2
 * @Descrip 系统启动前、启动后的初始化操作
 * @Version 1.0
 */
public abstract class SystemInit implements InitializingBean, CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

}
