package com.viewhigh.vadp.framework.common.controler.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
/**
 * 
 * 类型工具类
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class TypeKit {

	/**
	 * 判断是否是基本类型
	 * 
	 * @param paramObject
	 * @return
	 */
	public static boolean isPrimitiveType(Object paramObject) {
		if ((paramObject instanceof String)) {
			return true;
		}
		if ((paramObject instanceof Number)) {
			return true;
		}
		if ((paramObject instanceof Character)) {
			return true;
		}
		if ((paramObject instanceof Boolean)) {
			return true;
		}
		if ((paramObject instanceof BigDecimal)) {
			return true;
		}
		if ((paramObject instanceof Date)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是基本类型
	 * 
	 * @param paramObject
	 * @return
	 */
	public static boolean isPrimitiveType(String clsName) {
		if (StringUtils.equalsIgnoreCase(clsName, String.class.getName())
				||  StringUtils.equalsIgnoreCase(clsName, String.class.getSimpleName())) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Integer.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Integer.class.getSimpleName()) ) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Float.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Float.class.getSimpleName()) ) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Long.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Long.class.getSimpleName())) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Double.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Double.class.getSimpleName())) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Boolean.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Boolean.class.getSimpleName())) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Character.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Character.class.getSimpleName())) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, BigDecimal.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, BigDecimal.class.getSimpleName())) {
			return true;
		}
		if (StringUtils.equalsIgnoreCase(clsName, java.util.Date.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Date.class.getSimpleName())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断是否是基本类型
	 * 
	 * @param paramObject
	 * @return
	 */
	public static Class getPrimitiveType(String clsName) {
		if (StringUtils.equalsIgnoreCase(clsName, String.class.getName())
				||  StringUtils.equalsIgnoreCase(clsName, String.class.getSimpleName())) {
			return String.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Integer.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Integer.class.getSimpleName()) ) {
			return Integer.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Float.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Float.class.getSimpleName()) ) {
			return Float.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Long.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Long.class.getSimpleName())) {
			return Long.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Double.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Double.class.getSimpleName())) {
			return Double.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Boolean.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Boolean.class.getSimpleName())) {
			return Boolean.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, Character.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Character.class.getSimpleName())) {
			return Character.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, BigDecimal.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, BigDecimal.class.getSimpleName())) {
			return BigDecimal.class;
		}
		if (StringUtils.equalsIgnoreCase(clsName, java.util.Date.class.getName())
				|| StringUtils.equalsIgnoreCase(clsName, Date.class.getSimpleName())) {
			return Date.class;
		}
		return java.lang.Object.class;
	}

	/**
	 * 判断是否是基本类型
	 * 
	 * @param paramObject
	 * @return
	 */
	public static boolean isPrimitiveType(Class cls) {
		if (cls.getName().equals(String.class.getName())) {
			return true;
		}
		if (cls.getName().equals(Integer.class.getName())) {
			return true;
		}
		if (cls.getName().equals(Float.class.getName())) {
			return true;
		}
		if (cls.getName().equals(Long.class.getName())) {
			return true;
		}
		if (cls.getName().equals(Double.class.getName())) {
			return true;
		}
		if (cls.getName().equals(Boolean.class.getName())) {
			return true;
		}
		if (cls.getName().equals(Character.class.getName())) {
			return true;
		}
		if (cls.getName().equals(BigDecimal.class.getName())) {
			return true;
		}
		if (cls.getName().equals(BigDecimal.class.getName())) {
			return true;
		}
		if (cls.getName().equals(java.util.Date.class.getName())) {
			return true;
		}
		return false;
	}
}
