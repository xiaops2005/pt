package com.viewhigh.dao;

import com.viewhigh.excel.domain.Base;

import java.util.List;
import java.util.Map;

/**
 * @Author zhaoxizi
 * @Date 18/1/2下午3:39
 */
public interface IAPIBaseDao {

    /**
     * 根据全类名获取字典list
    * @Author zhaoxizi
    * @Date 18/1/2
    * @return java.util.List<java.lang.Object>
    *
    */

    public List<?> getDictionariesByKey(String dictionaryName);

    public List<?>  getDictionariesByKey(String dictionaryName, Map<String, List<String>> searchCondition);

    /**
     * 插入单个字典表
    * @Author zhaoxizi
    * @Date 18/1/3
    * @param d
    * @return java.lang.Integer
    *
    */


    public <T extends Base> String saveDictionary(T d);

    /**
     * 根据全类名获取字典list
    * @Author zhaoxizi
    * @Date 18/1/2
    * @return java.util.Map<java.lang.String,java.util.List<java.lang.Object>>
    *
    */
    public Map<String, List<?>> getDictionariesByClass(List<String> dictionaryName) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
    /**
     * 根据全类名获取字典list
    * @Author zhaoxizi
    * @Date 18/1/2
    * @return java.util.Map<java.lang.String,java.util.List<java.lang.Object>>
    *
    */
    public Map<String, List<?>> getDictionariesByClass(Map<String, Map<String, List<String>>> dictionaries) throws ClassNotFoundException, IllegalAccessException, InstantiationException;

    /**
     * 保存字典list
    * @Author zhaoxizi
    * @Date 18/1/3
    * @param dictionarys
    * @return void
    *
    */
    public <T extends Base> void saveDictionaries(Map<String, List<?>> newDictionaries, Map<String, List<?>> OldDictionaries);

    /**
     * 插入字典表通用判断方法
     * @Author zhaoxizi
     * @Date 18/1/3
     * @return boolean
     *
     */
    public <T extends Base> boolean beforeInsertDictionaries(String name, T d, List<T> oldList);

}
