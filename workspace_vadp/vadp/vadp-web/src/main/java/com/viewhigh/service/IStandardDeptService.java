package com.viewhigh.service;

import java.util.List;

import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.vadp.framework.base.IBaseService;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

public interface IStandardDeptService extends IBaseService {

	public QueryResult getStandardDept(String hospital,String deptCode, String deptName,String belongSystem, String isStop);
	
	public String save(List<BdDept> dept);
	
	public String delete(String arr);
	
	public String startOrStop(String arr,String flag);
}
