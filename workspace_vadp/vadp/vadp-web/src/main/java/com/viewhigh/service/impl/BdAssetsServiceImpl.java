package com.viewhigh.service.impl;

import com.viewhigh.dao.IBDAssetsDao;
import com.viewhigh.entity.BdAssets;
import com.viewhigh.service.BdAssetsService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("bdAssetsServiceImpl")
@Transactional
public class BdAssetsServiceImpl extends BaseServiceImpl implements BdAssetsService {
	@Autowired
	private IBDAssetsDao bdAssetsDao;


	@Override
	public List<BdAssets> findALl() {
		return bdAssetsDao.findAll();
	}

	@Override
	public List<BdAssets> findByParam(String accYear, String pkHospital, String assetsCode, String assetsName, String capitalSourceId, String isStopId, Integer standardFlag) {
		return bdAssetsDao.findByParam(accYear,pkHospital,assetsCode,assetsName,capitalSourceId,isStopId, standardFlag);
	}


	@Override
	public void deleteByIds(String pkAssets) {
		bdAssetsDao.deleteByIds(pkAssets);
	}

	@Override
	public void saveBdAssets(List<BdAssets> bdAssetsList) {
		bdAssetsDao.saveBdAssets(bdAssetsList);
	}
}
