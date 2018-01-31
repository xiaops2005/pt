package com.viewhigh.dao.impl;

import com.google.common.base.Strings;
import com.viewhigh.dao.BdExecuteMethodDAO;
import com.viewhigh.entity.BdExecuteMethod;
import com.viewhigh.vadp.framework.data.base.dao.BaseHibernateDAO;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 执行方法 -- DAO 实现
 * Created by PeiKai on 2018/1/9.
 */
@Repository
public class BdExecuteMethodDAOImpl extends BaseHibernateDAO implements BdExecuteMethodDAO{

    @Override
    public BdExecuteMethod findBdExecuteMethodById(String id) {
        System.out.println("查询执行方法 id = [" + id + "]");
        return (BdExecuteMethod) this.queryObject("from BdExecuteMethod b where b.pkExecuteMethod=?", id);
    }

    @Override
    public List<BdExecuteMethod> getExecuteMethodList(String hospital, String methodCode, String methodName, Integer stopFlag) {
        System.out.println("hospital = [" + hospital + "], methodCode = [" + methodCode + "], methodName = [" + methodName + "], stopFlag = [" + stopFlag + "]");

        String sql = "select b from BdExecuteMethod b where 1=1";
        List<Object> paramList = new ArrayList<>();
        if (hospital != null) {
            sql += " and b.pkHospital=?";
            paramList.add(hospital);
        }
        if (methodCode != null) {
            sql += " and b.methodCode=?";
            paramList.add(methodCode);
        }
        if (methodName != null) {
            sql += " and b.methodName=?";
            paramList.add(methodName);
        }
        if (stopFlag != null) {
            sql += " and b.isStop=?";
            paramList.add(stopFlag);
        }

        List resList = queryObjects(sql, paramList.toArray());

        return resList;
    }

    @Override
    @Transactional(readOnly = false)
    public BdExecuteMethod saveExecuteMethod(BdExecuteMethod insertObj) {
        Date nowTime = new Date();
        insertObj.setCreationtime(nowTime);
        this.addObject(insertObj);
        return insertObj;
    }

    @Override
    @Transactional(readOnly = false)
    public List<BdExecuteMethod> saveExecuteMethodList(List<BdExecuteMethod> saveData) {
        System.out.println("批量保存执行方法 " + saveData.size());
        Date nowTime = new Date();
        saveData.forEach(item -> {
            if (Strings.isNullOrEmpty(item.getPkExecuteMethod())) {
                item.setCreationtime(nowTime);
                this.addObject(item);
            }
            else {
                item.setModifiedtime(nowTime);
                this.updateObject(item);
            }
        });
        return saveData;
    }

    @Override
    @Transactional(readOnly = false)
    public void delExecuteMethodList(String[] delKeys) {
        System.out.println("批量删除执行方法 delKeys = " + Arrays.toString(delKeys));

        for (int i = 0; i < delKeys.length; i++) {
            BdExecuteMethod delObj = findBdExecuteMethodById(delKeys[i]);
            if (delObj != null) {
                this.removeObject(delObj);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public BdExecuteMethod modifyExecuteMethod(BdExecuteMethod modifyObj) {
        Date nowTime = new Date();
        modifyObj.setModifiedtime(nowTime);
        this.updateObject(modifyObj);
        return findBdExecuteMethodById(modifyObj.getPkExecuteMethod());
    }

    @Override
    @Transactional(readOnly = false)
    public List<BdExecuteMethod> startOrStopExecuteMethodList(Integer startOrStopFlag, String[] modifyKeys) {
        System.out.println(startOrStopFlag == 1 ? "启用" : "停用" + " startOrStopFlag = [" + startOrStopFlag + "], delKeys = [" + modifyKeys + "]");
        Date nowTime = new Date();
        List<BdExecuteMethod> modifyObjList = new ArrayList<>();
        for (int i = 0; i < modifyKeys.length; i++) {
            BdExecuteMethod modifyObj = findBdExecuteMethodById(modifyKeys[i]);
            if (modifyObj != null) {
                modifyObj.setIsStop(startOrStopFlag);
                modifyObj.setModifiedtime(nowTime);

                modifyObjList.add(modifyObj);
            }
        }
        return modifyObjList;
    }


}
