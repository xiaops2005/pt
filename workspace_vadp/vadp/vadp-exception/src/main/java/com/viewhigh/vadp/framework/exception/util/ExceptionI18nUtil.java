package com.viewhigh.vadp.framework.exception.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * 
 * 读取异常错误代码国际化资源文件
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月26日
 * 修改日期: 2017年06月26日
 */
public class ExceptionI18nUtil {

	
	/**
	 * 判断是否存在错误异常码   
	 * @param: @param paramException
	 * @param: @param paramLocale
	 * @param: @return      
	 * @return: boolean      
	 * @throws
	 */
	public static boolean has(CoreException paramException, Locale paramLocale){
		if (paramException == null) {
			throw new IllegalArgumentException(
					"inpute paramException is null at getI18nInfo method of " + ExceptionI18nUtil.class.getName());
		}
		if (paramLocale == null) {
			paramLocale = Locale.getDefault();
		}
		
		String filePath = getProPertyResource(paramException.getClass().getName());
		
		ResourceBundle localResourceBundle = ResourceBundle.getBundle(filePath, paramLocale);
		
		return localResourceBundle.containsKey(paramException.getCode());
	}
	
	
	private static String getProPertyResource(String paramString) {
		String str = null;
		int i = paramString.lastIndexOf(".");
		paramString = paramString.substring(0, i);
		str = paramString + "/LocalStrings";
		return str;
	}

	
	/**
	 * 
	 * 读取异常代码错误信息   
	 * @param: @param paramUniEAPException
	 * @param: @param paramLocale
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public static String getI18nInfo(CoreException paramException, Locale paramLocale) {
		if (paramException == null) {
			throw new IllegalArgumentException(
					"inpute paramException is null at getI18nInfo method of " + ExceptionI18nUtil.class.getName());
		}
		if (paramLocale == null) {
			paramLocale = Locale.getDefault();
		}
		
		String filePath = getProPertyResource(paramException.getClass().getName());
		
		ResourceBundle localResourceBundle = ResourceBundle.getBundle(filePath, paramLocale);
		String str2 = localResourceBundle.getString(paramException.getCode());
		int i = getParameterCount(str2);
		if ((i > 0) && ((paramException.getArgs() == null) || (paramException.getArgs().length < i))) {
			throw new IllegalArgumentException(str2 + " need " + i + " parameters Exception args don't map");
		}

		str2 = MessageFormat.format(str2, paramException.getArgs());

		return str2;
	}

	private static int getParameterCount(String paramString) {
		int i = 0;
		String str = "\\{[0-9]+\\}";
		Pattern localPattern = Pattern.compile(str);
		Matcher localMatcher = localPattern.matcher(paramString);
		while (localMatcher.find()) {
			i++;
		}
		return i;
	}

}
