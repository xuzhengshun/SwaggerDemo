package com.xzs.entity;

import io.swagger.annotations.*;
import java.util.Date;

@ApiModel(value="博客表")
public class Blog {
    /**
     * 自增id
     */
    @ApiModelProperty("自增id")
    private Long id;

    /**
     * 博客名称
     */
    @ApiModelProperty("博客名称")
    private String blogName;

    /**
     * 博客摘要
     */
    @ApiModelProperty("博客摘要")
    private String blogAbstract;

    /**
     * 博客封面图片
     */
    @ApiModelProperty("博客封面图片")
    private String blogImg;

    /**
     * 博客所属人id
     */
    @ApiModelProperty("博客所属人id")
    private String blogAdminId;

    /**
     * 分类名称
     */
    @ApiModelProperty("分类名称")
    private String classifyName;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @ApiModelProperty("最后修改时间")
    private Date updateTime;

    /**
     * 博客状态
     */
    @ApiModelProperty("博客状态")
    private String status;

    /**
     * 扩展字段
     */
    @ApiModelProperty("扩展字段")
    private String ext;

    /**
     * 博客内容
     */
    @ApiModelProperty("博客内容")
    private String blogContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogAbstract() {
        return blogAbstract;
    }

    public void setBlogAbstract(String blogAbstract) {
        this.blogAbstract = blogAbstract;
    }

    public String getBlogImg() {
        return blogImg;
    }

    public void setBlogImg(String blogImg) {
        this.blogImg = blogImg;
    }

    public String getBlogAdminId() {
        return blogAdminId;
    }

    public void setBlogAdminId(String blogAdminId) {
        this.blogAdminId = blogAdminId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getBlogContent() {
        return blogContent;
    }

    public void setBlogContent(String blogContent) {
        this.blogContent = blogContent;
    }
}