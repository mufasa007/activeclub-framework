package com.activeclub.core.utils.copyUtil;

import java.io.*;
import java.util.List;

/**
 * @Author 59456
 * @Date 2021/9/19
 * @Descrip 深拷贝方法
 * @Version 1.0
 */
public class CopyUtil {

    /**
     * 深拷贝方法
     * @param src 数据源
     * @param <T> 泛型类型
     * @return 全新的对象实体
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> List<T> deepCopy(List<T> src) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(byteOut);
        out.writeObject(src);

        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
        ObjectInputStream in = new ObjectInputStream(byteIn);
        @SuppressWarnings("unchecked")
        List<T> dest = (List<T>) in.readObject();
        return dest;
    }

}
