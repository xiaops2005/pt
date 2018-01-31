package com.viewhigh.excel.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import com.viewhigh.excel.domain.Base;

@XmlRootElement
@Entity
@Table(name = "bd_code")
public class BdCode  extends Base{
	@Id
	@Column(name = "pk_code")
	private String  pkCode;
	@Column(name = "codetype_id")
	private String  codetypeId;
	@Column(name = "codetype_name")
	private String  codetypeName;
	@Column(name = "code_id")
	private String  codeId;
	@Column(name = "code_name")
	private String  codeName;
	@Column(name = "belong_area")
	private String  belongArea;
	@Column(name = "is_stop")
	private String   isStop;
	@Column(name = "remark")
	private String  remark;
	@Column(name = "dr")
	private Integer dr;
	@Column(name = "ts")
	private Date  ts;
	public String getPkCode() {
		return pkCode;
	}
	public void setPkCode(String pkCode) {
		this.pkCode = pkCode;
	}
	public String getCodetypeId() {
		return codetypeId;
	}
	public void setCodetypeId(String codetypeId) {
		this.codetypeId = codetypeId;
	}
	public String getCodetypeName() {
		return codetypeName;
	}
	public void setCodetypeName(String codetypeName) {
		this.codetypeName = codetypeName;
	}
	public String getCodeId() {
		return codeId;
	}
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getBelongArea() {
		return belongArea;
	}
	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}
	public String getIsStop() {
		return isStop;
	}
	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
