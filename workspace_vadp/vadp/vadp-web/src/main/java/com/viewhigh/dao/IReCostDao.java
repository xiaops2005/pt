package com.viewhigh.dao;

import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.ReCost;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface IReCostDao {

	public abstract String saveDept(BdDept dept);

    List<ReCost> findALl();

    QueryResult findByParam(String accYear, String pkHospital, String deptCode, String deptName);

	void deleteByIds(String pkCost);

	void saveReCost(List<ReCost> reCostList);

}