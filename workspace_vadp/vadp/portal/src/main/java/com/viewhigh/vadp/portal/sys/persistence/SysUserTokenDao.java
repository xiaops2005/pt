package com.viewhigh.vadp.portal.sys.persistence;

import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.portal.sys.domain.SysMembership;
import com.viewhigh.vadp.portal.sys.domain.SysUserToken;
@SuppressWarnings("unchecked")
@Component
public class SysUserTokenDao extends BaseHibernateDAO {


    public SysUserToken findByUserId(String userId)
    {
    	return (SysUserToken) this.queryObject("from SysUserToken where userId=? ", new Object[]{userId});

    }
    public SysUserToken findByToken(String token)
    {
    	return (SysUserToken) this.queryObject("from SysUserToken where token=? ", new Object[]{token});

    }
    public SysUserToken save(SysUserToken token) {
    	this.getHibernateTemplate().saveOrUpdate(token);
		return token;
	}

}
