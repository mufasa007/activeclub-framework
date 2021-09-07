package com.activeclub.core.web.service;

import com.activeclub.core.bean.Page;

import java.util.List;

/**
 * @Author 59456
 * @Date 2021/9/7
 * @Descrip
 * @Version 1.0
 */
public interface BaseService<T> {

    void insert(T t);

    void delete(String code);

    void update(T t);

    void upsert(T t);

    void insertList(List<T> tList);

    void deleteList(List<String> codeList);

    void updateList(List<T> tList);

    void upsertList(List<T> tList);

    Object detail(String code);

    List<?> detailList(List<String> codeList);

    Page findByPage(T t);

}
