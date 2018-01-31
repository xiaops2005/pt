package com.viewhigh.service.impl;

import com.google.common.base.Strings;
import com.viewhigh.dao.BdExecuteMethodDAO;
import com.viewhigh.entity.BdExecuteMethod;
import com.viewhigh.service.BdExecuteMethodService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 执行方法 -- Server 实现
 *
 * Created by PeiKai on 2018/1/9.
 */
@Service("BdExecuteMethodService")
public class BdExecuteMethodServiceImpl implements BdExecuteMethodService {

    @Autowired
    private BdExecuteMethodDAO bdExecuteMethodDAO;

    @Override
    // 查询 执行方法
    public List<BdExecuteMethod> getExecuteMethodList(String hospital, String methodCode, String methodName, Integer stopFlag) {

        return bdExecuteMethodDAO.getExecuteMethodList(hospital, methodCode, methodName, stopFlag);
    }

    @Override
    public BdExecuteMethod saveExecuteMethod(BdExecuteMethod saveData) {
        if (Strings.isNullOrEmpty(saveData.getPkExecuteMethod())) {
            // 保存新增对象，设置 ID
            BdExecuteMethod insertObj = new BdExecuteMethod();
            BeanUtils.copyProperties(saveData, insertObj);

            return bdExecuteMethodDAO.saveExecuteMethod(insertObj);
        }
        else {
            // 保存修改对象
            return bdExecuteMethodDAO.modifyExecuteMethod(saveData);
        }
    }

    @Override
    public String saveExecuteMethodList(List<BdExecuteMethod> saveList) {
        bdExecuteMethodDAO.saveExecuteMethodList(saveList);
        return "OK";
    }

    // 删除 执行方法
    @Override
    public String delExecuteMethodList(String delKeys) {
        String[] delKeysArr = delKeys.split(",");
        bdExecuteMethodDAO.delExecuteMethodList(delKeysArr);
        return "OK";
    }

    // 启用、停用 执行方法
    @Override
    public String startOrStopExecuteMethodList(String modifyKeysStr, Integer startOrStopFlag){
        if (Strings.isNullOrEmpty(modifyKeysStr)) {
            System.out.println("启用/停用操作的对象主键为 null ！！！");
            return null;
        }
        String[] modifyKeys = modifyKeysStr.split(",");
        bdExecuteMethodDAO.startOrStopExecuteMethodList(startOrStopFlag, modifyKeys);
        return "OK";
    }

    @Override
    public String checkoutSaveObjList (List<BdExecuteMethod> methodList) {
        StringBuffer checkoutInfo = new StringBuffer();

        return checkoutInfo.toString();
    }

    // 检验对象属性是否为空
    private String checkoutObj (BdExecuteMethod bdExecuteMethod) {
        String checkoutReturn = "";
        if (Strings.isNullOrEmpty(bdExecuteMethod.getPkHospital())) {
            checkoutReturn += "所属医院为空;";
        }
        if (Strings.isNullOrEmpty(bdExecuteMethod.getMethodCode())) {
            checkoutReturn += "方法编码为空;";
        }
        if (Strings.isNullOrEmpty(bdExecuteMethod.getMethodName())) {
            checkoutReturn += "方法名称为空;";
        }
        if (bdExecuteMethod.getIsStop() == null) {
            checkoutReturn += "是否停用为空;";
        }

        return checkoutReturn;
    }
}
