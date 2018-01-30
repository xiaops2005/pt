package com.viewhigh.vadp.portal.sys.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.portal.sys.domain.SysRoleMenu;
import com.viewhigh.vadp.portal.sys.domain.SysUserRole;
@SuppressWarnings("unchecked")
@Component
public class SysUserRoleDao extends BaseHibernateDAO{
	
    public List<SysUserRole> findByUserId(String userId)
    {
    	return this.queryObjects("from SysUserRole where userId=? ", new Object[]{userId});

    }
    public List<SysUserRole> findByUserIdAndDomainId(String userId,String domainId)
    {
    	return this.queryObjects("from SysUserRole where userId=? and domainId=? ", new Object[]{userId,domainId});

    }

	public void save(List<SysUserRole> userRoles) {
	
		this.addOrUpdateObjects(userRoles);
	}
    public void deleteInBatch(final List<SysUserRole> userRoles)
    {
    	this.getHibernateTemplate().deleteAll(userRoles);
    }
}
