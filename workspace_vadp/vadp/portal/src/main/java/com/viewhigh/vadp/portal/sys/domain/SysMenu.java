package com.viewhigh.vadp.portal.sys.domain;

import com.viewhigh.vadp.common.domain.BaseEntity;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "td_pt_menu")
public class SysMenu extends BaseEntity{
    private static final long serialVersionUID = 1L;

    /**
     * 父菜单ID，一级菜单为0
     */
    @Column(name="parent_id")
    private String parentId;

    /**
     * 父菜单名称
     */
    @Column(name="parent_name")
    private String parentName;

    /**
     * 菜单名称
     */
    @Column(name="name")
    private String name;

    /**
     * 菜单URL
     */
    @Column(name="url")
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    @Column(name="perms")
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    @Column(name="type")
    private Integer type;

    /**
     * 菜单图标
     */
    @Column(name="icon")
    private String icon;

    /**
     * 排序
     */
    @Column(name="order_num")
    private Integer orderNum;

    /**
     * 设置：父菜单ID，一级菜单为0
     * @param parentId 父菜单ID，一级菜单为0
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取：父菜单ID，一级菜单为0
     * @return String
     */
    public String getParentId() {
        return parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    /**
     * 设置：菜单名称
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取：菜单名称
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * 设置：菜单URL
     * @param url 菜单URL
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取：菜单URL
     * @return String
     */
    public String getUrl() {
        return url;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 设置：菜单图标
     * @param icon 菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取：菜单图标
     * @return String
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 设置：排序
     * @param orderNum 排序
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取：排序
     * @return Integer
     */
    public Integer getOrderNum() {
        return orderNum;
    }
}
