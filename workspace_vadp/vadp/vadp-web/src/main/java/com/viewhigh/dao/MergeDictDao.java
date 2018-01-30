package com.viewhigh.dao;

import com.viewhigh.entity.BdDeptMerge;
import com.viewhigh.vadp.framework.data.base.dao.IBaseDao;
import com.viewhigh.vo.MergeDictVO;

import java.util.List;

/**
 * Created by litianzhu on 2018/1/9.
 */
public interface MergeDictDao extends IBaseDao {

    BdDeptMerge findById(String pk);
    List<MergeDictVO> findByParam(BdDeptMerge m);
    void saveList(List<MergeDictVO> mergeDictVOs);
    void deleteByIds(String ids);

}
