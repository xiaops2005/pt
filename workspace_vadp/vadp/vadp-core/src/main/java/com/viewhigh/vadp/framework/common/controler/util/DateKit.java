package com.viewhigh.vadp.framework.common.controler.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;
/**
 * 
 * 日期工具类
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class DateKit extends DateUtils {
	
	public static String[] patten = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd'T'HH:mm:ss.SSS Z", "dd/MMM/yyyy:HH:mm:ss" ,"EEE MMM dd HH:mm:ss Z yyyy"};
	
	/**
	 * 
	 * @Title: parseDate   
	 * @Description: string转换date 
	 * @param: @param value
	 * @param: @return
	 * @param: @throws ParseException      
	 * @return: Date      
	 * @throws
	 */
	public static Date parseDate(String value) throws ParseException {
		Date date = null;
		if(StringUtils.isBlank(value)){
			return null;
		}
		if (NumberUtils.isNumber(value)) {
			date = new Date(NumberUtils.toLong(value));
			return date;
		}
		date = parseDate(value, patten);
		return date;
	}
	
}
