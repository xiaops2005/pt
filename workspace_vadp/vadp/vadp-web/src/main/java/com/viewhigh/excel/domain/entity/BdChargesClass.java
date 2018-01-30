package com.viewhigh.excel.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

import com.viewhigh.excel.domain.Base;

@XmlRootElement
@Entity
@Table(name = "bd_charge_class")
public class BdChargesClass extends Base {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "pk_charge_class")
	private String pkChargeClass;
	@Column(name = "class_code")
	private String classCode;
	@Column(name = "class_name")
	private String className;
	@Column(name = "remark")
	private String remark;
	@Column(name = "acc_year")
	private Integer accYear;
	@Column(name = "creator")
	private String creator;
	@Column(name = "creationtime")
	private Date creationTime;
	@Column(name = "modifier")
	private double modifier;
	@Column(name = "modifiedtime")
	private Date modifiedTime;
	@Column(name = "dr")
	private Integer dr;
	@Column(name = "ts")
	private Date ts;
	@Column(name = "is_stop")
	private String isStop;
	@Column(name = "pk_hospital")
	private String pkHospital;

	public String getPkHospital() {
		return pkHospital;
	}

	public void setPkHospital(String pkHospital) {
		this.pkHospital = pkHospital;
	}

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public String getPkChargeClass() {
		return pkChargeClass;
	}

	public void setPkChargeClass(String pkChargeClass) {
		this.pkChargeClass = pkChargeClass;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAccYear() {
		return accYear;
	}

	public void setAccYear(Integer accYear) {
		this.accYear = accYear;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public double getModifier() {
		return modifier;
	}

	public void setModifier(double modifier) {
		this.modifier = modifier;
	}

	public Date getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
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
