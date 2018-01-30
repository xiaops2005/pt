package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.entity.BdChargesHos;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.vadp.framework.util.UUIDGenerater;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 */
@Component
public class ChargesHosValidateUtil {


	/**
     * @param bdChargesHosMaps
     * @param validateId
     * @param deptName
     * @param deptCode
     */
    public String validate(List<BdChargesHos> l , String chargesHosCode, String chargesHosName){
        for(BdChargesHos b : l){
        	if(StringUtils.equals(b.getChargesCode(), chargesHosCode)){
        		return b.getPkChargeClass();
        	}
        }
        BdChargesHos b= new BdChargesHos();
        String id = UUIDGenerater.generateId();
	    b.setChargesName(chargesHosName);;
	    b.setChargesCode(chargesHosCode);
	    b.setPkChargesHos(id);
	    l.add(b);
	    return id;
    }
}
