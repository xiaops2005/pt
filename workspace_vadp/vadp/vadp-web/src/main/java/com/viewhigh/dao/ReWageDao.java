package com.viewhigh.dao;

import com.viewhigh.entity.ReWage;

import java.util.List;

public interface ReWageDao {
    List<ReWage> getReWage(String accYar, String pkHospital, String pkDept);
}
