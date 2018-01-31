package com.viewhigh.service;


import com.viewhigh.excel.domain.entity.BdMedicalIdentify;
import com.viewhigh.vadp.framework.base.IBaseService;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;

public interface IBdMedicalIdentifyService extends IBaseService {
	//获取医院字典
	public QueryResult getHospital();
	//获取列表
	public QueryResult getList(String hospital,String idCode,String idName,String isStop);
	//保存
	public String save(BdMedicalIdentify b);
	//保存list
	public void saveList(List<BdMedicalIdentify> list);
	//删除 arr格式（1，2，3）删除列表主键
	public String delete(String arr);
	//启用、停用 flag为表示 0否 1是
	public String startOrStop(String arr,String flag);
}
