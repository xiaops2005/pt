package com.viewhigh.service.impl;

import com.viewhigh.dao.MergeDictDao;
import com.viewhigh.entity.BdDeptMerge;
import com.viewhigh.service.MergeDictService;
import com.viewhigh.vadp.framework.base.service.BaseServiceImpl;
import com.viewhigh.vo.MergeDictVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by litianzhu on 2018/1/9.
 */
@Service("mergeDictServiceImpl")
@Transactional
public class MergeDictServiceImpl extends BaseServiceImpl implements MergeDictService {

    @Autowired
    private MergeDictDao mergeDictDao;

    @Override
    public BdDeptMerge save(BdDeptMerge mergeDict) {
        String id = (String) mergeDictDao.addObject(mergeDict);
        return mergeDictDao.findById(id);
    }

    @Override
    public List<MergeDictVO> findByParam(BdDeptMerge mergeDict) {
        return mergeDictDao.findByParam(mergeDict);
    }

    @Override
    public void saveList(List<MergeDictVO> mergeDictVOs) {
        mergeDictDao.saveList(mergeDictVOs);
    }

    @Override
    public void deleteByIds(String ids) {
        mergeDictDao.deleteByIds(ids);
    }
}
