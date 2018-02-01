package com.viewhigh.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface MovieMgrService {
	/**
	 * 保存豆瓣接口JSON返回值
	 * @param doubanId
	 * @return
	 */
	JSONObject saveDoubanJson(String doubanId);
	
	/**
	 * 从数据库解析豆瓣JSON，并存到数据库
	 * @param doubanId
	 */
	void saveDoubanValue(String doubanId);
}
