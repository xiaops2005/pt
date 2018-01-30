package com.viewhigh.service.impl;

import com.viewhigh.dao.BdDeptDao;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.service.BdDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bdDeptServiceImpl")
@Transactional
public class BdDeptServiceImpl implements BdDeptService {

	@Autowired
	private BdDeptDao bdDeptDao;

	@Override
	public List<BdDept> findByParam(Integer year, String pkHospital, String deptCode, String deptName, String belongSystem, Integer isStop) {
		return bdDeptDao.findByParam(year, pkHospital, deptCode, deptName, belongSystem, isStop);
	}

	@Override
	public void deleteByIds(String pkDepts) {
		bdDeptDao.deleteByIds(pkDepts);
	}

	@Override
	public void saveBdDept(List<BdDept> bdDeptList) {
		bdDeptDao.saveBdDept(bdDeptList);
	}
}
