package com.viewhigh.service;


import java.util.List;

import com.viewhigh.excel.domain.entity.BdChargesClass;
import com.viewhigh.vadp.framework.base.IBaseService;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

public interface IBdChargeClassService extends IBaseService {
	//获取医院字典
	public QueryResult getHospital();
	//获取项目大类列表
	public QueryResult getList(String  hospital ,String itemCode,String itemName,String isStop);
	//保存
	public String save(List<BdChargesClass> b);
	//删除 arr格式（1，2，3）删除列表主键
	public String delete(String arr);
	//启用、停用 flag为表示 0否 1是
	public String startOrStop(String arr,String flag);
}
