package com.viewhigh.vadp.framework.web;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.SessionFactoryUtils;

import com.viewhigh.vadp.framework.data.cons.DataConfConst;
import com.viewhigh.vadp.framework.data.trans.DynamicDataSourceSetting;

/**
 * 实现spring的事务集成管理方式
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class DynamicTransactionManagerForSpring extends HibernateTransactionManager {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Override
	public DataSource getDataSource() {
		//			SessionFactoryImplementor  sf=(SessionFactoryImplementor) getSessionFactory();
		return SessionFactoryUtils.getDataSource(getSessionFactory());
	}

	@Override
	public SessionFactory getSessionFactory() {
		String dsName = DynamicDataSourceSetting.getDataSourceKey();
		SessionFactory sessionFactory = DataConfConst.SESSION_FACTORY_MAP.get(dsName);
		return sessionFactory;
	}
}
