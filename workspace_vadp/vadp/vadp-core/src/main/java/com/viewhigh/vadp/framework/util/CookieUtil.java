package com.viewhigh.vadp.framework.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie常用操作
 * 版权所属：东软望海科技有限公司。 
 * 作者：刘晓平 版本：V1.0 
 * 创建日期： 2017年06月22日 
 * 修改日期:2017年06月22日
 */
public class CookieUtil {

	public static Integer maxAge = 24 * 60 * 60; 
	
	/**
	 * 根据名字获取cookie
	 * 
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static String getCookieByName(HttpServletRequest request, String name) {
		Map<String, String> cookieMap = getCookieAll(request);
		if (cookieMap.containsKey(name)) {
			return cookieMap.get(name);
		} 
		return null;
	}

	

	/**
	 * 读取所有cookie
	 * 
	 * 注意二、从客户端读取Cookie时，包括maxAge在内的其他属性都是不可读的，也不会被提交。
	 * 浏览器提交Cookie时只会提交name与value属性。
	 * maxAge属性只被浏览器用来判断Cookie是否过期
	 * 
	 * @param request
	 * @param response
	 */
	public static Map<String,String> getCookieAll(HttpServletRequest request) {
		Map<String,String> hashMap = new HashMap<String, String>();
		Cookie[] cookies = request.getCookies();// 这样便可以获取一个cookie数组
		if (null == cookies) {
			return hashMap;
		}
		for (Cookie cookie : cookies) {
			hashMap.put(cookie.getName(), cookie.getValue());
		}
		return hashMap;
	}

	/**
	 * 添加cookie
	 * 
	 * @param response
	 * @param name
	 * @param value
	 */
	public static void addCookie(HttpServletResponse response, String name, String value) {
		Cookie cookie = new Cookie(name.trim(), value.trim());
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

	/**
	 * 修改cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 * @param value
	 *  注意一、修改、删除Cookie时，新建的Cookie除value、maxAge之外的所有属性，
	 *  例如name、path、domain等，都要与原Cookie完全一样。
	 *  否则，浏览器将视为两个不同的Cookie不予覆盖，导致修改、删除失败。
	 */
	public static void editCookie(HttpServletRequest request, HttpServletResponse response, String name, String value) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				cookie.setValue(value);
				cookie.setPath("/");
				cookie.setMaxAge(maxAge);
				response.addCookie(cookie);
				break;
			}
		}
	}

	/**
	 * 删除cookie
	 * 
	 * @param request
	 * @param response
	 * @param name
	 */
	public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return;
		}
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(name)) {
				cookie.setValue(null);
				cookie.setMaxAge(0);// 立即销毁cookie
				cookie.setPath("/");
				response.addCookie(cookie);
				break;
			}
		}
	}
}
