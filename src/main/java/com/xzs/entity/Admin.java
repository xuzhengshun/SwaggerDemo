package com.xzs.entity;

import io.swagger.annotations.*;
import java.util.Date;

@ApiModel(value="管理员表")
public class Admin {
    /**
     * 自增id
     */
    @ApiModelProperty("自增id")
    private Long id;

    /**
     * 管理员昵称
     */
    @ApiModelProperty("管理员昵称")
    private String adminName;

    /**
     * 账户密码
     */
    @ApiModelProperty("账户密码")
    private String adminPwad;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String iphone;

    /**
     * 邮箱
     */
    @ApiModelProperty("邮箱")
    private String email;

    /**
     * 账户头像
     */
    @ApiModelProperty("账户头像")
    private String adminImg;

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
     * 账户等级
     */
    @ApiModelProperty("账户等级")
    private String adminType;

    /**
     * 账户状态
     */
    @ApiModelProperty("账户状态")
    private String status;

    /**
     * 扩展字段
     */
    @ApiModelProperty("扩展字段")
    private String ext;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPwad() {
        return adminPwad;
    }

    public void setAdminPwad(String adminPwad) {
        this.adminPwad = adminPwad;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdminImg() {
        return adminImg;
    }

    public void setAdminImg(String adminImg) {
        this.adminImg = adminImg;
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

    public String getAdminType() {
        return adminType;
    }

    public void setAdminType(String adminType) {
        this.adminType = adminType;
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
}