package com.viewhigh;

public class Constants {
	/**
	 * 调用豆瓣的时间间隔（最多10秒调用一次）
	 */
	public static long INVOKE_DOUBAN_INTERVAL = 10*1000L;

	/**
	 * 豆瓣API前缀
	 */
	public static final String DOUBAN_API_PREFIX = "http://api.douban.com/v2/movie/subject/";
	
	/**
	 * 豆瓣API host
	 */
	public static final String DOUBAN_API_HOST = "api.douban.com";
	
}
