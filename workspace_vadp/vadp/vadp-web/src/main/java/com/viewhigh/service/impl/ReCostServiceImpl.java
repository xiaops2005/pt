package com.viewhigh.service.impl;

import com.viewhigh.dao.IBDAssetsDao;
import com.viewhigh.dao.IReCostDao;
import com.viewhigh.entity.BdAssets;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.ReCost;
import com.viewhigh.service.BdAssetsService;
import com.viewhigh.service.IReCostService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("reCostServiceImpl")
@Transactional
public class ReCostServiceImpl extends BaseServiceImpl implements IReCostService {
	@Autowired
	private IReCostDao reCostDao;


	@Override
	public List<ReCost>   findALl() {
		return reCostDao.findALl();
	}

	@Override
	public QueryResult findByParam(String year, String pkHospital, String deptCode, String deptName){
		return reCostDao.findByParam( year,  pkHospital,  deptCode,  deptName);
	}


	@Override
	public void deleteByIds(String pkCost) {
        reCostDao.deleteByIds(pkCost);
	}

	@Override
	public void saveReCost(List<ReCost>  reCostList) {
        reCostDao.saveReCost(reCostList);
	}
}
