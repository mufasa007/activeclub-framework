package com.activeclub.core.bean;

/**
 * @Author 59456
 * @Date 2021/8/12
 * @Descrip
 * @Version 1.0
 */
public class Page {

    private Object data;
    private Integer pageNum;
    private Integer pageSize;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
