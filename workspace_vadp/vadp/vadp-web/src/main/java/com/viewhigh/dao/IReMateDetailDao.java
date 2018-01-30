package com.viewhigh.dao;

import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;

import java.util.List;
import java.util.Map;

public interface IReMateDetailDao extends IBaseDao {
	List<ReMateDetail> findByParam(Integer year, String pkHospital, String materialsDeptCode, String materialsDeptName, String isCharges, String pkMaterials);
}
