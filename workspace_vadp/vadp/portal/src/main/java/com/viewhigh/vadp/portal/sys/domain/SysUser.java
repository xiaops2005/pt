package com.viewhigh.vadp.portal.sys.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.viewhigh.vadp.common.domain.BaseEntity;

import javax.persistence.*;


import java.util.*;

/**
 * 系统用户
 */
@Entity
@Table(name = "td_pt_user")
public class SysUser extends BaseEntity {

    @Column(name="username",unique = true, nullable = false, length = 50)
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name="password",nullable = false, length = 100)
    private String password;
    @Column(name="name")
    private String name;
    @Column(name="salt")
    private String salt;

    /**
     * 当前组织 Id
     */
    @Column(name="domain_id")
    private String domainId;
    @Column(name="email")
    private String email;
    @Column(name="mobile")
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    @Column(name="status")
    private Byte status;

    @Transient
    private List<String> roleIds;

    @Transient
    private List<String> domainIds;

    public SysUser() {
        super();
    }

    public SysUser(String username, String password, String salt, String createdBy) {
        super();

        this.setUsername(username);
        this.setPassword(password);
        this.setSalt(salt);
        this.setCreatedBy(createdBy);
    }

    /**
     * 设置：
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.setId(userId);
    }

    /**
     * 获取：
     *
     * @return String
     */
    public String getUserId() {
        return this.getId();
    }

    /**
     * 设置：用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取：用户名
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置：密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取：密码
     *
     * @return String
     */

    public String getPassword() {
        return password;
    }

    /**
     * 设置：邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取：邮箱
     *
     * @return String
     */
    public String getEmail() {
        return email;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    /**
     * 设置：手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取：手机号
     *
     * @return String
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置：状态  0：禁用   1：正常
     *
     * @param status 状态  0：禁用   1：正常
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * 获取：状态  0：禁用   1：正常
     *
     * @return Byte
     */
    public Byte getStatus() {
        return status;
    }


    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }


    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDomains() {

        return domainIds;
    }

    public void setDomains(List<String> domainIds) {
        this.domainIds = domainIds;
    }
}