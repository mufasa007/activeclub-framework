package com.activeclub.core.common.Handler;

import com.activeclub.core.bean.BaseException;
import com.activeclub.core.bean.BaseResponse;
import com.activeclub.core.constants.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 59456
 * @Date 2021/8/23
 * @Descrip
 * @Version 1.0
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public BaseResponse baseExceptionHandler(Exception e){
        BaseResponse baseResponse = new BaseResponse();
        if(e instanceof BaseException){
            BaseException be = (BaseException) e;
            baseResponse.setCode(be.getCode());
            baseResponse.setMsg(be.getMessage());
        }else {
            baseResponse.setCode(ErrorCode.UNKOWN_ERROR.code);
            baseResponse.setMsg(ErrorCode.UNKOWN_ERROR.msg);
        }

        return baseResponse;
    }
}
