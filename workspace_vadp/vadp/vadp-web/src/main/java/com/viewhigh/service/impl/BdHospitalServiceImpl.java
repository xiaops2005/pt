package com.viewhigh.service.impl;

import com.viewhigh.dao.BdHospitalDao;
import com.viewhigh.entity.BdHospital;
import com.viewhigh.service.BdHospitalService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by litianzhu on 2018/1/10.
 */
@Service("bdHospitalServiceImpl")
@Transactional
public class BdHospitalServiceImpl extends BaseServiceImpl implements BdHospitalService {
	@Autowired
	private BdHospitalDao bdHospitalDao;


	@Override
	public List<BdHospital> findALl() {
		return bdHospitalDao.findAll();
	}

	@Override
	public List<BdHospital> findByParam(String hospitalCode, String hospitalName, String hospitalTypeId, String hospitalClassId) {
		log.info("abcxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		return bdHospitalDao.findByParam(hospitalCode, hospitalName, hospitalTypeId, hospitalClassId);
	}

	@Override
	public void deleteByIds(String pkHospitals) {
		bdHospitalDao.deleteByIds(pkHospitals);
	}

	@Override
	public void saveBdHospital(List<BdHospital> bdHospitalList) {
		bdHospitalDao.saveBdHospital(bdHospitalList);
	}
}
