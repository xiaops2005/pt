package com.viewhigh.dao;

import com.viewhigh.entity.BdDeptHia;

import java.util.List;

public interface HiaDeptDao {
    List<BdDeptHia> queryBdDeptHia(String hiaCode, String isStop);

    void saveHiaDept(List<BdDeptHia> bdDeptHiaList);

    void deleteByIds(String pkDeptHia);
}
