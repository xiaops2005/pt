package com.viewhigh.excel.validator;

import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.IncomeDetailBean;
import com.viewhigh.excel.domain.entity.BdChargesHos;
import com.viewhigh.excel.domain.entity.BdCode;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.utils.BdCodeValidateUtil;
import com.viewhigh.excel.utils.ChargesHosValidateUtil;
import com.viewhigh.excel.utils.DeptValidateUtil;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.List;
import java.util.Map;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;


@Component
public class IncomeDetailBeanValidator extends LocalValidatorFactoryBean implements
		Validator {


    @Autowired
    private DeptValidateUtil deptValidateUtil;
    @Autowired
    private BdCodeValidateUtil bdCodeValidateUtil;
    @Autowired
    private ChargesHosValidateUtil chargesHosValidateUtil;
    
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return IncomeDetailBeanValidator.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		//super.validate(target, errors);
		BaseVO vo = (BaseVO) target;
        String className = vo.getClassz();
        List<BdDept> l1 = (List<BdDept>) vo.getDictionaries().get("BdDept");
        List<BdChargesHos> l2 = (List<BdChargesHos>) vo.getDictionaries().get("BdChargesHos");
        List<BdCode> l3 = (List<BdCode>) vo.getDictionaries().get("BdCode");
        Object obj = vo.getObj();
		IncomeDetailBean detail = (IncomeDetailBean) obj;
		if (!StringUtils.hasText(String.valueOf(detail.getAccYear()))) {
			errors.rejectValue("accYear", "detail.accYear.required", "年度不能为空！");
		}
		if (!String.valueOf(detail.getAccYear()).matches("\\d{4}")) {
			errors.rejectValue("accYear", "detail.accYear.numeric", "年度必须为4位整数！");
		}
		if (!StringUtils.hasText(detail.getIncomeType())) {
			errors.rejectValue("incomeType", "detail.incomeType.required", "收入类型不能为空！");
		}
		if (!StringUtils.hasText(detail.getPkDeptStartCode())) {
			errors.rejectValue("pkDeptStartCode", "detail.pkDeptStartCode.required", "开单科室编码不能为空！");
		}
		if (!StringUtils.hasText(detail.getPkDeptName())) {
			errors.rejectValue("pkDeptName", "detail.pkDeptName.required", "开单科室名称不能为空！");
		}	
		if (!StringUtils.hasText(detail.getPkChargesHosCode())) {
			errors.rejectValue("pkChargesHosCode", "detail.pkChargesHosCode.required", "收费项目编码不能为空！");
		}
		if (!StringUtils.hasText(detail.getPkChargesHosName())) {
			errors.rejectValue("pkChargesHosName", "detail.pkChargesHosName.required", "收费项目名称不能为空！");
		}
		if (!StringUtils.hasText(detail.getChargesType())) {
			errors.rejectValue("chargesType", "detail.chargesType.required", "明细分类不能为空！");
		}
		if (!StringUtils.hasText(detail.getAmount())) {
			errors.rejectValue("amount", "detail.amount.required", "金额不能为空！");
		}
		if (!NumberUtils.isNumber(detail.getAmount())) {
			errors.rejectValue("amount", "detail.amount.numeric", "金额必须为数字类型");
		}
		if (!StringUtils.hasText(detail.getUnitPrice())) {
			errors.rejectValue("unitPrice", "detail.unitPrice.required", "单价不能为空！");
		}
		if (!NumberUtils.isNumber(detail.getUnitPrice())) {
			errors.rejectValue("unitPrice", "detail.unitPrice.numeric", "单价必须为数字类型");
		}
		if (!StringUtils.hasText(detail.getQuantity())) {
			errors.rejectValue("quantity", "detail.quantity.required", "数量不能为空！");
		}
		if (!NumberUtils.isNumber(detail.getQuantity())) {
			errors.rejectValue("quantity", "detail.quantity.numeric", "数量必须为数字类型");
		}
		//收入类型
		String IncomeTypeID = bdCodeValidateUtil.validate(l3, detail.getIncomeType());
		detail.setIncomeTypeId(IncomeTypeID);
		//医护标识
		String medicalYdentify = bdCodeValidateUtil.validate(l3, detail.getMedicalIdentify());
		detail.setMedicalIdentifyId(medicalYdentify);
		//开单科室字典
		String deptStartId = deptValidateUtil.validate(l1,detail.getPkDeptStartCode(),detail.getPkDeptName(),deptValidateUtil.HIS);
		detail.setPkDeptStart(deptStartId);
		//执行科室
		String deptExecute = deptValidateUtil.validate(l1, detail.getPkDeptExecuteCode(), detail.getPkDeptExecuteName(),deptValidateUtil.HIS);
		detail.setPkDeptExecute(deptExecute);
		//成本科室
		String deptCost = deptValidateUtil.validate(l1, detail.getPkDeptCostCode(), detail.getPkDeptCostName(),deptValidateUtil.CBCS);
		detail.setPkDeptCost(deptCost);
		//收费项目
		String chargesHos = chargesHosValidateUtil.validate(l2, detail.getPkChargesHosCode(), detail.getPkChargesHosName());
		detail.setPkChargesHos(chargesHos);
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
