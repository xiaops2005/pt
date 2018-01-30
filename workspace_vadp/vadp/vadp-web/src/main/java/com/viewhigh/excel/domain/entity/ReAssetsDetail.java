package com.viewhigh.excel.domain.entity;


import com.viewhigh.excel.domain.Base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "re_assets_detail")
public class ReAssetsDetail extends Base {

    @Id
    @Column(name = "pk_assets_detail")
    private String pkAssetsDetail;
    @Column(name = "status_id")
    private String statusId;
    @Column(name = "is_inherit_id")
    private String isInheritId;
    @Column(name = "is_public_id")
    private String isPublicId;
    @Column(name = "pk_hospital")
    private String pkHospital;
    @Column(name = "acc_year")
    private BigDecimal accYear;
    @Column(name = "pk_dept")
    private String pkDept;
    @Column(name = "pk_assets")
    private String pkAssets;
    @Column(name = "depreciation")
    private Double depreciation;
    @Column(name = "capital_source_id")
    private String capitalSourceId;
    @Column(name = "ratio")
    private Double ratio;
    @Column(name = "is_bunkfee_id")
    private String isBunkfeeId;
    @Column(name = "pk_dept_merge")
    private String pkDeptMerge;
    @Column(name = "pk_dept_cost")
    private String pkDeptCost;
    @Column(name = "pk_charge_class")
    private String pkChargeClass;
    @Column(name = "creator")
    private String creator;
    @Column(name = "creationtime")
    private Date creationtime;
    @Column(name = "dr")
    private BigDecimal dr;
    @Column(name = "ts")
    private Date ts;

    public String getIsInheritId() {
        return isInheritId;
    }

    public void setIsInheritId(String isInheritId) {
        this.isInheritId = isInheritId;
    }

    public String getIsPublicId() {
        return isPublicId;
    }

    public void setIsPublicId(String isPublicId) {
        this.isPublicId = isPublicId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getPkAssetsDetail() {
        return pkAssetsDetail;
    }

    public void setPkAssetsDetail(String pkAssetsDetail) {
        this.pkAssetsDetail = pkAssetsDetail;
    }

    public String getPkHospital() {
        return pkHospital;
    }

    public void setPkHospital(String pkHospital) {
        this.pkHospital = pkHospital;
    }

    public BigDecimal getAccYear() {
        return accYear;
    }

    public void setAccYear(BigDecimal accYear) {
        this.accYear = accYear;
    }

    public String getPkDept() {
        return pkDept;
    }

    public void setPkDept(String pkDept) {
        this.pkDept = pkDept;
    }

    public String getPkAssets() {
        return pkAssets;
    }

    public void setPkAssets(String pkAssets) {
        this.pkAssets = pkAssets;
    }

    public Double getDepreciation() {
        return depreciation;
    }

    public void setDepreciation(Double depreciation) {
        this.depreciation = depreciation;
    }

    public String getCapitalSourceId() {
        return capitalSourceId;
    }

    public void setCapitalSourceId(String capitalSourceId) {
        this.capitalSourceId = capitalSourceId;
    }

    public Double getRatio() {
        return ratio;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public String getIsBunkfeeId() {
        return isBunkfeeId;
    }

    public void setIsBunkfeeId(String isBunkfeeId) {
        this.isBunkfeeId = isBunkfeeId;
    }

    public String getPkDeptMerge() {
        return pkDeptMerge;
    }

    public void setPkDeptMerge(String pkDeptMerge) {
        this.pkDeptMerge = pkDeptMerge;
    }

    public String getPkDeptCost() {
        return pkDeptCost;
    }

    public void setPkDeptCost(String pkDeptCost) {
        this.pkDeptCost = pkDeptCost;
    }

    public String getPkChargeClass() {
        return pkChargeClass;
    }

    public void setPkChargeClass(String pkChargeClass) {
        this.pkChargeClass = pkChargeClass;
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
