package com.poetryappreciation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.util.Date;

/**
 * 诗词
 */
public class PoetryMessage extends Model<PoetryMessage> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 留言人
     */
    @TableField("user")
    private String user;
    /**
     * 诗词id
     */
    @TableField("poetry_id")
    private String poetryId;
    /**
     * 留言内容
     */
    @TableField("message")
    private String message;
    /**
     * 留言时间
     */
    @TableField("message_time")
    private Date messageTime;

    @TableField(exist = false)
    private String messageTimeStr;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }

    public String getMessageTimeStr() {
        return messageTimeStr;
    }

    public void setMessageTimeStr(String messageTimeStr) {
        this.messageTimeStr = messageTimeStr;
    }

    @Override
    public String toString() {
        return "PoetryMessage{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", poetryId='" + poetryId + '\'' +
                ", message='" + message + '\'' +
                ", messageTime=" + messageTime +
                ", messageTimeStr='" + messageTimeStr + '\'' +
                '}';
    }
}
