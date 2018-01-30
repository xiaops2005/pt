package com.viewhigh.service;

import com.viewhigh.excel.domain.entity.BdDept;

import java.util.List;

public interface BdDeptService {

	List<BdDept> findByParam(Integer year, String pkHospital, String deptCode, String deptName, String belongSystem, Integer isStop);

	void deleteByIds(String pkDepts);

	void saveBdDept(List<BdDept> bdDeptList);
}
