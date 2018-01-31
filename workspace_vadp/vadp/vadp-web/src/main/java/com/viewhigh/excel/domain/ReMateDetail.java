package com.viewhigh.excel.domain;


import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;

/**
 * 材料整理表
 * @author xiaops
 *
 */
@XmlRootElement
public class ReMateDetail extends Base {
	
	public ReMateDetail() {
		// TODO Auto-generated constructor stub
	}
	
	@XmlElement(name="年度")
	private Integer accYear;
	
	@XmlElement(name="材料科室编码")
	private String materialsDeptCode;
	
	@XmlElement(name="材料科室名称")
	private String materialsDeptName;
	
	@XmlElement(name="成本科室编码")
	private String costDeptCode;
	
	@XmlElement(name="成本科室名称")
	private String costDeptName;
	
	@XmlElement(name="材料编码")
	private String materialsCode;
	
	@XmlElement(name="材料名称")
	private String materialsName;
	
	@XmlElement(name="计量单位")
	private String unit;
	
	@XmlElement(name="规格型号")
	private String model;
	
	@XmlElement(name="数量")
	private Integer quantity;
	
	@XmlElement(name="单价")
	private BigDecimal unitPrice;
	
	@XmlElement(name="金额")
	private BigDecimal amount;
	
	@XmlElement(name="是否单收费")
	private String isChargesId;

	//材料科室ID
	@XmlElement(name="*")
	private String pkDept;
	
	//材料ID
	@XmlElement(name="*")
	private String pkMaterials;
	
	//成本科室ID
	@XmlElement(name="*")
	private String pkDeptCost;
	
	@XmlElement(name="合并科室ID")
	private String pkDeptMerge;
	
	@XmlElement(name="项目大类ID")
	private String pkChargeClass;
	
	@XmlElement(name="是否继承数据")
	private String isInheritId;
	
	@XmlElement(name="是否公摊")
	private String isPublicId;
	
	//单据状态
	@XmlElement(name="*")
	private String statusId;
	
	@XmlElement(name="创建人")
	private String creator;
	
	@XmlElement(name="创建时间")
	private Date creationtime;
	
	@XmlElement(name="删除标志")
	private Integer dr;

	@XmlElement(name="时间戳")
	private Date ts;
	
	//材料整理表主键
	@Id
	@XmlElement(name="材料整理表主键")
	private String pkMateOriginal;
	
	@XmlElement(name="所属医院ID")
	private String pkHospital;
	






	
	public Integer getAccYear() {
		return accYear;
	}
	public void setAccYear(Integer accYear) {
		this.accYear = accYear;
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
	public String getPkDeptCost() {
		return pkDeptCost;
	}
	public void setPkDeptCost(String pkDeptCost) {
		this.pkDeptCost = pkDeptCost;
	}
	public String getIsChargesId() {
		return isChargesId;
	}
	public void setIsChargesId(String isChargesId) {
		this.isChargesId = isChargesId;
	}
	public String getMaterialsDeptCode() {
		return materialsDeptCode;
	}
	public void setMaterialsDeptCode(String materialsDeptCode) {
		this.materialsDeptCode = materialsDeptCode;
	}
	public String getMaterialsDeptName() {
		return materialsDeptName;
	}
	public void setMaterialsDeptName(String materialsDeptName) {
		this.materialsDeptName = materialsDeptName;
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
	public String getMaterialsCode() {
		return materialsCode;
	}
	public void setMaterialsCode(String materialsCode) {
		this.materialsCode = materialsCode;
	}
	public String getMaterialsName() {
		return materialsName;
	}
	public void setMaterialsName(String materialsName) {
		this.materialsName = materialsName;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
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
	public String getIsInheritId() {
		return isInheritId;
	}
	public void setIsInheritId(String isInheritId) {
		this.isInheritId = isInheritId;
	}
	public String getIsPublicId() {
		return isPublicId;
	}
	public void setIsPublicId(String isPublicId) {
		this.isPublicId = isPublicId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
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
	public Date getCreationtime() {
		return creationtime;
	}
	public void setCreationtime(Date creationtime) {
		this.creationtime = creationtime;
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
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	
	
}
