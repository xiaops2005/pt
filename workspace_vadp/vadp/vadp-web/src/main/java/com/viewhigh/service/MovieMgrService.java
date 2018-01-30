package com.viewhigh.service;

import com.alibaba.fastjson.JSONObject;
import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.excel.domain.entity.BdMaterials;

import java.util.List;

public interface MovieMgrService {
	/**
	 * 从数据库或者API获取豆瓣信息
	 * @param doubanId
	 * @return
	 */
	JSONObject getDoubanMovieInfo(String doubanId);
}
