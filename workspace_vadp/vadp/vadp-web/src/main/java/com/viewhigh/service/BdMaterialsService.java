package com.viewhigh.service;

import com.viewhigh.entity.BdHospital;
import com.viewhigh.excel.domain.entity.BdMaterials;

import java.util.List;

public interface BdMaterialsService {
	public List<BdMaterials> findAll();
	
    List<BdMaterials> findByParam(Integer year, String pkHospital, String deptCode, String deptName, String isSingle, String isStop);

    void deleteByIds(String pkMaterials);

    void saveBdMaterials(List<BdMaterials> bdMaterialsList);
}
