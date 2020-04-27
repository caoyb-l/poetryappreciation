package com.poetryappreciation.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

/**
 * 诗词
 */
public class Poetry extends Model<Poetry> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 诗词名称
     */
    @TableField("name")
    private String name;
    /**
     * 诗词分类
     */
    @TableField("type")
    private String type;
    /**
     * 注解
     */
    @TableField("annotation")
    private String annotation;
    /**
     * 翻译
     */
    @TableField("translate")
    private String translate;
    /**
     * 诗词内容
     */
    @TableField("content")
    private String content;
    /**
     * 作者
     */
    @TableField("author")
    private String author;
    /**
     * 作者介绍
     */
    @TableField("author_desc")
    private String authorDesc;
    /**
     * 浏览量
     */
    @TableField("browse_count")
    private Integer browseCount;
    /**
     * 发布人
     */
    @TableField("publish_user")
    private String publishUser;
    /**
     * 点赞数
     */
    @TableField("fabulous_count")
    private Integer fabulousCount;
    /**
     * 点赞数
     */
    @TableField("url")
    private String url;

    @TableField(exist = false)
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthorDesc() {
        return authorDesc;
    }

    public void setAuthorDesc(String authorDesc) {
        this.authorDesc = authorDesc;
    }


    public String getPublishUser() {
        return publishUser;
    }

    public void setPublishUser(String publishUser) {
        this.publishUser = publishUser;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public Integer getFabulousCount() {
        return fabulousCount;
    }

    public void setFabulousCount(Integer fabulousCount) {
        this.fabulousCount = fabulousCount;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Poetry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", annotation='" + annotation + '\'' +
                ", translate='" + translate + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", authorDesc='" + authorDesc + '\'' +
                ", browseCount=" + browseCount +
                ", publishUser='" + publishUser + '\'' +
                ", fabulousCount=" + fabulousCount +
                ", url=" + url +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
