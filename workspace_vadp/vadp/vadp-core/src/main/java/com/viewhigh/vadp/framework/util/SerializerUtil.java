package com.viewhigh.vadp.framework.util;

import com.viewhigh.vadp.framework.serializer.ObjectSerializable;
/**
 * 
 * 提供序列化助手类
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年6月7日
 * 修改日期: 2017年6月7日
 */
public class SerializerUtil {
	public static Object unserial(Object value) {
		if (value == null)
			return null;
		return ((ObjectSerializable) SpringContextUtil.getBean("serializer")).unserial(value);
	}

	public static Object serial(Object object) {
		if (object != null) {
			try {
				object = ((ObjectSerializable) SpringContextUtil.getBean("serializer")).serial(object);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		return object;
	}
}
