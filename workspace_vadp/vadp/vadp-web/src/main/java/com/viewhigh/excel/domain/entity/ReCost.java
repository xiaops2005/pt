package com.viewhigh.excel.domain.entity;

import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "re_cost")
public class ReCost  extends Base {



    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name="pk_cost")
    private String pkCost;
    @Column(name="pk_hospital")
    private String pkHospital;
    @Column(name="acc_year")
    private BigDecimal accYear;

    @Column(name="acc_month")
    private BigDecimal accMonth;

    @Column(name="pk_dept")
    private String pkDept;

    private String deptCode;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    private String deptName;

    @Column(name="dept_level")
    private BigDecimal deptLevel;
    @Column(name="wage_amount")
    private Double wageAmount;
    @Column(name="materials_amount")
    private Double materialsAmount;
    @Column(name="drug_amount")
    private Double drugAmount;
    @Column(name="assets_amount")
    private Double assetsAmount;
    @Column(name="intangible_amount")
    private Double intangibleAmount;
    @Column(name="risk_funds_amount")
    private Double riskFundsAmount;
    @Column(name="other_amount")
    private Double otherAmount;
    @Column(name="dept_amount_sum")
    private Double deptAmountSum;
    @Column(name="pk_dept_merge")
    private String pkDeptMerge;
    @Column(name="status_id")
    private String statusId;
    @Column(name="creator")
    private String creator;
    @Column(name="creationtime")
    private Date creationtime;
    @Column(name="modifier")
    private String modifier;
    @Column(name="modifiedtime")
    private Date modifiedtime;
    @Column(name="approver")
    private String approver;
    @Column(name="approvetime")
    private Date approvetime;
    @Column(name="approvenote")
    private String approvenote;
    @Column(name="dr")
    private BigDecimal dr;
    @Column(name="ts")
    private Date ts;

    public BigDecimal getAccYear() {
        return accYear;
    }

    public void setAccYear(BigDecimal accYear) {
        this.accYear = accYear;
    }

    public BigDecimal getAccMonth() {
        return accMonth;
    }

    public void setAccMonth(BigDecimal accMonth) {
        this.accMonth = accMonth;
    }

    public String getPkDept() {
        return pkDept;
    }

    public void setPkDept(String pkDept) {
        this.pkDept = pkDept;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifiedtime() {
        return modifiedtime;
    }

    public void setModifiedtime(Date modifiedtime) {
        this.modifiedtime = modifiedtime;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public void setApprovetime(Date approvetime) {
        this.approvetime = approvetime;
    }

    public String getApprovenote() {
        return approvenote;
    }

    public void setApprovenote(String approvenote) {
        this.approvenote = approvenote;
    }

    public Double getIntangibleAmount() {
        return intangibleAmount;
    }

    public void setIntangibleAmount(Double intangibleAmount) {
        this.intangibleAmount = intangibleAmount;
    }

    public String getPkCost() {
        return pkCost;
    }

    public void setPkCost(String pkCost) {
        this.pkCost = pkCost;
    }

    public String getPkHospital() {
        return pkHospital;
    }

    public void setPkHospital(String pkHospital) {
        this.pkHospital = pkHospital;
    }

    public BigDecimal getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(BigDecimal deptLevel) {
        this.deptLevel = deptLevel;
    }

    public Double getWageAmount() {
        return wageAmount;
    }

    public void setWageAmount(Double wageAmount) {
        this.wageAmount = wageAmount;
    }

    public Double getMaterialsAmount() {
        return materialsAmount;
    }

    public void setMaterialsAmount(Double materialsAmount) {
        this.materialsAmount = materialsAmount;
    }

    public Double getDrugAmount() {
        return drugAmount;
    }

    public void setDrugAmount(Double drugAmount) {
        this.drugAmount = drugAmount;
    }

    public Double getAssetsAmount() {
        return assetsAmount;
    }

    public void setAssetsAmount(Double assetsAmount) {
        this.assetsAmount = assetsAmount;
    }

    public Double getRiskFundsAmount() {
        return riskFundsAmount;
    }

    public void setRiskFundsAmount(Double riskFundsAmount) {
        this.riskFundsAmount = riskFundsAmount;
    }

    public Double getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(Double otherAmount) {
        this.otherAmount = otherAmount;
    }

    public Double getDeptAmountSum() {
        return deptAmountSum;
    }

    public void setDeptAmountSum(Double deptAmountSum) {
        this.deptAmountSum = deptAmountSum;
    }

    public String getPkDeptMerge() {
        return pkDeptMerge;
    }

    public void setPkDeptMerge(String pkDeptMerge) {
        this.pkDeptMerge = pkDeptMerge;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreationtime() {
        return creationtime;
    }

    public void setCreationtime(Date creationtime) {
        this.creationtime = creationtime;
    }

    public BigDecimal getDr() {
        return dr;
    }

    public void setDr(BigDecimal dr) {
        this.dr = dr;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }


}
