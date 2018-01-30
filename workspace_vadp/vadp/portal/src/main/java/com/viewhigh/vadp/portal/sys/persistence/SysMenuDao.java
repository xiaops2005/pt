package com.viewhigh.vadp.portal.sys.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.mock.DataAccessException;
import com.viewhigh.vadp.framework.data.mock.HibernateCallback;
import com.viewhigh.vadp.portal.sys.domain.SysMenu;

@SuppressWarnings("unchecked")
@Component
public class SysMenuDao extends BaseHibernateDAO {

	public SysMenu findById(String id) {
		return super.findById(id, SysMenu.class);
	}

	public List<SysMenu> findAllByIdIn(final List<String> Ids) {
		final String hql = "from SysMenu where id in (:ids) order by parentId,orderNum";
		return (List<SysMenu>) this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws DataAccessException {
				Query query = session.createQuery(hql);
				query.setParameterList("ids", Ids);
				return query.list();
			}
		});

	}

	public List<SysMenu> findByParentId(String parent) {
		return this.queryObjects("from SysMenu where parentId=? order by orderNum", new Object[] { parent });
	}

	public List<SysMenu> findByTypeNot(Integer type) {
		return this.queryObjects("from SysMenu where type <> ? ", new Object[] { type });
	}
	public List<SysMenu> findAll()
    {
    	return this.queryObjects("from SysMenu order by parentId,orderNum");
    }
	public void deleteInBatch(final List<SysMenu> menus)
    {
    	this.getHibernateTemplate().deleteAll(menus);
    }

	public SysMenu save(SysMenu menu) {
		this.addObject(menu);
		return menu;
	}
}
