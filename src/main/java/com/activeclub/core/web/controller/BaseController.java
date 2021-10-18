package com.activeclub.core.web.controller;

import com.activeclub.core.bean.BaseException;
import com.activeclub.core.bean.BaseResponse;
import com.activeclub.core.constants.ErrorCode;
import com.activeclub.core.utils.NullUtil;

/**
 * @Author 59456
 * @Date 2021/8/15
 * @Descrip
 * @Version 1.0
 */
public class BaseController {

    /**
     * 直接返回成功默认信息
     * @return BaseResponse
     */
    public BaseResponse success(){
        return success("");
    }

    /**
     * 成功返回空数据
     * @param msg 自定义成功信息
     * @return
     */
    public BaseResponse success(String msg){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ErrorCode.SUCCESS.code);
        if(NullUtil.strIsNull(msg)){
            baseResponse.setMsg(ErrorCode.SUCCESS.msg);
        }else {
            baseResponse.setMsg(msg);
        }
        return baseResponse;
    }

    /**
     *
     * @param msg 自定义成功信息
     * @param data 返回数据
     * @return
     */
    public BaseResponse success(String msg,Object data){
        BaseResponse baseResponse = success(msg);
        baseResponse.setData(data);
        return baseResponse;
    }

    /**
     * 返回错误的默认信息
     * @param errorCode
     * @return
     */
    public BaseResponse error(ErrorCode errorCode){
        BaseResponse baseResponse =  new BaseResponse();
        baseResponse.setCode(errorCode.code);
        baseResponse.setMsg(errorCode.msg);
        return baseResponse;
    }

    public BaseResponse error(BaseException baseException){
        BaseResponse baseResponse =  new BaseResponse();
        baseResponse.setCode(baseException.getCode());
        baseResponse.setMsg(baseException.getMessage());
        return baseResponse;
    }

    /**
     * 返回错误的信息，但是自定义错误信息
     * @param errorCode
     * @param msg
     * @return
     */
    public BaseResponse error(ErrorCode errorCode,String msg){
        BaseResponse baseResponse =  new BaseResponse();
        baseResponse.setCode(errorCode.code);
        baseResponse.setMsg(errorCode.msg);
        return baseResponse;
    }



    /**
     *
     * @param code 错误码
     * @param msg 错误信息
     * @return
     */
    public BaseResponse error(String code,String msg){
        BaseResponse baseResponse =  new BaseResponse();
        baseResponse.setCode(code);
        baseResponse.setMsg(msg);
        return baseResponse;
    }


}
