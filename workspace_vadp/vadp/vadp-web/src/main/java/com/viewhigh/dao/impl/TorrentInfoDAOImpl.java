package com.viewhigh.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import cn.hutool.core.util.StrUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.viewhigh.dao.IBdMaterialsDao;
import com.viewhigh.dao.IMovieInfoDAO;
import com.viewhigh.dao.ITorrentInfoDAO;
import com.viewhigh.entity.MovieInfo;
import com.viewhigh.entity.TorrentInfo;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import com.viewhigh.vadp.framework.util.StringUtil;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class TorrentInfoDAOImpl extends BaseHibernateDAO implements ITorrentInfoDAO {

	@Override
	public void saveOrUpdateTorrent(TorrentInfo ti) {
		this.getHibernateTemplate().saveOrUpdate(ti);
	}

	@Override
	public List<TorrentInfo> queryTorrents(String movieId) {
		return this.queryObjects("from TorrentInfo where movieId=?", movieId);
	}

	@Override
	public void delTorrent(String id) {
		this.update("delete from torrent_info where id=?", new Object[]{id});
	}

	@Override
	public TorrentInfo getTorrentInfo(String id) {
		return this.getHibernateTemplate().get(TorrentInfo.class, id);
	}

	@Override
	public int getTorrentCount(String movieId) {
		int count = this.getRecordCount("select id from torrent_info", null);
		return 0;
	}


}
