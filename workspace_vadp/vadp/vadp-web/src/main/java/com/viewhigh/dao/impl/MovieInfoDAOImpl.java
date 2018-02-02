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
import com.viewhigh.entity.MovieInfo;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import com.viewhigh.vadp.framework.util.StringUtil;

@Repository
@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW )
public class MovieInfoDAOImpl extends BaseHibernateDAO implements IMovieInfoDAO {

	@Override
	public Boolean exists(String id) {
		String hql = "select count(*) from MovieInfo where id='" + id +"'";  
		Integer count = ((Integer)getHibernateTemplate().iterate(hql).next()).intValue();
		if(count == 0){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public MovieInfo getMovieInfo(String id) {
		MovieInfo mi = this.findById(id, MovieInfo.class);
		return mi;
	}

	@Override
	public void addResponseJson(String id, String resultJson) {
		MovieInfo mi = new MovieInfo();
		mi.setId(id);
		mi.setResponseJson(resultJson);
		this.addObject(mi);
	}

	@Override
	public void deleteByIds(String ids) {
		String sql = "delete from movie_info where id in (" + ids + ")";
		this.update(sql, null);
	}

	public QueryResult query(MovieInfo mi) {
		StringBuffer sql = new StringBuffer(" from MovieInfo where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtils.isBlank(mi.getTitle())) {
			sql.append(" and title like ?");
			params.add("%"+mi.getTitle()+"%");
		}
		if (!StringUtils.isBlank(mi.getDirectors())) {
			sql.append(" and directors like ?");
			params.add("%"+mi.getDirectors()+"%");
		}
		if (!StringUtils.isBlank(mi.getCasts())) {
			sql.append(" and casts like ?");
			params.add("%"+mi.getCasts()+"%");
		}
		if (!StringUtils.isBlank(mi.getYear())) {
			sql.append(" and year like ?");
			params.add("%"+mi.getYear()+"%");
		}
		if (!StringUtils.isBlank(mi.getCountries())) {
			sql.append(" and countries like ?");
			params.add("%"+mi.getCountries()+"%");
		}
		if (!StringUtils.isBlank(mi.getGenres())) {
			sql.append("  and genres like ?");
			params.add("%"+mi.getGenres()+"%");
		}
		sql.append(" order by createTime desc");
		return this.queryObjectsByPage(sql.toString(), params.toArray());
	}

	@Override
	public void saveDoubanValue(MovieInfo mi) {
		this.updateObject(mi);
	}

	@Override
	public MovieInfo queryById(String id) {
		MovieInfo mi = this.getHibernateTemplate().get(MovieInfo.class, id);
		return mi;
	}

	@Override
	public void setTorrentFlag(String movieId, boolean b) {
		String torrentFlag = b==true?"1":"0";
		String sql = "update movie_info set torrent_flag=" + torrentFlag  + " where id=" + movieId;
		this.update(sql);
	}

}
