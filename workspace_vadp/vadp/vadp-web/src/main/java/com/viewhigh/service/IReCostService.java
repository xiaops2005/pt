package com.viewhigh.service;

import com.viewhigh.entity.BdAssets;
import com.viewhigh.excel.domain.Base;
import com.viewhigh.excel.domain.entity.ReCost;
import com.viewhigh.vadp.framework.data.persistence.pagination.QueryResult;

import java.util.List;


public interface IReCostService {

    List<ReCost> findALl();

    QueryResult findByParam(String year, String pkHospital, String deptCode, String deptName);

    void deleteByIds(String pkCost);

    void saveReCost(List<ReCost> reCostList);


}
