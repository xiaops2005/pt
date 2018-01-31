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
@Table(name = "bd_medical_identify")
public class BdMedicalIdentify extends Base {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "pk_medical_identify")
	private String pkMedicalIdentify;
	@Column(name = "pk_hospital")
	private String pkHospital;
	@Column(name = "identify_code")
	private String identifyCode;
	@Column(name = "identify_name")
	private String identifyName;
	@Column(name = "remark")
	private String remark;
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
	public String getPkMedicalIdentify() {
		return pkMedicalIdentify;
	}
	public void setPkMedicalIdentify(String pkMedicalIdentify) {
		this.pkMedicalIdentify = pkMedicalIdentify;
	}
	public String getPkHospital() {
		return pkHospital;
	}
	public void setPkHospital(String pkHospital) {
		this.pkHospital = pkHospital;
	}
	public String getIdentifyCode() {
		return identifyCode;
	}
	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}
	public String getIdentifyName() {
		return identifyName;
	}
	public void setIdentifyName(String identifyName) {
		this.identifyName = identifyName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public String getIsStop() {
		return isStop;
	}
	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

}
