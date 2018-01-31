package com.viewhigh.vadp.framework.data.trans;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.viewhigh.vadp.framework.data.cons.DataConfConst;

/**
 * 事务管理器，可以提供事务的开始、提交、回滚的操作
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class TransactionManager {

	private Transaction hibTx;

	public void beginTransaction() {
		String dsName = DynamicDataSourceSetting.getDataSourceKey();
		SessionFactory sessionFactory = DataConfConst.SESSION_FACTORY_MAP.get(dsName);
		this.hibTx = sessionFactory.getCurrentSession().beginTransaction();
	}

	public void comminTransaction() {
		this.hibTx.commit();
	}

	public void rollbackTransaction() {
		this.hibTx.rollback();
	}
}
