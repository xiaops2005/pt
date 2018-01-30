package com.viewhigh.vadp.framework.data.mock;

import org.hibernate.Session;

public interface HibernateCallback<T> {
	T doInHibernate(Session session) throws DataAccessException;
}
