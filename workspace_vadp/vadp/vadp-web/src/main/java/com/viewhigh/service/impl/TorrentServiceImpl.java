package com.viewhigh.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewhigh.Constants;
import com.viewhigh.dao.IMovieInfoDAO;
import com.viewhigh.dao.ITorrentInfoDAO;
import com.viewhigh.dao.impl.MovieInfoDAOImpl;
import com.viewhigh.entity.MovieInfo;
import com.viewhigh.entity.TorrentInfo;
import com.viewhigh.service.MovieMgrService;
import com.viewhigh.service.TorrentService;
import com.viewhigh.util.Strings;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

@Service
public class TorrentServiceImpl extends BaseServiceImpl implements TorrentService {

	@Autowired
	private IMovieInfoDAO movieInfoDao;
	@Autowired
	private ITorrentInfoDAO torrentInfoDao;

	@Override
	public void saveOrUpdateTorrent(TorrentInfo ti) {
		torrentInfoDao.saveOrUpdateTorrent(ti);
		movieInfoDao.setTorrentFlag(ti.getMovieId(),"1");
	}

	@Override
	public List<TorrentInfo> queryTorrents(String movieId) {
		return torrentInfoDao.queryTorrents(movieId);
	}

	@Override
	public void delTorrent(String id) {
		TorrentInfo ti = torrentInfoDao.getTorrentInfo(id);
		String movieId = ti.getMovieId();
		int count = torrentInfoDao.getTorrentCount(movieId);
		if(count == 0){
			movieInfoDao.setTorrentFlag(movieId, "0");
		}
		torrentInfoDao.delTorrent(id);
		
	}


}
