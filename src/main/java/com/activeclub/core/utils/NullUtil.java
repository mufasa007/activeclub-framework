package com.activeclub.core.utils;

import java.util.List;

import static com.activeclub.core.constants.NormalConstants.NULL_CANSTANTS1;
import static com.activeclub.core.constants.NormalConstants.NULL_CANSTANTS2;

/**
 * @Author 59456
 * @Date 2021/8/8
 * @Descrip
 * @Version 1.0
 */
public class NullUtil {



    /**
     * 检查list是否为空
     * @param list
     * @return
     */
    public static boolean listIsNull(List<?> list){
        if(null == list || list.size()==0){
            return true;
        }
        return false;
    }

    /**
     * 检查string是否为空
     * @param string
     * @return
     */
    public static boolean strIsNull(String string){
        if(null == string || NULL_CANSTANTS1.equals(string)){
            return true;
        }
        return false;
    }




}
