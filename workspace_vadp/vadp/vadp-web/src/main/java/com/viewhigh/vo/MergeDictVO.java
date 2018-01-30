package com.viewhigh.vo;

import java.util.Date;

/**
 * Created by litianzhu on 2018/1/10.
 */
public class MergeDictVO {

    private String pkDeptMerge;
    private String pkHospital;
    private String hospitalName;
    private String mergeCode;
    private String mergeName;
    private String pkDeptSta;
    private String remark;
    private String accYear;
    private String creator;
    private Date creationtime;
    private String modifier;
    private Date modifiedtime;
    private Integer dr;
    private Date ts;
    private Integer isStop;

    public String getPkDeptMerge() {
        return pkDeptMerge;
    }

    public void setPkDeptMerge(String pkDeptMerge) {
        this.pkDeptMerge = pkDeptMerge;
    }

    public String getPkHospital() {
        return pkHospital;
    }

    public void setPkHospital(String pkHospital) {
        this.pkHospital = pkHospital;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getMergeCode() {
        return mergeCode;
    }

    public void setMergeCode(String mergeCode) {
        this.mergeCode = mergeCode;
    }

    public String getMergeName() {
        return mergeName;
    }

    public void setMergeName(String mergeName) {
        this.mergeName = mergeName;
    }

    public String getPkDeptSta() {
        return pkDeptSta;
    }

    public void setPkDeptSta(String pkDeptSta) {
        this.pkDeptSta = pkDeptSta;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccYear() {
        return accYear;
    }

    public void setAccYear(String accYear) {
        this.accYear = accYear;
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

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public Date getTs() {
        return ts;
    }

    public void setTs(Date ts) {
        this.ts = ts;
    }

    public Integer getIsStop() {
        return isStop;
    }

    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
    }
}
