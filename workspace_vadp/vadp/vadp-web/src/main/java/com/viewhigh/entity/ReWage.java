package com.viewhigh.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 人员经费
 * <p>
 * Created by kevin on 2018/1/18.
 */
@XmlRootElement
@Entity
@Table(name = "re_wage")
public class ReWage {

    @Id
    @GeneratedValue(generator = "pkReWageGen")
    @GenericGenerator(name = "pkReWageGen", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
    @Column(name = "pk_wage")
    private String pkWage;//人员经费主键

    @Column(name = "pk_hospital")
    private String pkHospital;//所属医院ID  外键

    @Column(name = "acc_year")
    private Integer accYear;//年份

    @Column(name = "acc_month")
    private Integer accMonth;//月份 暂不用

    @Column(name = "pk_dept")
    private String pkDept;//成本科室ID  外键（导入时，提供科室编码与名称）

    @Column(name = "doctor_num")
    private Integer doctorNum;  //医生人数

    @Column(name = "doctor_wage")
    private Double doctorWage;  //医生经费

    @Column(name = "nurse_num")
    private Integer nurseNum;  //护士人数

    @Column(name = "nurse_wage")
    private Double nurseWage;  //护士经费

    @Column(name = "amount_sum")
    private Double amountSum;  //人员经费合计  系统需重新计算

    @Column(name = "pk_dept_merge")
    private String pkDeptMerge; //合并科室ID  外键，成本归集时回写

    @Column(name = "status_id")
    private String statusId; //单据状态 外键，来源于 bd_code 中 codetype_id = “STATUS”

    @Column(name = "creator")
    private String creator; //创建人  外键

    @Column(name = "creationtime")
    private Date creationtime;//创建时间

    @Column(name = "modifier")
    private String modifier; //修改人  外键

    @Column(name = "modifiedtime")
    private Date modifiedtime;//修改时间

    @Column(name = "approver")
    private String approver; //审核人  外键

    @Column(name = "approvetime")
    private Date approvetime;//审核时间

    @Column(name = "approvenote")
    private String approvenote; //审核评语

    @Column(name = "dr")
    private Integer dr;//删除标志 0 初始值 1 逻辑删除

    @Column(name = "ts")
    private Date ts; //时间戳


    public ReWage() {
    }

    public String getPkWage() {
        return pkWage;
    }

    public void setPkWage(String pkWage) {
        this.pkWage = pkWage;
    }

    public String getPkHospital() {
        return pkHospital;
    }

    public void setPkHospital(String pkHospital) {
        this.pkHospital = pkHospital;
    }

    public Integer getAccYear() {
        return accYear;
    }

    public void setAccYear(Integer accYear) {
        this.accYear = accYear;
    }

    public Integer getAccMonth() {
        return accMonth;
    }

    public void setAccMonth(Integer accMonth) {
        this.accMonth = accMonth;
    }

    public String getPkDept() {
        return pkDept;
    }

    public void setPkDept(String pkDept) {
        this.pkDept = pkDept;
    }

    public Integer getDoctorNum() {
        return doctorNum;
    }

    public void setDoctorNum(Integer doctorNum) {
        this.doctorNum = doctorNum;
    }

    public Double getDoctorWage() {
        return doctorWage;
    }

    public void setDoctorWage(Double doctorWage) {
        this.doctorWage = doctorWage;
    }

    public Integer getNurseNum() {
        return nurseNum;
    }

    public void setNurseNum(Integer nurseNum) {
        this.nurseNum = nurseNum;
    }

    public Double getNurseWage() {
        return nurseWage;
    }

    public void setNurseWage(Double nurseWage) {
        this.nurseWage = nurseWage;
    }

    public Double getAmountSum() {
        return amountSum;
    }

    public void setAmountSum(Double amountSum) {
        this.amountSum = amountSum;
    }

    public String getPkDeptMerge() {
        return pkDeptMerge;
    }

    public void setPkDeptMerge(String pkDeptMerge) {
        this.pkDeptMerge = pkDeptMerge;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
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
}
