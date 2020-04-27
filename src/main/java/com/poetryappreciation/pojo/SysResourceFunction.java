package com.poetryappreciation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

public class SysResourceFunction {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("resourceId")
    private Integer resourceId;

    @TableField("resourceName")
    private String resourceName;

    @TableField("function")
    private String function;

    @TableField("functionName")
    private String functionName;

    @TableField("isDel")
    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "SysResourceFunction{" +
                "id=" + id +
                ", resourceId=" + resourceId +
                ", resourceName='" + resourceName + '\'' +
                ", function='" + function + '\'' +
                ", functionName='" + functionName + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}
