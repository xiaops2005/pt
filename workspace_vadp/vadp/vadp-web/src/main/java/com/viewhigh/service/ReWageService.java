package com.viewhigh.service;

import com.viewhigh.entity.ReWage;

import java.util.List;

public interface ReWageService {

    /**
     * 查询方法
     * 页面查询按钮触发的查询
     * @param accYar
     * @param pkHospital
     * @param pkDept
     * @return List<ReWage>
     */
    List<ReWage> getReWage(String accYar, String pkHospital, String pkDept);



}
