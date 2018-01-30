package com.viewhigh.service;

import com.viewhigh.entity.BdHospital;

import java.util.List;

/**
 * Created by litianzhu on 2018/1/10.
 */
public interface BdHospitalService {

    List<BdHospital> findALl();

    List<BdHospital> findByParam(String hospitalCode, String hospitalName, String hospitalTypeId, String hospitalClassId);

    void deleteByIds(String pkHospitals);

    void saveBdHospital(List<BdHospital> bdHospitalList);


}
