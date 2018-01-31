package com.viewhigh.dao;

import com.viewhigh.entity.BdExecuteMethod;

import java.util.List;

/**
 * 执行方法 -- DAO 接口
 *
 * Created by PeiKai on 2018/1/9.
 */
public interface BdExecuteMethodDAO {

    BdExecuteMethod findBdExecuteMethodById(String id);

    List<BdExecuteMethod> getExecuteMethodList(String hospital, String methodCode, String methodName, Integer stopFlag);

    BdExecuteMethod saveExecuteMethod(BdExecuteMethod saveData);

    List<BdExecuteMethod> saveExecuteMethodList(List<BdExecuteMethod> saveData);

    void delExecuteMethodList(String[] delKeys);

    BdExecuteMethod modifyExecuteMethod(BdExecuteMethod modifyObj);

    List<BdExecuteMethod> startOrStopExecuteMethodList(Integer startOrStopFlag, String[] modifyKeys);
}
