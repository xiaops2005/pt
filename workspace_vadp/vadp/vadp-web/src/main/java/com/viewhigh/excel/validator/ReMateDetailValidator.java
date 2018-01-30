package com.viewhigh.excel.validator;

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
import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.BdMaterials;
import com.viewhigh.excel.utils.BdCodeValidateUtil;
import com.viewhigh.excel.utils.DeptValidateUtil;
import com.viewhigh.excel.utils.MaterialsValidateUtil;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

@Component
public class ReMateDetailValidator extends LocalValidatorFactoryBean implements
		Validator {

//	private Map<String, BdDept> bdDeptMaps = null;
//	
//	private Map<String, BdMaterials> bdMaterialsMaps = null;
	
	@Autowired
    private DeptValidateUtil deptValidateUtil;
	
	@Autowired
	private MaterialsValidateUtil materialsValidateUtil;
	
	@Autowired
	private BdCodeValidateUtil bdCodeValidateUtil;
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ReMateDetailValidator.class.equals(clazz);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void validate(Object target, Errors errors) {
		BaseVO vo = (BaseVO) target;
        
        List<BdDept> bdDeptList = (List<BdDept>)vo.getDictionaries().get("BdDept");
        List<BdMaterials> bdMaterialsList = (List<BdMaterials>)vo.getDictionaries().get("BdMaterials");
        List<BdCode> bdCodeList = (List<BdCode>)vo.getDictionaries().get("BdCode");
        try{
        	Object obj = vo.getObj();
        	ReMateDetail reMateDetail = (ReMateDetail) obj;
            
    		String[] str={"是","否"};
            List<String> asList = Arrays.asList(str);
            if(!asList.contains(reMateDetail.getIsChargesId())){
            	errors.rejectValue("isChargesId", "invalid.enum.value", "是否单收费的值错误");
            }
            
    		//成本科室ID
    		String costDeptId = deptValidateUtil.validate(bdDeptList,reMateDetail.getCostDeptCode(),reMateDetail.getCostDeptName(),DeptValidateUtil.CBCS);
    		reMateDetail.setPkDeptCost(costDeptId);
    		//材料科室ID
    		String deptId = deptValidateUtil.validate(bdDeptList,reMateDetail.getMaterialsDeptCode(),reMateDetail.getMaterialsDeptName(),DeptValidateUtil.MATE);
    		reMateDetail.setPkDept(deptId);
    		//材料ID
    		String materialsId = materialsValidateUtil.validate(bdMaterialsList,reMateDetail.getMaterialsCode(),reMateDetail.getMaterialsName(),reMateDetail.getUnit(),reMateDetail.getModel(),reMateDetail.getUnitPrice(),reMateDetail.getIsChargesId(),reMateDetail.getPkHospital());
    		reMateDetail.setPkMaterials(materialsId);
    		//是否单收费
    		String isChargesId = bdCodeValidateUtil.validate(bdCodeList, "IS_SINGLE", reMateDetail.getIsChargesId());
            reMateDetail.setIsChargesId(isChargesId);
            //单据状态
            String statusId = bdCodeValidateUtil.validate(bdCodeList, "STATUS", reMateDetail.getStatusId());
            reMateDetail.setStatusId(statusId);
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
