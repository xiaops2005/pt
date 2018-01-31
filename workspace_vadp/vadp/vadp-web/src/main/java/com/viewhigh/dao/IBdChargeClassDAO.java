package com.viewhigh.dao;


import com.viewhigh.excel.domain.entity.BdChargesClass;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface IBdChargeClassDAO extends IBaseDao{
	
	public QueryResult getHospital();

	public QueryResult getList(String hospital, String itemCode, String itemName, String isStop);
	
	public String save(List<BdChargesClass> lst);
	
	public String delete(String arr);
	
	public String startOrStop(String arr, String flag);
}
