package com.viewhigh.vadp.framework.util;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * UUID生成器
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class UUIDGenerater {

	public static String generateId() {
		UUID uuid = UUID.randomUUID();
		return StringUtils.replace(uuid.toString(), "-", "");
	}

}
