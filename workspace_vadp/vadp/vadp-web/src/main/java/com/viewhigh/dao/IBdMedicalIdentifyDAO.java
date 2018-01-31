package com.viewhigh.dao;


import com.viewhigh.excel.domain.entity.BdMedicalIdentify;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface IBdMedicalIdentifyDAO extends IBaseDao{
	
	public QueryResult getHospital();

	public QueryResult getList(String hospital, String idCode, String idName, String isStop);
	
	public String save(BdMedicalIdentify b);

	void saveList(List<BdMedicalIdentify> list);
	
	public String delete(String arr);
	
	public String startOrStop(String arr, String flag);
}
