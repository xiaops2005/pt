package com.viewhigh.vadp.portal.sys.persistence;

import java.util.List;

import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.portal.sys.domain.SysMenu;
import com.viewhigh.vadp.portal.sys.domain.SysRoleMenu;
@SuppressWarnings("unchecked")
@Component
public class SysRoleMenuDao extends BaseHibernateDAO {


    public SysRoleMenu findById(String id)
    {
    	return this.findById(id, SysRoleMenu.class);

    }

    public List<SysRoleMenu> findAllByRoleId(String roleId){
    	return this.queryObjects("from SysRoleMenu where roleId=? ", new Object[]{roleId});
    }
    public void deleteInBatch(final List<SysRoleMenu> roleMenus)
    {
    	this.getHibernateTemplate().deleteAll(roleMenus);
    }

	public void save(List<SysRoleMenu> roleMenus) {
	
		this.addOrUpdateObjects(roleMenus);
	}
}
