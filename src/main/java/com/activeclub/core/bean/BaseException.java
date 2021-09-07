package com.activeclub.core.bean;

/**
 * @Author 59456
 * @Date 2021/8/15
 * @Descrip
 * @Version 1.0
 */
public class BaseException extends RuntimeException{

    private String code;

    public BaseException(String code,String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
