package com.viewhigh.excel.validator;

import com.viewhigh.dao.IReCostDao;
import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.entity.BdDept;
import com.viewhigh.excel.domain.entity.ReCost;
import com.viewhigh.excel.utils.DeptValidateUtil;
import com.viewhigh.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.executable.ExecutableValidator;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class ReCostValidator extends LocalValidatorFactoryBean implements
        Validator {

    @Autowired
    private DeptValidateUtil deptValidateUtil;

    @Override
    public void validate(Object target, Errors errors) {
        BaseVO vo = (BaseVO) target;
        String className = vo.getClassz();
        List< BdDept>   bdDeptList = (List< BdDept>)vo.getDictionaries().get("BdDept");
        ReCost rc = (ReCost) vo.getObj();
        if(!Strings.checkYear(rc.getAccYear().intValue())) {
            errors.rejectValue("year", "recost.year.fomart", "年份字段格式错误！");
        }else {
            if (rc.getDeptLevel().intValue() == 1 && (addDept(bdDeptList, rc)) ) {
                    errors.rejectValue("pkDept", "recost.pkDept.required", "科室字典插入失败,科室编码或科室名称重复！");
            }else{
                if(checkAmountSum(rc)){
                    errors.rejectValue("deptAmountSum", "recost.deptAmountSum.error", "科室合计列计算错误！");
                }else{
                    rc.setCreator("zhaoxizi");
                    rc.setTs(new Date());
                }

            }

        }
    }

    public Boolean checkAmountSum(ReCost rc ){
        Double b=rc.getAssetsAmount()+rc.getOtherAmount()+rc.getRiskFundsAmount()+rc.getIntangibleAmount()+rc.getWageAmount()
                +rc.getDrugAmount()+rc.getMaterialsAmount();
        if(Math.abs(b-rc.getDeptAmountSum().doubleValue())<0.02){
            rc.setDeptAmountSum(b);
            return false;
        }else{
            return true;
        }

   }

  public Boolean  addDept( List< BdDept>   bdDeptList, ReCost rc ){
      Boolean flag=true;
      BdDept bd=new BdDept();
      try {
          //成本科室ID
          String costDeptId = deptValidateUtil.validate(bdDeptList,rc.getDeptCode(),rc.getDeptName(), DeptValidateUtil.CBCS);
          rc.setPkDept(costDeptId);
      } catch (Exception e) {
          e.printStackTrace();
          return true;

      }
      return false;
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
