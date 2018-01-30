package com.viewhigh.excel.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Id;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 人员经费表
 * @author xiaops
 *
 */
@XmlRootElement
public class ReWage extends Base {
	@XmlElement(name="年度")
	private Integer accYear;
	
	@XmlElement(name="科室编码")
	private String costDeptCode;
	
	@XmlElement(name="科室名称")
	private String costDeptName;
	
	@XmlElement(name="医生人数")
	private BigDecimal doctorNum;
	
	@XmlElement(name="医生薪酬")
	private BigDecimal doctorWage;
	
	@XmlElement(name="护士人数")
	private BigDecimal nurseNum;
	
	@XmlElement(name="护士薪酬")
	private BigDecimal nurseWage;
	
	//成本科室ID
	@XmlElement(name="*")
	private String pkDept;
	
	//合并科室ID
	@XmlElement(name="*")
	private String pkDeptMerge;
	
	//单据状态
	@XmlElement(name="*")
	private String statusId;
	
	//创建人
	@XmlElement(name="*")
	private String creator;
	
	//创建时间
	@XmlElement(name="*")
	private Date creationtime;
	
	//删除标志
	@XmlElement(name="*")
	private BigDecimal dr;

	//时间戳
	@XmlElement(name="*")
	private Date ts;
	
	//人员经费主键
	@Id
	@XmlElement(name="人员经费主键")
	private String pkWage;
	
	//所属医院ID
	@XmlElement(name="*")
	private String pkHospital;
	
	//人员经费合计
	@XmlElement(name="*")
	private BigDecimal amountSum;

	public Integer getAccYear() {
		return accYear;
	}

	public void setAccYear(Integer accYear) {
		this.accYear = accYear;
	}

	public String getCostDeptCode() {
		return costDeptCode;
	}

	public void setCostDeptCode(String costDeptCode) {
		this.costDeptCode = costDeptCode;
	}

	public String getCostDeptName() {
		return costDeptName;
	}

	public void setCostDeptName(String costDeptName) {
		this.costDeptName = costDeptName;
	}

	public BigDecimal getDoctorNum() {
		return doctorNum;
	}

	public void setDoctorNum(BigDecimal doctorNum) {
		this.doctorNum = doctorNum;
	}

	public BigDecimal getDoctorWage() {
		return doctorWage;
	}

	public void setDoctorWage(BigDecimal doctorWage) {
		this.doctorWage = doctorWage;
	}

	public BigDecimal getNurseNum() {
		return nurseNum;
	}

	public void setNurseNum(BigDecimal nurseNum) {
		this.nurseNum = nurseNum;
	}

	public BigDecimal getNurseWage() {
		return nurseWage;
	}

	public void setNurseWage(BigDecimal nurseWage) {
		this.nurseWage = nurseWage;
	}

	public String getPkDept() {
		return pkDept;
	}

	public void setPkDept(String pkDept) {
		this.pkDept = pkDept;
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

	public BigDecimal getAmountSum() {
		return amountSum;
	}

	public void setAmountSum(BigDecimal amountSum) {
		this.amountSum = amountSum;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	
}
