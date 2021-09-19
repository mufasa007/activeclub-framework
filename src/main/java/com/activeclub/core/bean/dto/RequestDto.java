package com.activeclub.core.bean.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author 59456
 * @Date 2021/9/19
 * @Descrip
 * @Version 1.0
 */
@Data
public class RequestDto {

    /**
     * 请求url地址
     */
    private String url;

    /**
     * body数据体
     */
    private String jsonStr;

    /**
     * 参数
     */
    private Map<String, String> params;

    /**
     * 入参个数
     */
    private Integer countParams = 0;

    /**
     * header参数
     */
    private Map<String, String> headers;

    /**
     * header个数
     */
    private Integer countHeaders = 0;

    /**
     * 是否有文件下载
     */
    private Boolean hasServletResponse = false;

    public void addParam(String key, String value){
        if(countParams==0){
            // 入参空值
            params = new HashMap<>();
        }else if(params.containsKey(key)){
            // 入参已有，且是刷新数据
            params.put(key, value);
        }else {
            // 入参已有，且是新增数据
            params.put(key, value);
            countParams++;
        }
    }

    public void addHeader(String key, String value){
        if(countHeaders==0){
            // 入参空值
            headers = new HashMap<>();
        }else if(headers.containsKey(key)){
            // 入参已有，且是刷新数据
            headers.put(key, value);
        }else {
            // 入参已有，且是新增数据
            headers.put(key, value);
            countHeaders++;
        }
    }

    public void deleteParam(String key){
        if(countParams == 0){
            return;
        }

        if(params.containsKey(key)){
            params.remove(key);
            countParams--;
        }
    }

    public void deleteHeader(String key){
        if(countHeaders == 0){
            return;
        }

        if(headers.containsKey(key)){
            headers.remove(key);
            countHeaders--;
        }
    }

}
