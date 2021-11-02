package com.activeclub.core.utils;

import com.activeclub.core.bean.BaseException;
import com.activeclub.core.constants.Configs;
import com.activeclub.core.constants.ErrorCode;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author zjk
 * @date 2021/11/2
 * 用于输入类名和方法名然后进行实例化使用
 */
@Component
public class ReflectUtil {
  private final Logger log = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private ApplicationContext applicationContext;

  @Autowired
  private Configs configs;

  /**
   * 通过反射执行逻辑
   *
   * @param className  类名
   * @param methodName 方法名
   * @param params     参数
   * @return 结果
   */
  public Object execute(String className, String methodName, Object[] params) {
    try {
      Class<?> aClass = Class.forName(className);
      Object bean = applicationContext.getBean(aClass);
      Method[] declaredMethods = aClass.getDeclaredMethods();
      for (Method method : declaredMethods) {
        if (method.getName().equals(methodName)) {
          if (configs.isDebugEnable()) {
            log.info("##反射执行[{}]的[{}]方法，参数为[{}]", className, methodName, JSON.toJSONString(params));
          }
          return method.invoke(bean, params);
        }
      }
      log.error("##反射执行[{}]的[{}]方法，参数为[{}]方法不存在!", className, methodName, JSON.toJSONString(params));
      throw new BaseException(ErrorCode.PARAM_NOT_FOUND.code, String.format("##反射执行[%s]的[%s]方法不存在]", className, methodName));
    } catch (Exception e) {
      log.error("##反射执行[{}]的[{}]方法，参数为[{}]执行报错!", className, methodName, JSON.toJSONString(params), e);
      throw new BaseException(ErrorCode.UNKOWN_ERROR.code, String.format("##反射执行[%s]的[%s]发生未知错误]", className, methodName));
    }

  }



}
