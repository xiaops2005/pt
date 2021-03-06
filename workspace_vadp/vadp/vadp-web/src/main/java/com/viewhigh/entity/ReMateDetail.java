package com.viewhigh.entity;


import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "re_mate_detail")
public class ReMateDetail extends Base {

	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	@Column(name = "PK_MATE_ORIGINAL")
	private String pkMateOriginal;
	@Column(name = "PK_HOSPITAL")
	private String pkHospital;
	@Column(name = "ACC_YEAR")
	private Integer accYear;
	@Column(name = "PK_DEPT")
	private String pkDept;
	@Column(name = "PK_MATERIALS")
	private String pkMaterials;
	@Column(name = "QUANTITY")
	private BigDecimal quantity;
	@Column(name = "PK_DEPT_MERGE")
	private String pkDeptMerge;
	@Column(name = "PK_DEPT_COST")
	private String pkDeptCost;
	@Column(name = "PK_CHARGE_CLASS")
	private String pkChargeClass;
	@Column(name = "UNIT_PRICE")
	private BigDecimal unitPrice;
	@Column(name = "AMOUNT")
	private BigDecimal amount;
	@Column(name = "IS_CHARGES")
	private String isCharges;
	@Column(name = "IS_INHERIT")
	private String isInherit;
	@Column(name = "IS_PUBLIC")
	private String isPublic;
	@Column(name = "STATUS_ID")
	private String statusId;
	@Column(name = "creator")
	private String creator;
	@Column(name = "creationtime")
	private Date creationtime;
	@Column(name = "modifier")
	private String modifier;
	@Column(name = "modifiedtime")
	private Date modifiedtime;
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
	
	
	
	
	public String getPkMateOriginal() {
		return pkMateOriginal;
	}
	public void setPkMateOriginal(String pkMateOriginal) {
		this.pkMateOriginal = pkMateOriginal;
	}
	public String getPkHospital() {
		return pkHospital;
	}
	public void setPkHospital(String pkHospital) {
		this.pkHospital = pkHospital;
	}
	public String getPkDept() {
		return pkDept;
	}
	public void setPkDept(String pkDept) {
		this.pkDept = pkDept;
	}
	public String getPkMaterials() {
		return pkMaterials;
	}
	public void setPkMaterials(String pkMaterials) {
		this.pkMaterials = pkMaterials;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public String getPkDeptMerge() {
		return pkDeptMerge;
	}
	public void setPkDeptMerge(String pkDeptMerge) {
		this.pkDeptMerge = pkDeptMerge;
	}
	public String getPkChargeClass() {
		return pkChargeClass;
	}
	public void setPkChargeClass(String pkChargeClass) {
		this.pkChargeClass = pkChargeClass;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getIsCharges() {
		return isCharges;
	}
	public void setIsCharges(String isCharges) {
		this.isCharges = isCharges;
	}
	public String getIsInherit() {
		return isInherit;
	}
	public void setIsInherit(String isInherit) {
		this.isInherit = isInherit;
	}
	public String getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
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
	public Integer getAccYear() {
		return accYear;
	}
	public void setAccYear(Integer accYear) {
		this.accYear = accYear;
	}
	public String getPkDeptCost() {
		return pkDeptCost;
	}
	public void setPkDeptCost(String pkDeptCost) {
		this.pkDeptCost = pkDeptCost;
	}

}
