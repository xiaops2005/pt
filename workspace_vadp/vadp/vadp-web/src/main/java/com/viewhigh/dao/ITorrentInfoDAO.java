package com.viewhigh.dao;

import com.viewhigh.entity.MovieInfo;
import com.viewhigh.entity.TorrentInfo;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.ReCost;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface ITorrentInfoDAO {
	
	void saveOrUpdateTorrent(TorrentInfo ti);

	List<TorrentInfo> queryTorrents(String movieId);

	void delTorrent(String id);

	TorrentInfo getTorrentInfo(String id);

	int getTorrentCount(String movieId);
}