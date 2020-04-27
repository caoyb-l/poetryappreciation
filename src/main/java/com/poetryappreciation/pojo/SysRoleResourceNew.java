package com.poetryappreciation.pojo;

import com.baomidou.mybatisplus.annotation.TableField;

/**
 * 角色与菜单关系实体
 */
public class SysRoleResourceNew {

    private static final long serialVersionUID = 1L;

    @TableField("roleId")
    private Integer roleId;

    @TableField("resourceId")
    private Integer resourceId;

    @TableField("functionId")
    private Integer functionId;


    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Integer functionId) {
        this.functionId = functionId;
    }


    @Override
    public String toString() {
        return "SysRoleResourceNew{" +
                "roleId=" + roleId +
                ", resourceId=" + resourceId +
                ", functionId=" + functionId +
                '}';
    }
}
