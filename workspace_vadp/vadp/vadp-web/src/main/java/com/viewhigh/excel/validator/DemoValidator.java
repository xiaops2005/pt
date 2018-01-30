package com.viewhigh.excel.validator;

import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.Demo;
import com.viewhigh.excel.utils.DeptValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;

import java.util.List;
import java.util.Map;


@Service
public class DemoValidator extends LocalValidatorFactoryBean implements
        Validator {


    private Map<String, BdDept> bdDeptMaps = null;

    @Autowired
    private DeptValidateUtil deptValidateUtil;


    @Override
    public void validate(Object target, Errors errors) {
        // TODO Auto-generated method stub
        //super.validate(target, errors);
        BaseVO vo = (BaseVO) target;
        String className = vo.getClassz();
//        bdDeptMaps = vo.getBdDeptMap();
        List<BdDept>   bdDeptList = (List<BdDept>)vo.getDictionaries().get("BdDept");
        try {
            Object obj = vo.getObj();
            Demo demo = (Demo) obj;
            if (!StringUtils.hasText(demo.getDept())) {
                errors.rejectValue("dept", "demo.dept.required", "开单科室编码不能为空！");
            }
            if (StringUtils.hasText(demo.getDept()) && demo.getDept().length() > 32) {
                errors.rejectValue("dept", "demo.dept.tooLong", "开单科室编码超出最大长度32！");
            }
            if (StringUtils.hasText(demo.getDeptName()) && demo.getDeptName().length() > 100) {
                errors.rejectValue("deptName", "demo.deptName.tooLong", "开单科室名称超出最大长度100！");
            }
            if (StringUtils.hasText(demo.getExecDeptCode()) && demo.getExecDeptCode().length() > 32) {
                errors.rejectValue("execDeptCode", "demo.execDeptCode.tooLong", "执行科室编码超出最大长度32！");
            }
            if (StringUtils.hasText(demo.getExecDeptName()) && demo.getExecDeptName().length() > 100) {
                errors.rejectValue("execDeptName", "demo.execDeptName.tooLong", "执行科室名称超出最大长度100！");
            }
            if (StringUtils.hasText(demo.getCostDept()) && demo.getCostDept().length() > 32) {
                errors.rejectValue("costDept", "demo.costDept.tooLong", "成本科室超出最大长度32！");
            }
            if (StringUtils.hasText(demo.getCostItemCode()) && demo.getCostItemCode().length() > 32) {
                errors.rejectValue("costItemCode", "demo.costItemCode.tooLong", "收费项目编码超出最大长度32！");
            }
            if (StringUtils.hasText(demo.getCostItemName()) && demo.getCostItemName().length() > 100) {
                errors.rejectValue("costItemName", "demo.costItemName.tooLong", "收费项目名称超出最大长度100！");
            }
            if (StringUtils.hasText(demo.getItemType()) && demo.getItemType().length() > 100) {
                errors.rejectValue("itemType", "demo.itemType.tooLong", "明细分类超出最大长度100！");
            }
            if (StringUtils.hasText(demo.getExecutor()) && demo.getExecutor().length() > 100) {
                errors.rejectValue("executor", "demo.executor.tooLong", "执行人超出最大长度100！");
            }
            if (StringUtils.hasText(demo.getItemSuper()) && demo.getItemSuper().length() > 100) {
                errors.rejectValue("demo", "demo.itemSuper.tooLong", "项目大类超出最大长度100！");
            }

            deptValidateUtil.validate(bdDeptList,demo.getDept(),demo.getDeptName(),DeptValidateUtil.CBCS);
        } catch (Exception e) {
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
