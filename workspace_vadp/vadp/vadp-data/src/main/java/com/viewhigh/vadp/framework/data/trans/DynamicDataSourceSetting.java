package com.viewhigh.vadp.framework.data.trans;

import org.apache.commons.lang.StringUtils;

/**
 * 动态切换数据源
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class DynamicDataSourceSetting {
	private static final ThreadLocal<String> dataSourceKeyData = new ThreadLocal<String>();

	public static String getDataSourceKey() {
		String dsKey = dataSourceKeyData.get();
		dsKey = dsKey == null ? "default" : dsKey;
		return dsKey;
	}

	public static void setDataSourceKey(String key) {
		if (StringUtils.isBlank(key)) {
			return;
		}
		dataSourceKeyData.set(key);
	}

}
