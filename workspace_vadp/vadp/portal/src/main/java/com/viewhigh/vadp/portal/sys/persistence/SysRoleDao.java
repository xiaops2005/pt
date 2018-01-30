package com.viewhigh.vadp.portal.sys.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.portal.sys.domain.SysMenu;
import com.viewhigh.vadp.portal.sys.domain.SysRole;
@SuppressWarnings("unchecked")
@Component
public class SysRoleDao extends BaseHibernateDAO {


    public SysRole findById(String id)
    {
    	return this.findById(id, SysRole.class);
    }
    public List<SysRole> findAllByName(String name)
    {
    	return this.queryObjects("from SysRole where name=? ", new Object[]{name});

    }
    public List<SysRole> findAllByDomainId(String domainId)
    {
    	return this.queryObjects("from SysRole where domainId=? ", new Object[]{domainId});
    }
    public void deleteInBatch(final List<SysRole> roles)
    {
    	this.getHibernateTemplate().deleteAll(roles);
    }

	public SysRole save(SysRole role) {
		this.addObject(role);
		return role;
	}
}
