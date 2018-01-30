package com.viewhigh.service.impl;

import com.viewhigh.dao.ReWageDao;
import com.viewhigh.entity.ReWage;
import com.viewhigh.service.ReWageService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReWageServiceImpl extends BaseServiceImpl implements ReWageService {

    @Autowired
    private ReWageDao reWageDao;

    @Override
    public List<ReWage> getReWage(String accYar, String pkHospital, String pkDept) {
        List<ReWage> list = reWageDao.getReWage(accYar, pkHospital, pkDept);
        return list;
    }
}
