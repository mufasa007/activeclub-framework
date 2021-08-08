package com.activeclub.core.bean;

/**
 * @Author 59456
 * @Date 2021/8/8
 * @Descrip
 * @Version 1.0
 */
public class AcResponse {

    private String code;
    private String msg;
    private Object[] data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object[] getData() {
        return data;
    }

    public void setData(Object[] data) {
        this.data = data;
    }
}
