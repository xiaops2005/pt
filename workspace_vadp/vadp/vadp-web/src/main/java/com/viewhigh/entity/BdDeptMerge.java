package com.viewhigh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Created by litianzhu on 2018/1/9.
 */
@XmlRootElement
@Entity
@Table(name = "BD_DEPT_MERGE")
public class BdDeptMerge {
    @Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
    @Column(name = "PK_DEPT_MERGE")
    private String pkDeptMerge;

    @Column(name = "PK_HOSPITAL")
    private String pkHospital;

    @Column(name = "MERGE_CODE")
    private String mergeCode;
    @Column(name = "MERGE_NAME")
    private String mergeName;
    @Column(name = "PK_DEPT_STA")
    private String pkDeptSta;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "ACC_YEAR")
    private String accYear;
    @Column(name = "CREATOR")
    private String creator;
    @Column(name = "CREATIONTIME")
    private Date creationtime;
    @Column(name = "MODIFIER")
    private String modifier;
    @Column(name = "MODIFIEDTIME")
    private Date modifiedtime;

    @Column(name = "DR")
    private Integer dr;

    @Column(name = "TS")
    private Date ts;

    @Column(name = "IS_STOP")
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
