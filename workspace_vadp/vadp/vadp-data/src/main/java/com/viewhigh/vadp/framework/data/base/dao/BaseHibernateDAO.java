package com.viewhigh.vadp.framework.data.base.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.SQLServerDialect;
import org.hibernate.engine.spi.EntityEntry;
import org.hibernate.event.spi.EventSource;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.spi.ServiceException;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.orm.hibernate4.HibernateCallback;
//import org.springframework.orm.hibernate4.SessionFactoryUtils;
//import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.viewhigh.vadp.framework.data.exception.DataException;
import com.viewhigh.vadp.framework.data.mock.HibernateCallback;
import com.viewhigh.vadp.framework.data.mock.HibernateDaoSupport;
import com.viewhigh.vadp.framework.data.persistence.pagination.Page;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageContext;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageResult;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageUtil;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import com.viewhigh.vadp.framework.data.persistence.pagination.impl.PageResultImpl;
import com.viewhigh.vadp.framework.data.util.StringUtils;

/**
 * BaseDao实现类BaseHiernateDao
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
@SuppressWarnings("all")
public class BaseHibernateDAO extends HibernateDaoSupport implements IBaseDao {

	protected final Logger log = LoggerFactory.getLogger(BaseHibernateDAO.class);

	//	public void setSessionFacotry(SessionFactory sessionFacotry) {
	//		super.setSessionFactory(sessionFacotry);
	//	}
	public <T> T findById(String id, Class clazz) {
		T result = null;
		if (id != null) {
			result = (T) this.getHibernateTemplate().get(clazz, id);
		}
		return result;
	}

	/**
	 * 添加对象
	 * 
	 * @param paramObject
	 *            实体类
	 * @return
	 * @throws ServiceException
	 */
	public Serializable addObject(Object paramObject) throws ServiceException {
		return getHibernateTemplate().save(paramObject);
	}

	/**
	 * 删除对象
	 * 
	 * @param paramObject
	 *            实体类
	 * @throws ServiceException
	 */
	public void removeObject(Object paramObject) throws ServiceException {
		getHibernateTemplate().delete(paramObject);
	}

	/**
	 * 更新对象
	 * 
	 * @param paramObject
	 *            实体类
	 * @throws ServiceException
	 */
	public void updateObject(Object paramObject) throws ServiceException {
		getHibernateTemplate().update(paramObject);
	}

	/**
	 * HQl语句返回一条数据
	 * 
	 * @param paramString
	 * @return
	 * @throws ServiceException
	 */
	public Object queryObject(String paramString) throws ServiceException {
		List localList = getHibernateTemplate().find(paramString);
		Object localObject = localList.isEmpty() ? null : localList.get(0);
		return localObject;
	}

	/**
	 * HQl语句返回一条数据
	 * 
	 * @param paramString
	 *            HQL语句
	 * @param paramObject
	 *            参数
	 * @return
	 * @throws ServiceException
	 */
	public Object queryObject(String paramString, Object paramObject) throws ServiceException {
		List<?> localList = getHibernateTemplate().find(paramString, paramObject);
		Object localObject = localList.isEmpty() ? null : localList.get(0);
		return localObject;
	}

	/**
	 * HQl语句返回一条数据
	 * 
	 * @param paramString
	 *            HQL语句
	 * @param paramObject
	 *            参数列表
	 * @return
	 * @throws ServiceException
	 */
	public Object queryObject(String paramString, Object[] paramArrayOfObject) throws ServiceException {
		List localList = getHibernateTemplate().find(paramString, paramArrayOfObject);
		Object localObject = localList.isEmpty() ? null : localList.get(0);
		return localObject;
	}

	/**
	 * HQL语句 返回查询列表
	 * @param paramString
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(String paramString) throws ServiceException {
		List localList = getHibernateTemplate().find(paramString);
		return localList;
	}

	/**
	 * HQL语句 返回查询列表
	 * @param paramString HQL语句
	 * @param paramObject 参数
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(String paramString, Object paramObject) throws ServiceException {
		List localList = getHibernateTemplate().find(paramString, paramObject);
		return localList;
	}

	/**
	 * HQL语句 返回查询列表
	 * @param paramString HQL语句
	 * @param paramArrayOfObject 参数列表
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(String paramString, Object[] paramArrayOfObject) throws ServiceException {
		List localList = getHibernateTemplate().find(paramString, paramArrayOfObject);
		return localList;
	}

	/**
	 * HQL语句 返回查询列表  -- （分页）
	 * @param paramString HQL语句
	 * @param paramArrayOfObject 参数列表
	 * @param paramInt1 FirstResult
	 * @param paramInt2 MaxResults
	 * @return
	 * @throws ServiceException
	 */
	public List queryObjects(final String paramString, final Object[] paramArrayOfObject, final int paramInt1, final int paramInt2) throws ServiceException {
		List localList = (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session paramAnonymousSession) {
				try {
					Query localQuery = paramAnonymousSession.createQuery(paramString);
					if (paramArrayOfObject != null) {
						int i = 0;
						for (int j = paramArrayOfObject.length; i < j; i++) {
							localQuery.setParameter(i, paramArrayOfObject[i]);
						}
					}
					if (paramInt2 > 0) {
						localQuery.setMaxResults(paramInt2);
						localQuery.setFirstResult((paramInt1 - 1) * paramInt2);
					}
					return localQuery.list();
				} catch (Exception localException) {
					Object localObject = localException;
					if (localException.getCause() != null) {
						localObject = localException.getCause();
					}
					throw new DataException("查询列表错误", (Throwable) localObject);
				}
			}
		});
		return localList;
	}

	/**
	 * HQL语句 返回查询列表 分页查询
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 * @throws ServiceException
	 */
	public QueryResult queryObjectsByPage(String paramString, Object[] paramArrayOfObject) throws ServiceException {
		boolean bool = false;
		QueryResult localQueryResult = PageUtil.getQueryResult();
		int i = 100;
		int j = 1;
		if (localQueryResult != null) {
			i = localQueryResult.getPageSize();
			j = localQueryResult.getPageNumber();
			bool = localQueryResult.isAutoCalcCount();
		}
		localQueryResult = new QueryResult();
		localQueryResult.setPageNumber(j);
		localQueryResult.setPageSize(i);
		int k = i;
		int m = j;
		List localList = queryObjects(paramString, paramArrayOfObject, m, k);
		localQueryResult.setResult(localList);
		if (bool) {
			if ((m == 1) && ((localList == null) || (localList.size() < k))) {
				localQueryResult.setRecordCount(localList == null ? 0 : localList.size());
			} else {
				int n = getTotalCount(paramString, paramArrayOfObject);
				localQueryResult.setRecordCount(n);
			}
		}
		SessionFactory localSessionFactory = getSessionFactory();
		PageContext localPageContext = PageUtil.initPageContext(getClass(), "HQL", paramString, paramArrayOfObject, null, null, localSessionFactory);
		localQueryResult.setPageContext(localPageContext);
		return localQueryResult;
	}

	/**
	 * HQL 返回查询总数
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public int getTotalCount(String paramString, final Object[] paramArrayOfObject) {
		String str1 = paramString.toLowerCase();
		int i = str1.indexOf("from");
		Dialect localDialect = ((SessionFactoryImpl) getSessionFactory()).getDialect();
		if ((localDialect instanceof SQLServerDialect)) {
			int j = str1.indexOf("order ");
			if (j > 0) {
				paramString = paramString.substring(0, j);
			}
		}
		final StringBuffer localStringBuffer = new StringBuffer();
		int k = str1.indexOf("select");
		if ((k >= 0) && (k < i)) {
			str1 = paramString.substring(k, i);
			int m = str1.indexOf("distinct");
			if (m >= 0) {
				str1 = str1.replaceAll("\\(", "").replaceAll("\\)", "");
			}
			localStringBuffer.append("select count(").append(str1.substring(k + 6, str1.length())).append(") ");
		} else {
			localStringBuffer.append("select count(*) ");
		}
		final String str2 = paramString.substring(i).replaceAll("(?i)fetch", "");

		List localList = (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session paramAnonymousSession) {
				Query localQuery = paramAnonymousSession.createQuery(localStringBuffer.append(str2).toString());
				if ((str2.indexOf("?") > 0) && (paramArrayOfObject != null)) {
					for (int i = 0; i < paramArrayOfObject.length; i++) {
						localQuery.setParameter(i, paramArrayOfObject[i]);
					}
				}
				return localQuery.list();
			}
		});
		return ((Long) localList.get(0)).intValue();
	}

	/**
	 * HQL 返回查询总数
	 * @param paramString HQL
	 * @param paramPage
	 * @return
	 */
	private long getTotalCount(String paramString, final Page paramPage) {
		String str1 = paramString.toLowerCase();
		int i = str1.indexOf("from");
		int j = str1.indexOf("order ");
		if (j > 0) {
			paramString = paramString.substring(0, j);
		}
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer.append("select count(*) ");
		final String str2 = paramString.substring(i);
		List localList = (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session paramAnonymousSession) {
				Query localQuery = paramAnonymousSession.createQuery(str2);
				if (str2.indexOf("?") > 0) {
					List localList = paramPage.getConditionValues();
					if ((localList == null) || (localList.isEmpty())) {
						throw new DataException("构造查询总数条件发生错误！！！");
					}
					for (int i = 0; i < localList.size(); i++) {
						localQuery.setParameter(i, localList.get(i));
					}
				}
				return localQuery.list();
			}
		});
		return ((Long) localList.get(0)).longValue();
	}

	/**
	 * 查询分页
	 * @param paramString
	 * @param paramPage
	 * @return
	 */
	public PageResult getPageResult(String paramString, Page paramPage) {
		if ((paramString == null) || ("".equals(paramString.trim()))) {
			throw new IllegalArgumentException("inPut queryHQL is null");
		}
		if (paramPage == null) {
			throw new IllegalArgumentException("inPut Page is null");
		}
		PageResultImpl localPageResultImpl = new PageResultImpl();
		int i = paramPage.getSize();
		long l1 = getTotalCount(paramString, paramPage);
		localPageResultImpl.setTotalCount(l1);
		long l2 = l1 / i;
		if (l1 % i > 0L) {
			l2 += 1L;
		}
		localPageResultImpl.setTotalPage(l2);
		List localList = getResult(paramString, paramPage);
		localPageResultImpl.setResultSet(localList);
		localPageResultImpl.setPage(paramPage);
		return localPageResultImpl;
	}

	private List getResult(final String paramString, final Page paramPage) {
		List localList = (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session paramAnonymousSession) {
				try {
					Query localQuery = paramAnonymousSession.createQuery(paramString);
					if (paramString.indexOf("?") > 0) {
						List localList = paramPage.getConditionValues();
						if ((localList == null) || (localList.isEmpty())) {
							throw new DataException("构造查询条件发生错误！！！");
						}
						for (int j = 0; j < localList.size(); j++) {
							localQuery.setParameter(j, localList.get(j));
						}
					}
					int i = paramPage.getSize();
					int j = paramPage.getPageNumber();
					if (i > 0) {
						localQuery.setMaxResults(i);
						localQuery.setFirstResult((j - 1) * i);
					}
					return localQuery.list();
				} catch (Exception localException) {
					Object localObject = localException;
					if (localException.getCause() != null) {
						localObject = localException.getCause();
					}
					throw new DataException("查询语句执行错误" + paramString, (Throwable) localObject);
				}
			}
		});
		return localList;
	}

	/**
	 * sql 语句更新
	 * @param paramString
	 * @return
	 */
	public int update(String paramString) {
		return update(paramString, null);
	}

	/**
	 * sql语句更新
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public int update(final String paramString, final Object[] paramArrayOfObject) {
		int i = ((Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session paramAnonymousSession) {
				try {
					SQLQuery localSQLQuery = paramAnonymousSession.createSQLQuery(paramString);
					if (paramArrayOfObject != null) {
						for (int i = 0; i < paramArrayOfObject.length; i++) {
							localSQLQuery = (SQLQuery) localSQLQuery.setParameter(i, paramArrayOfObject[i]);
						}
					}
					return Integer.valueOf(localSQLQuery.executeUpdate());
				} catch (Exception localException) {
					Object localObject = localException;
					if (localException.getCause() != null) {
						localObject = localException.getCause();
					}
					throw new DataException("修改语句执行错误", (Throwable) localObject);
				}
			}
		})).intValue();
		return i;
	}

	/**
	 * 批量更新
	 * @param paramList
	 */
	public void addOrUpdateObjects(List paramList) {
		addOrUpdateObjects(paramList, 100);
	}

	/**
	 * 批量更新
	 * @param paramList
	 * @param paramInt
	 */
	public void addOrUpdateObjects(List paramList, int paramInt) {
		if ((paramList == null) || (paramList.size() == 0) || (paramInt == 0)) {
			return;
		}
		int i = paramList.size();
		EntityEntry localEntityEntry = null;
		for (int j = 0; j < i; j++) {
			localEntityEntry = ((EventSource) getSession()).getPersistenceContext().getEntry(paramList.get(j));
			getHibernateTemplate().saveOrUpdate(paramList.get(j));
			if (j % paramInt == 0)
				getSession().flush();
		}
	}

	/**
	 * SQL语句查询分页
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public QueryResult query(String paramString, Object[] paramArrayOfObject) {
		QueryResult localQueryResult = query(paramString, paramArrayOfObject, null);
		SessionFactory localSessionFactory = getSessionFactory();
		PageContext localPageContext = PageUtil.initPageContext(getClass(), "SQL", paramString, paramArrayOfObject, null, null, localSessionFactory);
		localQueryResult.setPageContext(localPageContext);
		return localQueryResult;
	}

	/**
	 * SQL语句查询分页
	 * @param paramString
	 * @param paramArrayOfObject
	 * @return
	 */
	public QueryResult query(final String paramString1, final Object[] paramArrayOfObject, final String paramString2) {
		boolean bool = false;
		QueryResult localQueryResult = PageUtil.getQueryResult();
		int i = 100;
		int j = 1;
		if (localQueryResult != null) {
			i = localQueryResult.getPageSize();
			j = localQueryResult.getPageNumber();
			bool = localQueryResult.isAutoCalcCount();
		}
		localQueryResult = new QueryResult();
		localQueryResult.setPageNumber(j);
		localQueryResult.setPageSize(i);
		final int k = i;
		final int m = j;
		List localList = (List) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) {
				SQLQuery sqlQuery = session.createSQLQuery(paramString1);
				sqlQuery.setResultTransformer(new ResultTransformer() {
					public Object transformTuple(Object[] tuple, String[] aliases) {
						Map<String, Object> maps = new HashMap<String, Object>();
						int i = 0;
						for (String col : aliases) {
							maps.put(StringUtils.camelName(col), tuple[i++]);
						}
						return maps;
					}

					public List transformList(List collection) {
						return collection;
					}
				});
				if (paramArrayOfObject != null) {
					int i = 0;
					for (int j = paramArrayOfObject.length; i < j; i++) {
						sqlQuery.setParameter(i, paramArrayOfObject[i]);
					}
				}
				sqlQuery.setMaxResults(k);
				sqlQuery.setFirstResult((m - 1) * k);
				return sqlQuery.list();
			}
		});
		localQueryResult.setResult(localList);
		SessionFactory localSessionFactory = getSessionFactory();
		PageContext localPageContext = PageUtil.initPageContext(getClass(), "SQL", paramString1, paramArrayOfObject, paramString2, null, localSessionFactory);
		localQueryResult.setPageContext(localPageContext);
		if (bool) {
			if ((m == 1) && ((localList == null) || (localList.size() < k)))
				localQueryResult.setRecordCount(localList == null ? 0 : localList.size());
			else {
				localQueryResult.setRecordCount(getRecordCount(paramString1, paramArrayOfObject));
			}
		}
		return localQueryResult;
	}

	public int getRecordCount(String paramString, final Object[] paramArrayOfObject) {
		String str1 = paramString.toLowerCase();
		Dialect localDialect = ((SessionFactoryImpl) getSessionFactory()).getDialect();
		if ((localDialect instanceof SQLServerDialect)) {
			int i = str1.indexOf("order ");
			if (i > 0) {
				paramString = paramString.substring(0, i);
			}
		}

		final String str2 = "select count(1) from (" + paramString + ") t";

		int j = ((Number) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session paramAnonymousSession) {
				try {
					SQLQuery localSQLQuery = paramAnonymousSession.createSQLQuery(str2);
					if (paramArrayOfObject != null) {
						for (int i = 0; i < paramArrayOfObject.length; i++) {
							localSQLQuery = (SQLQuery) localSQLQuery.setParameter(i, paramArrayOfObject[i]);
						}
					}
					return localSQLQuery.uniqueResult();
				} catch (Exception localException) {
					Object localObject = localException;
					if (localException.getCause() != null) {
						localObject = localException.getCause();
					}
					throw new DataException("取得记录数语句执行错误！！！", (Throwable) localObject);
				}
			}
		})).intValue();
		return j;
	}

	public Session getSession() {
		return getCurrentSession();
	}

	public Session getCurrentSession() {
		return super.currentSession();
	}

	/**
	 * 为portaljpa迁移添加的新方法
	 */
	//	@Override
	//	public <T> T save(Object entity)
	//	{
	//		this.addObject(entity);
	//		return (T) entity;
	//	}
	/**
	 * HQL语句 返回查询列表 分页查询
	 * @param paramString
	 * @param paramArrayOfObject
	 * @param pageSize
	 * @param pageNum
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public QueryResult queryObjectsByPage(String paramString, Object[] paramArrayOfObject, int pageSize, int pageNum) throws ServiceException {
		boolean bool = false;
		int i = 100;
		int j = 1;
		i = pageSize;
		j = pageNum;

		QueryResult localQueryResult = new QueryResult();
		localQueryResult.setPageNumber(j);
		localQueryResult.setPageSize(i);
		int k = i;
		int m = j;
		List localList = queryObjects(paramString, paramArrayOfObject, m, k);
		localQueryResult.setResult(localList);
		if (bool) {
			if ((m == 1) && ((localList == null) || (localList.size() < k))) {
				localQueryResult.setRecordCount(localList == null ? 0 : localList.size());
			} else {
				int n = getTotalCount(paramString, paramArrayOfObject);
				localQueryResult.setRecordCount(n);
			}
		}
		SessionFactory localSessionFactory = getSessionFactory();
		PageContext localPageContext = PageUtil.initPageContext(getClass(), "HQL", paramString, paramArrayOfObject, null, null, localSessionFactory);
		localQueryResult.setPageContext(localPageContext);
		return localQueryResult;
	}
	/***************************************************新方法***************************************/

}
