package com.viewhigh.vadp.portal.sys.persistence;

import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.portal.sys.domain.SysMembership;
import com.viewhigh.vadp.portal.sys.domain.SysUser;

@Component
public class SysMembershipDao extends BaseHibernateDAO {

    public SysMembership findByDomainIdAndUserId(String domainId, String userId)
    {
    	return (SysMembership) this.queryObject("from SysMembership where domainId=? and userId=?", new Object[]{domainId,userId});
    }
    public SysMembership save(SysMembership domainUser) {
		this.addObject(domainUser);
		return domainUser;
	}
}
