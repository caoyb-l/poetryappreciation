package com.poetryappreciation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * 用户点赞
 */
public class UserCollection extends Model<UserCollection> {

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
    @TableField("collection_time")
    private Date collectionTime;

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

    public Date getCollectionTime() {
        return collectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        this.collectionTime = collectionTime;
    }

    @Override
    public String toString() {
        return "UserCollection{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", poetryId='" + poetryId + '\'' +
                ", collectionTime=" + collectionTime +
                '}';
    }
}
