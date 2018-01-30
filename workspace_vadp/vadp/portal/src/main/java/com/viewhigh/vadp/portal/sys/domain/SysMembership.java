package com.viewhigh.vadp.portal.sys.domain;

import com.viewhigh.vadp.common.domain.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 组织和用户关系
 */
@Entity
@Table(name = "td_pt_domain_user")
public class SysMembership extends BaseEntity {
	@Column(name="domain_id")
    private String domainId;
	@Column(name="user_id")
    private String userId;

    public SysMembership() {
        super();
    }

    public SysMembership(String domainId, String userId, String createdBy) {
        super();
        this.setDomainId(domainId);
        this.setUserId(userId);
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


}
