package com.viewhigh.dao;

import com.viewhigh.excel.domain.entity.BdCode;

import java.util.List;
import java.util.Map;

/**
 * 基础字典
 */
public interface BdCodeDao {
    List<BdCode> findALlById(String id);
    void save(BdCode bdDept);
    void save(Map<String, BdCode> map);

    List<BdCode> findAll();
}
