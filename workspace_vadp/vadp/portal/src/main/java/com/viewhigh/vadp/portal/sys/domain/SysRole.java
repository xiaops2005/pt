package com.viewhigh.vadp.portal.sys.domain;

import com.viewhigh.vadp.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.util.List;

/**
 * 组织用户角色关系，一个用户在不同的组织中具有不同的角色
 */
@Entity
@Table(name = "td_pt_role")
public class SysRole extends BaseEntity {
	@Column(name="domain_id")
    private String domainId;
	@Column(name="name")
    private String name;
	@Column(name="remark")
    private String remark;

    /**
     * 系统保留记录
     */
	@Column(name="systemed")
    private Boolean systemed;
    /**
     * 状态  0：禁用   1：正常
     */
	@Column(name="status")
    private Byte status;

    @Transient
    private List<String> menuIds;

    public SysRole() {
        super();
    }

    public SysRole(String name, String createdBy) {
        super();
        this.setName(name);
        this.setCreatedBy(createdBy);
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public List<String> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<String> menuIds) {
        this.menuIds = menuIds;
    }

    public Boolean getSystemed() {
        return systemed;
    }

    public void setSystemed(Boolean systemed) {
        this.systemed = systemed;
    }
}
