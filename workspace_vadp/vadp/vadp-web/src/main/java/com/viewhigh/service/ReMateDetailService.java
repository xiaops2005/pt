package com.viewhigh.service;

import com.viewhigh.entity.ReMateDetail;
import com.viewhigh.excel.domain.entity.BdMaterials;

import java.util.List;

public interface ReMateDetailService {
    List<ReMateDetail> findByParam(Integer year, String pkHospital, String materialsDeptCode, String materialsDeptName, String isCharges, String pkMaterials);
    
    String exportReMateDetail(Integer year, String pkHospital, String materialsDeptCode, String materialsDeptName,String isCharges, String pkMaterials);
}
