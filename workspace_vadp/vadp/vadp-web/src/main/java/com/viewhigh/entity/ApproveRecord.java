package com.viewhigh.entity;


import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "REC_APPROVE")
public class ApproveRecord extends Base {

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "PK_REC_APPROVE")
	private String pkRecApprove;
	
	@Column(name = "PK_HOSPITAL")
	private String pkHospital;
	
	@Column(name = "ACC_YEAR")
	private Integer accYear;
	
	@Column(name = "APP_TYPE_ID")
	private String appTypeId;
	
	@Column(name = "STATUS_ID")
	private String statusId;
	
	@Column(name = "dr")
	private Long dr;
	
	@Column(name = "ts")
	private Date ts;
	
	@Column(name = "APPROVER")
	private String approver;
	
	@Column(name = "APPROVETIME")
	private Date approvetime;
	
	@Column(name = "APPROVENOTE")
	private String approvenote;

	public String getPkRecApprove() {
		return pkRecApprove;
	}

	public void setPkRecApprove(String pkRecApprove) {
		this.pkRecApprove = pkRecApprove;
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

	public String getAppTypeId() {
		return appTypeId;
	}

	public void setAppTypeId(String appTypeId) {
		this.appTypeId = appTypeId;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public Long getDr() {
		return dr;
	}

	public void setDr(Long dr) {
		this.dr = dr;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
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

}
