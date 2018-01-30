package com.viewhigh.service;

import com.viewhigh.entity.BdDeptHia;

import java.util.List;

public interface HiaDeptService {

    /**
     * 查询
     *
     * @param hiaCode
     * @param isStop
     * @return
     */
    public List<BdDeptHia> queryBdDeptHia(String hiaCode, String isStop);


    /**
     * 保存
     *
     * @param bdDeptHiaList
     */
    public void saveHiaDept(List<BdDeptHia> bdDeptHiaList);

    /**
     * 删除
     * @param pkDeptHia
     */
    public void deleteByIds(String pkDeptHia);
}
