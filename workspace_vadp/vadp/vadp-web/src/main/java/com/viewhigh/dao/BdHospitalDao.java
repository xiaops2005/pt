package com.viewhigh.dao;

import com.viewhigh.entity.BdHospital;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;

import java.util.List;

/**
 * Created by litianzhu on 2018/1/10.
 */
public interface BdHospitalDao extends IBaseDao {
	List<BdHospital> findAll();

	List<BdHospital> findByParam(String hospitalCode, String hospitalName, String hospitalTypeId, String hospitalClassId);

	void deleteByIds(String pkHospitals);

	void saveBdHospital(List<BdHospital> bdHospitalList);

}
