package com.viewhigh.vadp.portal.sys.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import com.viewhigh.vadp.portal.sys.domain.SysDomain;
@SuppressWarnings("unchecked")
@Component
public class SysDomainDao extends BaseHibernateDAO {


    public SysDomain findById(String id)
    {
    	return super.findById(id, SysDomain.class);
    }
    public List<SysDomain> findAllByName(String name)
    {
    	return this.queryObjects("from SysDomain where name=? ", new Object[]{name});
    }
    public Page<SysDomain> findAll(Pageable pageable)
    {
    	QueryResult qr = this.queryObjectsByPage("from SysDomain ", null, pageable.getPageSize(), pageable.getPageNumber());
		Page<SysDomain> result = new PageImpl<SysDomain>(qr.getResult(), pageable, qr.getRecordCount());
		return result;
    }
    public SysDomain save(SysDomain domain)
    {
    	this.addObject(domain);
    	return domain;
    }
    public void deleteInBatch(final List<SysDomain> domains)
    {
    	this.getHibernateTemplate().deleteAll(domains);
    }
    
}
