package com.viewhigh.dao;

import com.viewhigh.entity.MovieInfo;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.ReCost;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface IMovieInfoDAO {
	/**
	 * ID是否存在
	 * @param id
	 * @return
	 */
	Boolean exists(String id);

	/**
	 * 根据ID获取MovieInfo对象
	 * @param id
	 * @return
	 */
	MovieInfo getMovieInfo(String id);

	/**
	 * 添加豆瓣API的返回值
	 * @param id
	 * @param resultJson
	 */
	void addResponseJson(String id, String resultJson);
	
	/**
	 * 根据ID删除
	 * @param ids
	 */
	void deleteByIds(String ids);
	
	QueryResult query(MovieInfo mi);

	void saveDoubanValue(MovieInfo mi);

	MovieInfo queryById(String id);

	void setTorrentFlag(String movieId, String torrentFlag);

	QueryResult queryPublishMovieList();

	void publish(String ids, String publishFlag);

	QueryResult searchMovie(String keyword);

	void postFeedback(String content);

}