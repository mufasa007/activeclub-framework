package com.activeclub.core.constants;

/**
 * @Author 59456
 * @Date 2021/8/8
 * @Descrip
 * @Version 1.0
 *
 *
 *
 */
public enum ErrorCode {

    SUCCESS("0","success"),// 成功


    PARAM_ERROR("10000","PARAM_ERROR"),
    PARAM_NULL("10001","PARAM_IS_NULL"),
    PARAM_DECODE("10002","PARAM_DECODE_ERROR"),

    DB_ERROR("20000","success"),// 数据库

    SEVER_ERROR("30000","success"),// 服务

    FILE_ERROR("40000","FILE_ERROR")// 文件
    ;

    public String code;
    public String msg;
    ErrorCode(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
