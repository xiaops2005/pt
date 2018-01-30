package com.viewhigh.dao;

import com.viewhigh.entity.BdAssets;

import java.util.List;

/**
 * @Author zhaoxizi
 * @Date 18/1/9下午6:37
 */
public interface IBDAssetsDao {
    List<BdAssets> findAll();

    List<BdAssets> findByParam(String accYear, String pkHospital, String assetsCode, String assetsName, String capitalSourceId, String isStopId, Integer standardFlag);


    void deleteByIds(String pkAssets);

    void saveBdAssets(List<BdAssets> bdAssetsList);
}
