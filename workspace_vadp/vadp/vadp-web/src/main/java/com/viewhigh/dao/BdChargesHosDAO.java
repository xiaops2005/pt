package com.viewhigh.dao;

import com.viewhigh.excel.domain.entity.BdChargesHos;
import java.util.List;
import java.util.Map;

/**
 */
public interface BdChargesHosDAO {
    List<BdChargesHos> findALl();
    void save(BdChargesHos charges);
    void save(Map<String,BdChargesHos> chargesmap);
}
