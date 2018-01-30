package com.viewhigh.excel.validator;

import com.viewhigh.entity.BdAssets;
import com.viewhigh.excel.domain.BaseVO;
import com.viewhigh.excel.domain.entity.*;
import com.viewhigh.excel.utils.BdAssetsValidateUtil;
import com.viewhigh.excel.utils.BdCodeValidateUtil;
import com.viewhigh.excel.utils.DeptValidateUtil;
import com.viewhigh.util.Strings;
import com.viewhigh.vadp.framework.util.UUIDGenerater;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ParameterNameProvider;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.util.Date;
import java.util.List;

/**
 * @Author zhaoxizi
 * @Date 18/1/8下午4:48
 */
@Service
public class ReAssetsOriginalValidator extends LocalValidatorFactoryBean implements
        Validator {

    @Autowired
    private DeptValidateUtil deptValidateUtil;
    @Autowired
    private BdAssetsValidateUtil bdAssetsValidateUtil;
    @Autowired
    private BdCodeValidateUtil bdCodeValidateUtil;

    @Override
    public void validate(Object target, Errors errors) {
        BaseVO vo = (BaseVO) target;
        String className = vo.getClassz();
        List< BdDept> bdDeptList = (List< BdDept>)vo.getDictionaries().get("BdDept");
        List<BdCode> bdCodeList = (List<BdCode>)vo.getDictionaries().get("BdCode");
        List<BdAssets> bdAssetsList = (List< BdAssets>)vo.getDictionaries().get("BdAssets");
        List<ReAssetsDetail> rdList = (List<ReAssetsDetail>)vo.getDictionaries().get("ReAssetsDetail");

        ReAssetsOriginal rao = (ReAssetsOriginal) vo.getObj();
        if(!Strings.checkYear(rao.getAccYear().intValue())) {//年份
            errors.rejectValue("accYear", "ReAssetsOriginal.year.fomart", "年份字段不是数字格式！");
        }else if(!Strings.checkYear(rao.getAccMonth().intValue())) {//月份
            errors.rejectValue("accMonth", "ReAssetsOriginal.month.fomart", "月份字段不是数字格式！");
        }else if (addDept(bdDeptList, rao) ) {//资产科室字典
                errors.rejectValue("pkDept", "ReAssetsOriginal.deptPk.error", "资产科室字典插入失败,科室编码或科室名称重复！");
        }else if (addBdAssets(bdAssetsList, rao) ) {//资产字典
                errors.rejectValue("pkAssets", "ReAssetsOriginal.pkAssets.error", "资产字典插入失败,编码或科室名称重复！");
        }else{
            //单据状态,默认新建
            String statusId = bdCodeValidateUtil.validate(bdCodeList, "STATUS", "新建");
            rao.setStatusId(statusId);
            //是否床位费
            String isBunkfeeId = bdCodeValidateUtil.validate(bdCodeList, "IS_BUNKFEE", rao.getIsBunkfeeId());
            rao.setIsBunkfeeId(isBunkfeeId);
            //资金来源
            String capitalSourceId = bdCodeValidateUtil.validate(bdCodeList, "CAPITAL_SOURCE", rao.getCapitalSourceId());
            rao.setCapitalSourceId(capitalSourceId);
            rao.setCreator("zhaoxizi");
            rao.setTs(new Date());
            //汇总折旧金额
            countDepreciation(rdList,rao);
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

    /**
    * @Author zhaoxizi
    * @Date 18/1/10
    * @param rdList, rao
    * @return void
     * 根据 医院+年+科室id+资产id+资金来源+自筹比例+是否床位费 汇总折旧金额
    */
    public void  countDepreciation( List< ReAssetsDetail> rdList ,ReAssetsOriginal rao ){
        Boolean falg=true;
        ReAssetsDetail oldRd=new ReAssetsDetail();
        for (ReAssetsDetail rd:rdList
             ) {
                if(rd.getAccYear().compareTo(rao.getAccYear())==0
                        &&rd.getRatio().compareTo(rao.getRatio())==0
                        && StringUtils.equals(rd.getPkHospital(),rao.getPkHospital())
                        &&StringUtils.equals(rd.getPkDept(),rao.getPkDept())
                        &&StringUtils.equals(rd.getPkAssets(),rao.getPkAssets())
                        && StringUtils.equals(rd.getCapitalSourceId(),rao.getCapitalSourceId())
                        &&StringUtils.equals(rd.getIsBunkfeeId(),rao.getIsBunkfeeId())
                        ) {
                    falg = false;
                    oldRd = rd;
                    break;
                }
        }
        if(falg){
            String id = UUIDGenerater.generateId();
            ReAssetsDetail newRd=new ReAssetsDetail();
            newRd.setAccYear(rao.getAccYear());
            newRd.setPkHospital(rao.getPkHospital());
            newRd.setPkDept(rao.getPkDept());
            newRd.setCapitalSourceId(rao.getCapitalSourceId());
            newRd.setRatio(rao.getRatio());
            newRd.setIsBunkfeeId(rao.getIsBunkfeeId());
            newRd.setPkAssetsDetail(id);
            newRd.setPkAssets(rao.getPkAssets());
            newRd.setDepreciation(0.0);
            rdList.add(newRd);
        }else{
            oldRd.setDepreciation(rao.getDepreciation()+oldRd.getDepreciation());

        }

    }

    public Boolean  addDept( List< BdDept>   bdDeptList, ReAssetsOriginal rao ){
        Boolean flag=true;
        BdDept bd=new BdDept();
        try {
            //资产科室ID
            String equiDeptId = deptValidateUtil.validate(bdDeptList,rao.getEQUIDeptCode(),rao.getEQUIDeptName(), DeptValidateUtil.EQUI);
            rao.setPkDept(equiDeptId);
        } catch (Exception e) {
            e.printStackTrace();
            return true;

        }
        return false;
    }
    public Boolean  addBdAssets( List< BdAssets>   bdAssetsList, ReAssetsOriginal rao ){
        Boolean flag=true;
        BdDept bd=new BdDept();
        try {
            //资产ID
            String pkAssets = bdAssetsValidateUtil.validate(bdAssetsList,rao);
            if(pkAssets==null){
                String a="";
            }
            rao.setPkAssets(pkAssets);
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
