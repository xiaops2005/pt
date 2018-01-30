package com.viewhigh.excel.utils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.vadp.framework.util.UUIDGenerater;

@Component
public class MaterialsValidateUtil {

//
//    /**
//     * 获取材料ID
//     * @param bdMaterialMaps	材料MAP
//     * @param materialsCode		材料编码
//     * @param materialsName		材料名称
//     * @param unit				计量单位
//     * @param model				规格型号
//     * @param unitPrice			单价
//     * @param isChargesId		是否单收费
//     * @return
//     */
//    public String validate(Map<String,BdMaterials> bdMaterialMaps, String materialsCode, String materialsName,String unit,String model,BigDecimal unitPrice,String isChargesId){
//        String id = null;
//    	if (!bdMaterialMaps.containsKey(materialsCode)) {
//    		BdMaterials b = new BdMaterials();
//            id = UUIDGenerater.generateId();
//            b.setMaterialsName(materialsName);
//            b.setMaterialsCode(materialsCode);
//            b.setPkMaterials(id);
//            b.setUnitName(unit);//计量单位
//            b.setModel(model);//规格型号
//            b.setUnitPrice(unitPrice);//单价
//            b.setIsSingleId(isChargesId);//是否单收费
//            bdMaterialMaps.put(materialsCode, b);
//        }else{
//        	BdMaterials bdMaterials = bdMaterialMaps.get(materialsCode);
//        	id = bdMaterials.getPkMaterials();
//        }
//    	return id;
//    }
	
	/**
	 * 判断材料字典编码是否存在，如果不存在则创建材料字典并返回ID，如果存在则直接返回ID
     * @param bdDeptList
     * @param deptCode
     * @param deptName
     * @param belongSystem
     */
    public String validate(List<BdMaterials> bdMaterialsList, String materialsCode, String materialsName,String unit,String model,BigDecimal unitPrice,String isCharges,String pkHospital){
        for(BdMaterials bdMaterials : bdMaterialsList){
        	if(StringUtils.equals(bdMaterials.getMaterialsCode(), materialsCode)){
        		return bdMaterials.getPkMaterials();
        	}
        }
        
		BdMaterials b = new BdMaterials();
		String id = UUIDGenerater.generateId();
		b.setMaterialsName(materialsName);
		b.setMaterialsCode(materialsCode);
		b.setPkMaterials(id);
		b.setUnitName(unit);// 计量单位
		b.setModel(model);// 规格型号
		b.setUnitPrice(unitPrice);// 单价
		b.setIsSingle(isCharges);// 是否单收费
		b.setPkHospital(pkHospital);
		bdMaterialsList.add(b);
		return id;
    }
}
