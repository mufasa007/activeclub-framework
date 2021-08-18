package com.activeclub.core.web.dao;

import java.util.List;

/**
 * @Author 59456
 * @Date 2021/8/17
 * @Descrip
 * @Version 1.0
 */
public interface BaseDao <T>{

    void insert(T t);
    void delete(String code);
    void update(T t);
    void upsert(T t);

    void insertList(List<T> tList);
    void deleteList(List<String> codeList);
    void updateList(List<T> tList);
    void upsertList(List<T> tList);

    Integer findCountByPage(T t);
    List<T> findByPage(T t);
}
