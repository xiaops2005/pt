package com.viewhigh.dao;


import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface IStandardDeptDAO extends IBaseDao{

	public QueryResult getStandardDept(String hospital,String deptCode, String deptName,String belongSystem, String isStop);
	
	public String save(List<BdDept> dept);
	
	public String delete(String arr);
	
	public String startOrStop(String arr, String flag);
}
