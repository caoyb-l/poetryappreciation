package com.poetryappreciation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * 用户点赞
 */
public class UserFabulous extends Model<UserFabulous> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户
     */
    @TableField("user")
    private String user;
    /**
     * 诗词ID
     */
    @TableField("poetry_id")
    private String poetryId;

    /**
     * 点赞日期
     */
    @TableField("fabulous_time")
    private Date fabulousTime;

    /**
     * 新旧标识
     */
    @TableField("flag")
    private String flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPoetryId() {
        return poetryId;
    }

    public void setPoetryId(String poetryId) {
        this.poetryId = poetryId;
    }

    public Date getFabulousTime() {
        return fabulousTime;
    }

    public void setFabulousTime(Date fabulousTime) {
        this.fabulousTime = fabulousTime;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "UserFabulous{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", poetryId='" + poetryId + '\'' +
                ", fabulousTime=" + fabulousTime +
                ", flag='" + flag + '\'' +
                '}';
    }
}
