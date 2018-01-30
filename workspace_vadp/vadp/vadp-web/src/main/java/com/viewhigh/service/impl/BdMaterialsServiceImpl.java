package com.viewhigh.service.impl;

import com.viewhigh.dao.BdCodeDao;
import com.viewhigh.dao.IBdMaterialsDao;
import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.service.BdCodeService;
import com.viewhigh.service.BdMaterialsService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BdMaterialsServiceImpl extends BaseServiceImpl implements BdMaterialsService {
	@Autowired
	private IBdMaterialsDao bdMaterialsDao;


	@Override
	public List<BdMaterials> findAll() {
		return bdMaterialsDao.findAll();
	}

	@Override
	public List<BdMaterials> findByParam(Integer year, String pkHospital, String materialsCode, String materialsName, String isSingle, String isStop) {
		return bdMaterialsDao.findByParam(year,pkHospital,materialsCode,materialsName,isSingle,isStop);
	}


	@Override
	public void deleteByIds(String pkMaterials) {
		bdMaterialsDao.deleteByIds(pkMaterials);
	}


	@Override
	public void saveBdMaterials(List<BdMaterials> bdMaterialsList) {
		bdMaterialsDao.saveBdMaterials(bdMaterialsList);
	}

}
