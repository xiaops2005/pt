package com.viewhigh.vadp.framework.data.mock;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.viewhigh.vadp.framework.data.cons.DataConfConst;
import com.viewhigh.vadp.framework.data.trans.DynamicDataSourceSetting;

/**
 * 持久層基础类
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
/**
 * 持久層基础类
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class HibernateDaoSupport {
	private static Map<String, HibernateTemplate> HT_MAP = new HashMap<String, HibernateTemplate>();

	//	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		HibernateTemplate ht = HT_MAP.get(DynamicDataSourceSetting.getDataSourceKey());
		if (ht == null) {
			//从当前线程中取出对应的sessionFactory然后创建hibernateTemplate
			ht = createHibernateTemplate(DataConfConst.SESSION_FACTORY_MAP.get(DynamicDataSourceSetting
					.getDataSourceKey()));
		}
		return ht;
	}

	//	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	//		this.hibernateTemplate = hibernateTemplate;
	//	}

	//	public final void setSessionFactory(SessionFactory sessionFactory) {
	//		if (getHibernateTemplate() == null || sessionFactory != getHibernateTemplate().getSessionFactory()) {
	//			this.hibernateTemplate = createHibernateTemplate(sessionFactory);
	//		}
	//	}

	public final SessionFactory getSessionFactory() {
		HibernateTemplate ht = HT_MAP.get(DynamicDataSourceSetting.getDataSourceKey());
		if (ht == null) {
			//从当前线程中取出对应的sessionFactory然后创建hibernateTemplate
			ht = createHibernateTemplate(DataConfConst.SESSION_FACTORY_MAP.get(DynamicDataSourceSetting
					.getDataSourceKey()));
		}
		return ht.getSessionFactory();
	}

	public final HibernateTemplate createHibernateTemplate(SessionFactory sessionFactory) {
		synchronized (HT_MAP) {
			if (HT_MAP.get(DynamicDataSourceSetting.getDataSourceKey()) == null) {
				HT_MAP.put(DynamicDataSourceSetting.getDataSourceKey(), new HibernateTemplate(sessionFactory));
			}
		}
		return HT_MAP.get(DynamicDataSourceSetting.getDataSourceKey());
	}

	/**
	 * 返回当前的hibernate的session
	 * @return
	 */
	protected final Session currentSession() {
		return getSessionFactory().getCurrentSession();
	}
}
