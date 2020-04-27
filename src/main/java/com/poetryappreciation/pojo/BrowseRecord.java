package com.poetryappreciation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * 用户浏览记录表
 */
public class BrowseRecord extends Model<BrowseRecord> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户
     */
    @TableField("user")
    private String user;
    /**
     * 诗词id
     */
    @TableField("poetry_id")
    private String poetryId;
    /**
     * 浏览时间
     */
    @TableField("browse_time")
    private Date browseTime;

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

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    @Override
    public String toString() {
        return "BrowseRecord{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", poetryId='" + poetryId + '\'' +
                ", browseTime='" + browseTime + '\'' +
                '}';
    }
}
