package com.viewhigh.vadp.portal.sys.domain;

import com.viewhigh.vadp.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织用户角色关系，一个用户在不同的组织中具有不同的角色
 */
@Entity
@Table(name = "td_pt_user_role")
public class SysUserRole extends BaseEntity {
	@Column(name="domain_id")
    private String domainId;
	@Column(name="user_id")
    private String userId;
	@Column(name="role_id")
    private String roleId;

    public SysUserRole() {
        super();
    }

    public SysUserRole(String domainId, String userId,String roleId, String createdBy) {
        super();
        this.setDomainId(domainId);
        this.setUserId(userId);
        this.setRoleId(roleId);
        this.setCreatedBy(createdBy);
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}
