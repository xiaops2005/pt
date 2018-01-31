package com.viewhigh.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface MovieMgrService {
	/**
	 * 从数据库或者API获取豆瓣信息
	 * @param doubanId
	 * @return
	 */
	JSONObject saveDoubanJson(String doubanId);
}
