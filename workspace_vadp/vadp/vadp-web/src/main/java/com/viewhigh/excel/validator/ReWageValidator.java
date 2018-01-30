package com.viewhigh.excel.validator;

import java.math.BigDecimal;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.viewhigh.dao.BdCodeDao;
import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.ReMateDetail;
import com.viewhigh.excel.domain.ReWage;
import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.excel.utils.BdCodeValidateUtil;
import com.viewhigh.excel.utils.DeptValidateUtil;
import com.viewhigh.excel.utils.MaterialsValidateUtil;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

@Component
public class ReWageValidator extends LocalValidatorFactoryBean implements
		Validator {

//	private Map<String, BdDept> bdDeptMaps = null;
//	
//	private Map<String, BdMaterials> bdMaterialsMaps = null;
	
	@Autowired
    private DeptValidateUtil deptValidateUtil;
	
	@Autowired
	private BdCodeValidateUtil bdCodeValidateUtil;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ReWageValidator.class.equals(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(Object target, Errors errors) {
		BaseVO vo = (BaseVO) target;
        
        List<BdDept> bdDeptList = (List<BdDept>)vo.getDictionaries().get("BdDept");
        
        try{
	    	Object obj = vo.getObj();
	    	List<BdCode> bdCodeList = (List<BdCode>)vo.getDictionaries().get("BdCode");
	    	ReWage reWage = (ReWage) obj;
	    	
	    	if(reWage.getDoctorNum().compareTo(new BigDecimal(Math.abs(reWage.getDoctorNum().intValue())))!= 0){
	    		errors.rejectValue("doctorNum", "reWage.doctorNum.numberic", "医生人数必须为正整数！");
	    	}
	    	if(reWage.getNurseNum().compareTo(new BigDecimal(Math.abs(reWage.getNurseNum().intValue())))!= 0){
	    		errors.rejectValue("nurseNum", "reWage.nurseNum.numberic", "护士人数必须为正整数！");
	    	}
	    	
	    	BigDecimal amountSum = reWage.getDoctorWage().add(reWage.getNurseWage());
	    	reWage.setAmountSum(amountSum);
	    	
			//成本科室ID
			String costDeptId = deptValidateUtil.validate(bdDeptList,reWage.getCostDeptCode(),reWage.getCostDeptName(),DeptValidateUtil.CBCS);
			reWage.setPkDept(costDeptId);
			
			//单据状态
			String statusId = bdCodeValidateUtil.validate(bdCodeList, reWage.getStatusId());
			reWage.setStatusId(statusId);
        }catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public ExecutableValidator forExecutables() {
		return null;
	}

	@Override
	public ParameterNameProvider getParameterNameProvider() {
		return null;
	}
}
