package com.activeclub.core.constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author 59456
 * @Date 2021/8/8
 * @Descrip
 * @Version 1.0
 */
public class NormalConstants {

    /**
     * 数据来源
     */
    public static Short ORIGIN_TYPE_OURSELF = 0;
    public static Short ORIGIN_TYPE_BYTEDANCE = 1;


    public static String NULL_CANSTANTS1 = "";
    public static String NULL_CANSTANTS2 = "null";

    public static String ADMIN = "admin";
    public static String DEFAULT_TENANT_CODE = "0";

    /**
     * 时间设置
     */
    public static Date startTime;
    public static Date endTime;
    static {
        try {
            startTime= new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2000-01-01").getTime());
            endTime= new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2099-12-31").getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
