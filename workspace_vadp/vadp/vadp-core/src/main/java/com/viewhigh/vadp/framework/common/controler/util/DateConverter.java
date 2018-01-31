package com.viewhigh.vadp.framework.common.controler.util;

import java.text.ParseException;

import org.apache.commons.beanutils.Converter;
/**
 * 
 * 时间类型转换器
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
@SuppressWarnings("all")
public class DateConverter implements Converter {

	/**
	 * 转换时间
	 */
	@Override
	public Object convert(Class type, Object value) {
		if (value == null)
			return null;
		if (!(value instanceof String))
			return value;
		try {
			return DateKit.parseDate(String.valueOf(value));
		} catch (ParseException e) {
			 throw new RuntimeException(e);
		}
	}

}
