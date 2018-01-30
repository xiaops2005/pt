package com.viewhigh.excel.domain;


import java.util.List;
import java.util.Map;

import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;

/**
 * Created by litianzhu on 2017/12/28.
 */
public class BaseVO {
    private String classz;

    private Map<String,BdDept> bdDeptMap;
    
    private Map<String,BdMaterials> BdMaterialsMap;

    public Map<String, List<?>> dictionaries;

    public Map<String, List<?>> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Map<String, List<?>> dictionaries) {
        this.dictionaries = dictionaries;
    }

    Class<? extends Base> clazz;

    Object obj;

    public String getClassz() {
        return classz;
    }

    public void setClassz(String classz) {
        this.classz = classz;
    }

    public Map<String, BdDept> getBdDeptMap() {
        return bdDeptMap;
    }

    public void setBdDeptMap(Map<String, BdDept> bdDeptMap) {
        this.bdDeptMap = bdDeptMap;
    }

    public Map<String, BdMaterials> getBdMaterialsMap() {
		return BdMaterialsMap;
	}

	public void setBdMaterialsMap(Map<String, BdMaterials> bdMaterialsMap) {
		BdMaterialsMap = bdMaterialsMap;
	}

	public Class<? extends Base> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Base> clazz) {
        this.clazz = clazz;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
