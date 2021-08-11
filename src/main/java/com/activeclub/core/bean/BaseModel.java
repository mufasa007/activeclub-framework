package com.activeclub.core.bean;

import java.util.Date;

/**
 * @Author 59456
 * @Date 2021/8/11
 * @Descrip
 * @Version 1.0
 */
public class BaseModel {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 状态码:-id已删除、0正常、1新增、2修改
     */
    private Long flag;

    /**
     * 唯一键:部门code等
     */
    private String code;

    /**
     * 数据来源
     */
    private Short originType;

    /**
     * 信息级别
     */
    private Short infoLevel;

    /**
     * 扩展字段群
     */
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;

    /**
     * 创建时间
     */
    private Date creatTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 修改者
     */
    private String modifier;

    public Short getOriginType() {
        return originType;
    }

    public void setOriginType(Short originType) {
        this.originType = originType;
    }

    public Short getInfoLevel() {
        return infoLevel;
    }

    public void setInfoLevel(Short infoLevel) {
        this.infoLevel = infoLevel;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public String getExt4() {
        return ext4;
    }

    public void setExt4(String ext4) {
        this.ext4 = ext4;
    }

    public String getExt5() {
        return ext5;
    }

    public void setExt5(String ext5) {
        this.ext5 = ext5;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
