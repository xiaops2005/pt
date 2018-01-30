package com.viewhigh.vadp.portal.sys.persistence;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.mock.DataAccessException;
import com.viewhigh.vadp.framework.data.mock.HibernateCallback;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import com.viewhigh.vadp.portal.sys.domain.SysUser;

@SuppressWarnings("unchecked")
@Component
public class SysUserDao extends BaseHibernateDAO {

	public SysUser findById(String id) {
		return super.findById(id, SysUser.class);

	}

	public SysUser findByUsername(String username) {
		return (SysUser) this.queryObject("from SysUser where username=? ", new Object[] { username });

	}

	public SysUser findByEmail(String email) {
		return (SysUser) this.queryObject("from SysUser where email=? ", new Object[] { email });

	}

	public List<SysUser> findAll() {
		return this.queryObjects("from SysUser ");

	}

	public Page<SysUser> findAllByDomainId(String domainId, Pageable pageable) {
		QueryResult qr = this.queryObjectsByPage("from SysUser where domainId=? ", new Object[] { domainId }, pageable.getPageSize(), pageable.getPageNumber());
		Page<SysUser> result = new PageImpl<SysUser>(qr.getResult(), pageable, qr.getRecordCount());
		return result;
	}

	public void delete(SysUser user) {
		super.removeObject(user);
	}

	public void deleteInBatch(final List<SysUser> users) {
		this.getHibernateTemplate().deleteAll(users);
	}

	/**
	 * 查询用户的所有权限
	 *
	 * @param userId 用户ID
	 */
	//    @Query(value = "select m.perms from td_pt_user_role ur " +
	//            "LEFT JOIN td_pt_role_menu rm on ur.role_id = rm.role_id " +
	//            "LEFT JOIN td_pt_menu m on rm.menu_id = m.menu_id " +
	//            "where ur.user_id = ?1 ", nativeQuery = true)
	//    @Query(value = "select m.perms from SysUserRole ur left join SysRoleMenu rm on ur.roleId=rm.roleId " +
	//            "left join SysMenu m on rm.MenuId = m.id " +
	//            "where ur.userId =?1")
	public List<String> queryAllPerms(final String userId) {
		final String sql = "select m.perms from td_pt_user_role ur " + "LEFT JOIN td_pt_role_menu rm on ur.role_id = rm.role_id " + "LEFT JOIN td_pt_menu m on rm.menu_id = m.menu_id "
				+ "where ur.user_id = ? ";
		return this.getHibernateTemplate().execute(new HibernateCallback<List<String>>() {
			@Override
			public List<String> doInHibernate(Session session) throws DataAccessException {
				Query query = session.createSQLQuery(sql);
				query.setParameter(0, userId);
				return query.list();
			}
		});

	}

	/**
	 * 查询用户的所有菜单ID
	 */
	//    @Query(value = "select distinct rm.menu_id from td_pt_user_role ur " +
	//            "LEFT JOIN td_pt_role_menu rm on ur.role_id = rm.role_id " +
	//            "where ur.user_id = ?1", nativeQuery = true)
	public List<String> queryAllMenuId(final String userId) {
		final String sql = "select distinct rm.menu_id from td_pt_user_role ur " + "LEFT JOIN td_pt_role_menu rm on ur.role_id = rm.role_id " + "where ur.user_id = ?";
		return this.getHibernateTemplate().execute(new HibernateCallback<List<String>>() {
			@Override
			public List<String> doInHibernate(Session session) throws DataAccessException {
				Query query = session.createSQLQuery(sql);
				query.setParameter(0, userId);
				return query.list();
			}
		});
	}

	public SysUser save(SysUser user) {
		this.addObject(user);
		return user;
	}
}
