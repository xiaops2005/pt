package com.viewhigh.service.impl;

import com.viewhigh.dao.HiaDeptDao;
import com.viewhigh.entity.BdDeptHia;
import com.viewhigh.service.HiaDeptService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HiaDeptServiceImpl extends BaseServiceImpl implements HiaDeptService {

    @Autowired
    private HiaDeptDao hiaDeptDao;

    @Override
    public List<BdDeptHia> queryBdDeptHia(String hiaCode, String isStop) {
        return hiaDeptDao.queryBdDeptHia(hiaCode, isStop);
    }

    @Override
    public void saveHiaDept(List<BdDeptHia> bdDeptHiaList) {
        hiaDeptDao.saveHiaDept(bdDeptHiaList);
    }

    @Override
    public void deleteByIds(String pkDeptHia) {
        hiaDeptDao.deleteByIds(pkDeptHia);
    }
}
