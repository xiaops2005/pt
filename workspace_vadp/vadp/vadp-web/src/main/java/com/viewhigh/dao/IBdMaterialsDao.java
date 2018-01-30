package com.viewhigh.dao;

import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;

import java.util.List;
import java.util.Map;

/**
 * Created by litianzhu on 2017/12/28.
 */
public interface IBdMaterialsDao extends IBaseDao {
    List<BdMaterials> findAll();
    void save(BdMaterials bdMaterials);
    void save(Map<String, BdMaterials> map);
    
	List<BdMaterials> findByParam(Integer year, String hospitalCode, String deptCode, String deptName, String isSingle, String isStop);
	void deleteByIds(String pkMaterials);
	void saveBdMaterials(List<BdMaterials> bdMaterialsList);
}
