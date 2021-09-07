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

    // 成功
    SUCCESS("0","success"),
    // 未知异常
    UNKOWN_ERROR("1","UNKOWN_ERROR"),

    PARAM_ERROR("10000","PARAM_ERROR"),
    PARAM_NULL("10001","PARAM_IS_NULL"),
    PARAM_DECODE("10002","PARAM_DECODE_ERROR"),

    // 数据库
    DB_ERROR("20000","success"),
    // 数据重复
    DB_UINDEX_ERROR("20001","duplicate key value violates unique constraint"),

    // 服务
    SEVER_ERROR("30000","success"),
    // 文件
    FILE_ERROR("40000","FILE_ERROR")
    ;

    public String code;
    public String msg;
    ErrorCode(String code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
