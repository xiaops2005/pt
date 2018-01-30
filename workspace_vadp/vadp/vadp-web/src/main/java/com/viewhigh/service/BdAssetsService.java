package com.viewhigh.service;

import com.viewhigh.entity.BdAssets;

import java.util.List;


public interface BdAssetsService {

    List<BdAssets> findALl();

    List<BdAssets> findByParam(String accYear, String pkHospital, String assetsCode, String assetsName, String capitalSourceId, String isStopId, Integer standardFlag);

    void deleteByIds(String pkAssets);

    void saveBdAssets(List<BdAssets> bdAssetsList);


}
