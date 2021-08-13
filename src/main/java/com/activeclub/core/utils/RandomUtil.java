package com.activeclub.core.utils;

import java.util.UUID;

/**
 * @Author 59456
 * @Date 2021/8/13
 * @Descrip
 * @Version 1.0
 */
public class RandomUtil {

    public static String getRandomCode(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static String getUindexNumber(){
        return String.format("null_%s",getRandomCode());
    }

//    public static void main(String[] args) {
//        for(int i =0;i<10;i++){
//            System.out.println(getUindexNumber());
//        }
//    }
}
