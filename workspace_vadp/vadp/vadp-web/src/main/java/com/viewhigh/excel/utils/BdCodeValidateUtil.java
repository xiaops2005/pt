package com.viewhigh.excel.utils;

import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.vadp.framework.util.UUIDGenerater;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by litianzhu on 2017/12/28.
 */
@Component
public class BdCodeValidateUtil {

    public String validate(List<BdCode> bdCodeList, String codeName){
        for(BdCode bdCode : bdCodeList){
        	if(StringUtils.equals(bdCode.getCodeName(),codeName)){
        		return bdCode.getPkCode();
        	}
        }
        return null;
    }
    
    public String validate(List<BdCode> bdCodeList, String codetypeId, String codeName){
        for(BdCode bdCode : bdCodeList){
        	if(StringUtils.equals(bdCode.getCodetypeId(),codetypeId) && StringUtils.equals(bdCode.getCodeName(),codeName)){
        		return bdCode.getPkCode();
        	}
        }
        return null;
    }
}
