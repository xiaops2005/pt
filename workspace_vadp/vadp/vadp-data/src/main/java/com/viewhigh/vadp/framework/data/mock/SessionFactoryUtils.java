/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.viewhigh.vadp.framework.data.mock;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.service.spi.Wrapped;

/**
 * Helper class featuring methods for Hibernate Session handling.
 * Also provides support for exception translation.
 *
 * <p>Used internally by {@link HibernateTransactionManager}.
 * Can also be used directly in application code.
 *
 * @author Juergen Hoeller
 * @since 3.1
 * @see HibernateExceptionTranslator
 * @see HibernateTransactionManager
 */
public abstract class SessionFactoryUtils {

	/**
	 * Order value for TransactionSynchronization objects that clean up Hibernate Sessions.
	 * Returns {@code DataSourceUtils.CONNECTION_SYNCHRONIZATION_ORDER - 100}
	 * to execute Session cleanup before JDBC Connection cleanup, if any.
	 * @see org.springframework.jdbc.datasource.DataSourceUtils#CONNECTION_SYNCHRONIZATION_ORDER
	 */
	public static final int SESSION_SYNCHRONIZATION_ORDER =1000 - 100;

	static final Logger logger = LoggerFactory.getLogger(SessionFactoryUtils.class);

	/**
	 * Bridging between the different ConnectionProvider package location in 4.0-4.2 vs 4.3.
	 */
	private static final Method getConnectionProviderMethod =getMethodIfAvailable(SessionFactoryImplementor.class, "getConnectionProvider");
	
	private static Method getMethodIfAvailable(Class<?> clazz, String methodName, Class<?>... paramTypes) {
		Assert.notNull(clazz, "Class must not be null");
		Assert.notNull(methodName, "Method name must not be null");
		if (paramTypes != null) {
			try {
				return clazz.getMethod(methodName, paramTypes);
			}
			catch (NoSuchMethodException ex) {
				return null;
			}
		}
		else {
			Set<Method> candidates = new HashSet<Method>(1);
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (methodName.equals(method.getName())) {
					candidates.add(method);
				}
			}
			if (candidates.size() == 1) {
				return candidates.iterator().next();
			}
			return null;
		}
	}

	private static DataSource getDataSource(SessionFactory sessionFactory) {
		if (getConnectionProviderMethod != null && sessionFactory instanceof SessionFactoryImplementor) {
			Wrapped cp = (Wrapped) invokeMethod(getConnectionProviderMethod, sessionFactory);
			if (cp != null) {
				return cp.unwrap(DataSource.class);
			}
		}
		return null;
	}
	private static Object invokeMethod(Method method, Object target) {
		return invokeMethod(method, target, new Object[0]);
	}

	private static Object invokeMethod(Method method, Object target, Object... args) {
		try {
			return method.invoke(target, args);
		}
		catch (Exception ex) {
			throw new DataAccessException(ex);
		}
	}
	/**
	 * Perform actual closing of the Hibernate Session,
	 * catching and logging any cleanup exceptions thrown.
	 * @param session the Hibernate Session to close (may be {@code null})
	 * @see org.hibernate.Session#close()
	 */
	public static void closeSession(Session session) {
		if (session != null) {
			try {
				session.close();
			}
			catch (HibernateException ex) {
				logger.debug("Could not close Hibernate Session", ex);
			}
			catch (Throwable ex) {
				logger.debug("Unexpected exception on closing Hibernate Session", ex);
			}
		}
	}

	/**
	 * Convert the given HibernateException to an appropriate exception
	 * from the {@code org.springframework.dao} hierarchy.
	 * @param ex HibernateException that occurred
	 * @return the corresponding DataAccessException instance
	 * @see HibernateExceptionTranslator#convertHibernateAccessException
	 * @see HibernateTransactionManager#convertHibernateAccessException
	 */
	public static DataAccessException convertHibernateAccessException(HibernateException ex) {
		// fallback
		return new DataAccessException(ex);
	}

}
