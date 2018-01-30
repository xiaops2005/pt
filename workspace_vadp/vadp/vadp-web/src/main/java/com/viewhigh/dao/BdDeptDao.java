package com.viewhigh.dao;

import com.viewhigh.excel.domain.entity.BdDept;

import java.util.List;
import java.util.Map;

/**
 * Created by litianzhu on 2017/12/28.
 */
public interface BdDeptDao {
	List<BdDept> findALl();

	void save(BdDept bdDept);

	void save(Map<String, BdDept> map);

	List<BdDept> findByParam(Integer year, String pkHospital, String deptCode, String deptName, String belongSystem, Integer isStop);

	void deleteByIds(String pkDepts);

	void saveBdDept(List<BdDept> bdDeptList);
}
