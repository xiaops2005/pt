package com.viewhigh.service;

import com.viewhigh.entity.BdDeptMerge;
import com.viewhigh.vo.MergeDictVO;

import java.util.List;

/**
 * Created by litianzhu on 2018/1/9.
 */
public interface MergeDictService {

    BdDeptMerge save(BdDeptMerge mergeDict);

    List<MergeDictVO> findByParam(BdDeptMerge m);

    void saveList(List<MergeDictVO> mergeDictVOs);

    void deleteByIds(String ids);
}
