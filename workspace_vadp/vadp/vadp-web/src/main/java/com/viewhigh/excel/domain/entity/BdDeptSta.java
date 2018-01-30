package com.viewhigh.excel.domain.entity;

import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "bd_dept_sta")
public class BdDeptSta extends Base {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "pk_dept_sta")
	private String pkDeptSta;
	@Column(name = "sta_code")
	private String staCode;
	@Column(name = "sta_name")
	private String staName;
	@Column(name = "remark")
	private String remark;
	@Column(name = "belong_area")
	private String belongArea;
	@Column(name = "creator")
	private String creator;
	@Column(name = "creationtime")
	private java.util.Date creationtime;
	@Column(name = "modifier")
	private String modifier;
	@Column(name = "modifiedtime")
	private java.util.Date modifiedtime;
	@Column(name = "dr")
	private Integer dr;
	@Column(name = "ts")
	private java.util.Date ts;
	@Column(name = "is_stop")
	private String isStop;

	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}

	public void setModifiedtime(java.util.Date modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public String getPkDeptSta() {
		return pkDeptSta;
	}

	public void setPkDeptSta(String pkDeptSta) {
		this.pkDeptSta = pkDeptSta;
	}

	public String getStaCode() {
		return staCode;
	}

	public void setStaCode(String staCode) {
		this.staCode = staCode;
	}

	public String getStaName() {
		return staName;
	}

	public void setStaName(String staName) {
		this.staName = staName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBelongArea() {
		return belongArea;
	}

	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public java.util.Date getCreationtime() {
		return creationtime;
	}

	public void setCreationtime(java.util.Date creationtime) {
		this.creationtime = creationtime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public java.util.Date getModifiedtime() {
		return modifiedtime;
	}

	public void setModifiedtime(java.sql.Timestamp modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	public Integer getDr() {
		return dr;
	}

	public void setDr(Integer dr) {
		this.dr = dr;
	}

	public java.util.Date getTs() {
		return ts;
	}

	public void setTs(java.util.Date ts) {
		this.ts = ts;
	}
}
