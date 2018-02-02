package com.viewhigh.service;

import com.alibaba.fastjson.JSONObject;
import com.viewhigh.entity.MovieInfo;
import com.viewhigh.entity.TorrentInfo;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface TorrentService {
	
	void saveOrUpdateTorrent(TorrentInfo ti);
	
	List<TorrentInfo> queryTorrents(String movieId);
	
	void delTorrent(String id);
}
