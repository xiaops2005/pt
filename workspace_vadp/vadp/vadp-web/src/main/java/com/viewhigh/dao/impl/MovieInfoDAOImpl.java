package com.viewhigh.dao.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Repository;
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

}
