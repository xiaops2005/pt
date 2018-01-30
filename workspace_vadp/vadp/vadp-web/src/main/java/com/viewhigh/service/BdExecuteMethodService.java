package com.viewhigh.service;

import com.viewhigh.entity.BdExecuteMethod;

import java.util.List;

/**
 * 执行方法 -- Server 接口
 * Created by PeiKai on 2018/1/9.
 */
public interface BdExecuteMethodService {

    /**
     * 查询 执行方法
     *
     * @param hospital  -- 医院ID
     * @param methodCode -- 方法编码
     * @param methodName -- 方法名称
     * @param stopFlag -- 是否停用
     * @return  List\<BdExecuteMethod\>
     */
    List<BdExecuteMethod> getExecuteMethodList(String hospital, String methodCode, String methodName, Integer stopFlag);

    /**
     * 保存 执行方法
     *
     * @param saveData  -- 保存的执行方法
     * @return 更新对象
     */
    BdExecuteMethod saveExecuteMethod(BdExecuteMethod saveData);

    /**
     * 保存 执行方法 (批量保存)
     * @return 查询对象
     */
    String saveExecuteMethodList(List<BdExecuteMethod> saveList);

    /**
     * 删除 执行方法
     *
     * @param delKeys   -- 执行方法主键
     */
    String delExecuteMethodList(String delKeys);

    /**
     * 启用、停用 执行方法
     *
     * @param modifyKeysStr -- 选定的执行方法的主键
     * @param startOrStopFlag -- 启用、停用 标识
     */
    String startOrStopExecuteMethodList(String modifyKeysStr, Integer startOrStopFlag);

    /**
     * 校验字段
     *
     * @param methodList
     * @return
     */
    String checkoutSaveObjList (List<BdExecuteMethod> methodList);
}
