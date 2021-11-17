package com.activeclub.core.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zjk
 * @date 2021/11/2
 */
@Component
public class Configs {

  @Value("${debug.enable:false}")
  private boolean debugEnable;

  @Value("${mq.enable:false}")
  private boolean mqEnable;

  public boolean isMqEnable() {
    return mqEnable;
  }

  public void setMqEnable(boolean mqEnable) {
    this.mqEnable = mqEnable;
  }

  public boolean isDebugEnable() {
    return debugEnable;
  }

  public void setDebugEnable(boolean debugEnable) {
    this.debugEnable = debugEnable;
  }
}
