package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.vadp.framework.util.UUIDGenerater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by litianzhu on 2017/12/28.
 */
@Component
public class DeptValidateUtil {

	/**
	 * 收入科室
	 */
    public static final String HIS = "01";
    /**
     * 材料科室
     */
    public static final String MATE = "02";
    /**
     * 资产科室
     */
    public static final String EQUI = "03";
    /**
     * 成本科室
     */
    public static final String CBCS = "04";

//	/**
//     *  BelongSystem = 3为成本科室
//     * @param bdDeptMaps
//     * @param validateId
//     * @param deptName
//     * @param deptCode
//     */
//    public String validate(Map<String,BdDept> bdDeptMaps, String deptCode, String deptName, String belongSystem){
//        String id = null;
//    	if (!bdDeptMaps.containsKey(deptCode)) {
//            BdDept b = new BdDept();
//            id = UUIDGenerater.generateId();
//            b.setDeptName(deptName);
//            b.setDeptCode(deptCode);
//            b.setPkDept(id);
//            b.setBelongSystem(belongSystem);
//            bdDeptMaps.put(deptCode, b);
//        }else{
//        	BdDept bdDept = bdDeptMaps.get(deptCode);
//        	id = bdDept.getPkDept();
//        }
//        return id;
//    }
    
	/**
	 * 判断科室字典编码是否存在，如果不存在则创建科室字典并返回ID，如果存在则直接返回ID
     * @param bdDeptList
     * @param deptCode
     * @param deptName
     * @param belongSystem
     */
    public String validate(List<BdDept> bdDeptList, String deptCode, String deptName, String belongSystem){
        for(BdDept bdDept : bdDeptList){
        	if(StringUtils.equals(bdDept.getDeptCode(), deptCode)){
        		return bdDept.getPkDept();
        	}
        }
        
        BdDept b = new BdDept();
        String id = UUIDGenerater.generateId();
        b.setDeptName(deptName);
        b.setDeptCode(deptCode);
        b.setPkDept(id);
        b.setBelongSystemId(belongSystem);
        bdDeptList.add(b);
        return id;
    }
}
