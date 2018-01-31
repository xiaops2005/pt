package com.viewhigh.vadp.framework.data.mock;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
/**
 * 基于hibernate操作的模板实现
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class HibernateTemplate extends HibernateAccessor{
	private SessionFactory sessionFactory;
	
	public HibernateTemplate() {
	}
	
	public HibernateTemplate(SessionFactory sessionFactory) {
		setSessionFactory(sessionFactory);
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    /******************************具体操作方法********************************************/ 
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private boolean exposeNativeSession = false;

	private boolean checkWriteOperations = true;

	private boolean cacheQueries = false;

	private String queryCacheRegion;

	private int fetchSize = 0;

	private int maxResults = 0;

	
	public <T> T execute(HibernateCallback<T> action) throws DataAccessException {
		return doExecute(action, false);
	}

	/**
	 * Execute the action specified by the given action object within a
	 * native {@link org.hibernate.Session}.
	 * <p>This execute variant overrides the template-wide
	 * {@link #isExposeNativeSession() "exposeNativeSession"} setting.
	 * @param action callback object that specifies the Hibernate action
	 * @return a result object returned by the action, or {@code null}
	 * @throws org.springframework.dao.DataAccessException in case of Hibernate errors
	 */
	public <T> T executeWithNativeSession(HibernateCallback<T> action) {
		return doExecute(action, true);
	}

	/**
	 * Execute the action specified by the given action object within a Session.
	 * @param action callback object that specifies the Hibernate action
	 * @param enforceNativeSession whether to enforce exposure of the native
	 * Hibernate Session to callback code
	 * @return a result object returned by the action, or {@code null}
	 * @throws org.springframework.dao.DataAccessException in case of Hibernate errors
	 */
	protected <T> T doExecute(HibernateCallback<T> action, boolean enforceNativeSession) throws DataAccessException {
		Assert.notNull(action, "Callback object must not be null");

		Session session = null;
		boolean isNew = false;
		try {
			session = getSessionFactory().getCurrentSession();
		}
		catch (HibernateException ex) {
			logger.debug("Could not retrieve pre-bound Hibernate session", ex);
		}
		if (session == null) {
			session = getSessionFactory().openSession();
			session.setFlushMode(FlushMode.MANUAL);
			isNew = true;
		}

		try {
			enableFilters(session);
			Session sessionToExpose =
					(enforceNativeSession || isExposeNativeSession() ? session : createSessionProxy(session));
			return action.doInHibernate(sessionToExpose);
		}
		catch (HibernateException ex) {
			throw SessionFactoryUtils.convertHibernateAccessException(ex);
		}
		catch (RuntimeException ex) {
			// Callback code threw application exception...
			throw ex;
		}
		finally {
			if (isNew) {
				SessionFactoryUtils.closeSession(session);
			}
			else {
				disableFilters(session);
			}
		}
	}
	
	protected Session createSessionProxy(Session session) {
		return (Session) Proxy.newProxyInstance(
				session.getClass().getClassLoader(), new Class<?>[] {Session.class},
				new CloseSuppressingInvocationHandler(session));
	}
	//-------------------------------------------------------------------------
	// Convenience methods for loading individual objects
	//-------------------------------------------------------------------------

	
	public <T> T get(Class<T> entityClass, Serializable id) throws DataAccessException {
		return get(entityClass, id, null);
	}

	
	public <T> T get(final Class<T> entityClass, final Serializable id, final LockMode lockMode)
			throws DataAccessException {

		return executeWithNativeSession(new HibernateCallback<T>() {
			
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException {
				if (lockMode != null) {
					return (T) session.get(entityClass, id, new LockOptions(lockMode));
				}
				else {
					return (T) session.get(entityClass, id);
				}
			}
		});
	}

	
	public Object get(String entityName, Serializable id) throws DataAccessException {
		return get(entityName, id, null);
	}

	
	public Object get(final String entityName, final Serializable id, final LockMode lockMode)
			throws DataAccessException {

		return executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				if (lockMode != null) {
					return session.get(entityName, id, new LockOptions(lockMode));
				}
				else {
					return session.get(entityName, id);
				}
			}
		});
	}

	
	public <T> T load(Class<T> entityClass, Serializable id) throws DataAccessException {
		return load(entityClass, id, null);
	}

	
	public <T> T load(final Class<T> entityClass, final Serializable id, final LockMode lockMode)
			throws DataAccessException {

		return executeWithNativeSession(new HibernateCallback<T>() {
			
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException {
				if (lockMode != null) {
					return (T) session.load(entityClass, id, new LockOptions(lockMode));
				}
				else {
					return (T) session.load(entityClass, id);
				}
			}
		});
	}

	
	public Object load(String entityName, Serializable id) throws DataAccessException {
		return load(entityName, id, null);
	}

	
	public Object load(final String entityName, final Serializable id, final LockMode lockMode)
			throws DataAccessException {

		return executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				if (lockMode != null) {
					return session.load(entityName, id, new LockOptions(lockMode));
				}
				else {
					return session.load(entityName, id);
				}
			}
		});
	}

	
	public <T> List<T> loadAll(final Class<T> entityClass) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<List<T>>() {
			
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria criteria = session.createCriteria(entityClass);
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				prepareCriteria(criteria);
				return criteria.list();
			}
		});
	}

	
	public void load(final Object entity, final Serializable id) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				session.load(entity, id);
				return null;
			}
		});
	}

	
	public void refresh(final Object entity) throws DataAccessException {
		refresh(entity, null);
	}

	
	public void refresh(final Object entity, final LockMode lockMode) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				if (lockMode != null) {
					session.refresh(entity, new LockOptions(lockMode));
				}
				else {
					session.refresh(entity);
				}
				return null;
			}
		});
	}

	
	public boolean contains(final Object entity) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<Boolean>() {
			
			public Boolean doInHibernate(Session session) {
				return session.contains(entity);
			}
		});
	}

	
	public void evict(final Object entity) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				session.evict(entity);
				return null;
			}
		});
	}
	//-------------------------------------------------------------------------
	// Convenience methods for storing individual objects
	//-------------------------------------------------------------------------
	
	public Serializable save(final Object entity) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<Serializable>() {
			
			public Serializable doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				return session.save(entity);
			}
		});
	}

	
	public Serializable save(final String entityName, final Object entity) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<Serializable>() {
			
			public Serializable doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				return session.save(entityName, entity);
			}
		});
	}

	
	public void update(Object entity) throws DataAccessException {
		update(entity, null);
	}

	
	public void update(final Object entity, final LockMode lockMode) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.update(entity);
				if (lockMode != null) {
					session.buildLockRequest(new LockOptions(lockMode)).lock(entity);
				}
				return null;
			}
		});
	}

	
	public void update(String entityName, Object entity) throws DataAccessException {
		update(entityName, entity, null);
	}

	
	public void update(final String entityName, final Object entity, final LockMode lockMode)
			throws DataAccessException {

		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.update(entityName, entity);
				if (lockMode != null) {
					session.buildLockRequest(new LockOptions(lockMode)).lock(entityName, entity);
				}
				return null;
			}
		});
	}

	
	public void saveOrUpdate(final Object entity) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.saveOrUpdate(entity);
				return null;
			}
		});
	}

	
	public void saveOrUpdate(final String entityName, final Object entity) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.saveOrUpdate(entityName, entity);
				return null;
			}
		});
	}

	
	public void replicate(final Object entity, final ReplicationMode replicationMode)
			throws DataAccessException {

		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.replicate(entity, replicationMode);
				return null;
			}
		});
	}

	
	public void replicate(final String entityName, final Object entity, final ReplicationMode replicationMode)
			throws DataAccessException {

		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.replicate(entityName, entity, replicationMode);
				return null;
			}
		});
	}

	
	public void persist(final Object entity) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.persist(entity);
				return null;
			}
		});
	}

	
	public void persist(final String entityName, final Object entity) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				session.persist(entityName, entity);
				return null;
			}
		});
	}

	
	public <T> T merge(final T entity) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<T>() {
			
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				return (T) session.merge(entity);
			}
		});
	}

	
	public <T> T merge(final String entityName, final T entity) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<T>() {
			
			@SuppressWarnings("unchecked")
			public T doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				return (T) session.merge(entityName, entity);
			}
		});
	}

	
	public void delete(Object entity) throws DataAccessException {
		delete(entity, null);
	}

	
	public void delete(final Object entity, final LockMode lockMode) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				if (lockMode != null) {
					session.buildLockRequest(new LockOptions(lockMode)).lock(entity);
				}
				session.delete(entity);
				return null;
			}
		});
	}

	
	public void delete(String entityName, Object entity) throws DataAccessException {
		delete(entityName, entity, null);
	}

	
	public void delete(final String entityName, final Object entity, final LockMode lockMode)
			throws DataAccessException {

		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				if (lockMode != null) {
					session.buildLockRequest(new LockOptions(lockMode)).lock(entityName, entity);
				}
				session.delete(entityName, entity);
				return null;
			}
		});
	}

	
	public void deleteAll(final Collection<?> entities) throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				checkWriteOperationAllowed(session);
				for (Object entity : entities) {
					session.delete(entity);
				}
				return null;
			}
		});
	}

	
	public void flush() throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) throws HibernateException {
				session.flush();
				return null;
			}
		});
	}

	
	public void clear() throws DataAccessException {
		executeWithNativeSession(new HibernateCallback<Object>() {
			
			public Object doInHibernate(Session session) {
				session.clear();
				return null;
			}
		});
	}


	//-------------------------------------------------------------------------
	// Convenience finder methods for HQL strings
	//-------------------------------------------------------------------------

	
	public List<?> find(final String queryString, final Object... values) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<List<?>>() {
			
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	
	public List<?> findByNamedParam(String queryString, String paramName, Object value)
			throws DataAccessException {

		return findByNamedParam(queryString, new String[] {paramName}, new Object[] {value});
	}

	
	public List<?> findByNamedParam(final String queryString, final String[] paramNames, final Object[] values)
			throws DataAccessException {

		if (paramNames.length != values.length) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		return executeWithNativeSession(new HibernateCallback<List<?>>() {
			
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	
	public List<?> findByValueBean(final String queryString, final Object valueBean)
			throws DataAccessException {

		return executeWithNativeSession(new HibernateCallback<List<?>>() {
			
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				queryObject.setProperties(valueBean);
				return queryObject.list();
			}
		});
	}


	//-------------------------------------------------------------------------
	// Convenience finder methods for named queries
	//-------------------------------------------------------------------------

	
	public List<?> findByNamedQuery(final String queryName, final Object... values) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<List<?>>() {
			
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.getNamedQuery(queryName);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	
	public List<?> findByNamedQueryAndNamedParam(String queryName, String paramName, Object value)
			throws DataAccessException {

		return findByNamedQueryAndNamedParam(queryName, new String[] {paramName}, new Object[] {value});
	}

	
	public List<?> findByNamedQueryAndNamedParam(
			final String queryName, final String[] paramNames, final Object[] values)
			throws DataAccessException {

		if (values != null && (paramNames == null || paramNames.length != values.length)) {
			throw new IllegalArgumentException("Length of paramNames array must match length of values array");
		}
		return executeWithNativeSession(new HibernateCallback<List<?>>() {
			
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.getNamedQuery(queryName);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						applyNamedParameterToQuery(queryObject, paramNames[i], values[i]);
					}
				}
				return queryObject.list();
			}
		});
	}

	
	public List<?> findByNamedQueryAndValueBean(final String queryName, final Object valueBean)
			throws DataAccessException {

		return executeWithNativeSession(new HibernateCallback<List<?>>() {
			
			public List<?> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.getNamedQuery(queryName);
				prepareQuery(queryObject);
				queryObject.setProperties(valueBean);
				return queryObject.list();
			}
		});
	}


	//-------------------------------------------------------------------------
	// Convenience finder methods for detached criteria
	//-------------------------------------------------------------------------

	
	public List<?> findByCriteria(DetachedCriteria criteria) throws DataAccessException {
		return findByCriteria(criteria, -1, -1);
	}

	
	public List<?> findByCriteria(final DetachedCriteria criteria, final int firstResult, final int maxResults)
			throws DataAccessException {

		Assert.notNull(criteria, "DetachedCriteria must not be null");
		return executeWithNativeSession(new HibernateCallback<List<?>>() {
			
			public List<?> doInHibernate(Session session) throws HibernateException {
				Criteria executableCriteria = criteria.getExecutableCriteria(session);
				prepareCriteria(executableCriteria);
				if (firstResult >= 0) {
					executableCriteria.setFirstResult(firstResult);
				}
				if (maxResults > 0) {
					executableCriteria.setMaxResults(maxResults);
				}
				return executableCriteria.list();
			}
		});
	}

	
	public <T> List<T> findByExample(T exampleEntity) throws DataAccessException {
		return findByExample(null, exampleEntity, -1, -1);
	}

	
	public <T> List<T> findByExample(String entityName, T exampleEntity) throws DataAccessException {
		return findByExample(entityName, exampleEntity, -1, -1);
	}

	
	public <T> List<T> findByExample(T exampleEntity, int firstResult, int maxResults) throws DataAccessException {
		return findByExample(null, exampleEntity, firstResult, maxResults);
	}

	
	public <T> List<T> findByExample(
			final String entityName, final T exampleEntity, final int firstResult, final int maxResults)
			throws DataAccessException {

		Assert.notNull(exampleEntity, "Example entity must not be null");
		return executeWithNativeSession(new HibernateCallback<List<T>>() {
			
			@SuppressWarnings("unchecked")
			public List<T> doInHibernate(Session session) throws HibernateException {
				Criteria executableCriteria = (entityName != null ?
						session.createCriteria(entityName) : session.createCriteria(exampleEntity.getClass()));
				executableCriteria.add(Example.create(exampleEntity));
				prepareCriteria(executableCriteria);
				if (firstResult >= 0) {
					executableCriteria.setFirstResult(firstResult);
				}
				if (maxResults > 0) {
					executableCriteria.setMaxResults(maxResults);
				}
				return executableCriteria.list();
			}
		});
	}


	//-------------------------------------------------------------------------
	// Convenience query methods for iteration and bulk updates/deletes
	//-------------------------------------------------------------------------

	
	public Iterator<?> iterate(final String queryString, final Object... values) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<Iterator<?>>() {
			
			public Iterator<?> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.iterate();
			}
		});
	}

	
	public void closeIterator(Iterator<?> it) throws DataAccessException {
		try {
			Hibernate.close(it);
		}
		catch (HibernateException ex) {
			throw SessionFactoryUtils.convertHibernateAccessException(ex);
		}
	}

	
	public int bulkUpdate(final String queryString, final Object... values) throws DataAccessException {
		return executeWithNativeSession(new HibernateCallback<Integer>() {
			
			public Integer doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(queryString);
				prepareQuery(queryObject);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return queryObject.executeUpdate();
			}
		});
	}
	
	protected void prepareQuery(Query queryObject) {
		if (isCacheQueries()) {
			queryObject.setCacheable(true);
			if (getQueryCacheRegion() != null) {
				queryObject.setCacheRegion(getQueryCacheRegion());
			}
		}
		if (getFetchSize() > 0) {
			queryObject.setFetchSize(getFetchSize());
		}
		if (getMaxResults() > 0) {
			queryObject.setMaxResults(getMaxResults());
		}
		//设置事务的生存时间
//		SessionHolder sessionHolder =
//				(SessionHolder) TransactionSynchronizationManager.getResource(getSessionFactory());
//		if (sessionHolder != null && sessionHolder.hasTimeout()) {
//			queryObject.setTimeout(sessionHolder.getTimeToLiveInSeconds());
//		}
	}

	/**
	 * Prepare the given Criteria object, applying cache settings and/or
	 * a transaction timeout.
	 * @param criteria the Criteria object to prepare
	 * @see #setCacheQueries
	 * @see #setQueryCacheRegion
	 */
	protected void prepareCriteria(Criteria criteria) {
		if (isCacheQueries()) {
			criteria.setCacheable(true);
			if (getQueryCacheRegion() != null) {
				criteria.setCacheRegion(getQueryCacheRegion());
			}
		}
		if (getFetchSize() > 0) {
			criteria.setFetchSize(getFetchSize());
		}
		if (getMaxResults() > 0) {
			criteria.setMaxResults(getMaxResults());
		}

//		SessionHolder sessionHolder =
//				(SessionHolder) TransactionSynchronizationManager.getResource(getSessionFactory());
//		if (sessionHolder != null && sessionHolder.hasTimeout()) {
		//设置事务的生存事件
//			criteria.setTimeout(sessionHolder.getTimeToLiveInSeconds());
//		}
	}
	protected void applyNamedParameterToQuery(Query queryObject, String paramName, Object value)
			throws HibernateException {

		if (value instanceof Collection) {
			queryObject.setParameterList(paramName, (Collection<?>) value);
		}
		else if (value instanceof Object[]) {
			queryObject.setParameterList(paramName, (Object[]) value);
		}
		else {
			queryObject.setParameter(paramName, value);
		}
	}
	protected void checkWriteOperationAllowed(Session session) throws DataAccessException {
		if (isCheckWriteOperations() && session.getFlushMode().lessThan(FlushMode.COMMIT)) {
			throw new DataAccessException(
					"Write operations are not allowed in read-only mode (FlushMode.MANUAL): "+
					"Turn your Session into FlushMode.COMMIT/AUTO or remove 'readOnly' marker from transaction definition.");
		}
	}
	public void setCheckWriteOperations(boolean checkWriteOperations) {
		this.checkWriteOperations = checkWriteOperations;
	}

	
	public boolean isCheckWriteOperations() {
		return this.checkWriteOperations;
	}
	public void setQueryCacheRegion(String queryCacheRegion) {
		this.queryCacheRegion = queryCacheRegion;
	}

	/**
	 * Return the name of the cache region for queries executed by this template.
	 */
	public String getQueryCacheRegion() {
		return this.queryCacheRegion;
	}

	/**
	 * Set the fetch size for this HibernateTemplate. This is important for processing
	 * large result sets: Setting this higher than the default value will increase
	 * processing speed at the cost of memory consumption; setting this lower can
	 * avoid transferring row data that will never be read by the application.
	 * <p>Default is 0, indicating to use the JDBC driver's default.
	 */
	public void setFetchSize(int fetchSize) {
		this.fetchSize = fetchSize;
	}

	/**
	 * Return the fetch size specified for this HibernateTemplate.
	 */
	public int getFetchSize() {
		return this.fetchSize;
	}

	/**
	 * Set the maximum number of rows for this HibernateTemplate. This is important
	 * for processing subsets of large result sets, avoiding to read and hold
	 * the entire result set in the database or in the JDBC driver if we're
	 * never interested in the entire result in the first place (for example,
	 * when performing searches that might return a large number of matches).
	 * <p>Default is 0, indicating to use the JDBC driver's default.
	 */
	public void setMaxResults(int maxResults) {
		this.maxResults = maxResults;
	}

	/**
	 * Return the maximum number of rows specified for this HibernateTemplate.
	 */
	public int getMaxResults() {
		return this.maxResults;
	}
	public void setCacheQueries(boolean cacheQueries) {
		this.cacheQueries = cacheQueries;
	}
	public void setExposeNativeSession(boolean exposeNativeSession) {
		this.exposeNativeSession = exposeNativeSession;
	}

	/**
	 * Return whether to expose the native Hibernate Session to
	 * HibernateCallback code, or rather a Session proxy.
	 */
	public boolean isExposeNativeSession() {
		return this.exposeNativeSession;
	}
	/**
	 * Return whether to cache all queries executed by this template.
	 */
	public boolean isCacheQueries() {
		return this.cacheQueries;
	}
	private class CloseSuppressingInvocationHandler implements InvocationHandler {

		private final Session target;

		public CloseSuppressingInvocationHandler(Session target) {
			this.target = target;
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			// Invocation on Session interface coming in...

			if (method.getName().equals("equals")) {
				// Only consider equal when proxies are identical.
				return (proxy == args[0]);
			}
			else if (method.getName().equals("hashCode")) {
				// Use hashCode of Session proxy.
				return System.identityHashCode(proxy);
			}
			else if (method.getName().equals("close")) {
				// Handle close method: suppress, not valid.
				return null;
			}

			// Invoke method on target Session.
			try {
				Object retVal = method.invoke(this.target, args);

				// If return value is a Query or Criteria, apply transaction timeout.
				// Applies to createQuery, getNamedQuery, createCriteria.
				if (retVal instanceof Query) {
					prepareQuery(((Query) retVal));
				}
				if (retVal instanceof Criteria) {
					prepareCriteria(((Criteria) retVal));
				}

				return retVal;
			}
			catch (InvocationTargetException ex) {
				throw ex.getTargetException();
			}
		}
	}
}
