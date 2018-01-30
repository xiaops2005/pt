package com.viewhigh.excel.domain.entity;

import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "bd_materials")
public class BdMaterials extends Base {

	@Id
//	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
//	@GeneratedValue(generator = "idGenerator")
	@Column(name = "pk_materials")
	private String pkMaterials;
	@Column(name = "pk_hospital")
	private String pkHospital;
	@Column(name = "materials_code")
	private String materialsCode;
	@Column(name = "materials_name")
	private String materialsName;
	@Column(name = "unit_name")
	private String unitName;
	@Column(name = "materials_desc")
	private String materialsDesc;
	@Column(name = "model")
	private String model;
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	@Column(name = "is_single")
	private String isSingle;
	@Column(name = "is_stop")
	private String isStop;
	@Column(name = "pk_charge_class")
	private String pkChargeClass;
	@Column(name = "remark")
	private String remark;
	@Column(name = "acc_year")
	private Integer accYear;
	@Column(name = "creator")
	private String creator;
	@Column(name = "creationtime")
	private java.util.Date creationtime;
	@Column(name = "modifier")
	private String modifier;
	@Column(name = "modifiedtime")
	private java.util.Date modifiedtime;
	@Column(name = "dr")
	private Long dr;
	@Column(name = "ts")
	private java.util.Date ts;

	public String getPkHospital() {
		return pkHospital;
	}

	public void setPkHospital(String pkHospital) {
		this.pkHospital = pkHospital;
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

	public String getPkMaterials() {
		return pkMaterials;
	}

	public void setPkMaterials(String pkMaterials) {
		this.pkMaterials = pkMaterials;
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

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMaterialsDesc() {
		return materialsDesc;
	}

	public void setMaterialsDesc(String materialsDesc) {
		this.materialsDesc = materialsDesc;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getIsSingle() {
		return isSingle;
	}

	public void setIsSingle(String isSingle) {
		this.isSingle = isSingle;
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

	public Integer getAccYear() {
		return accYear;
	}

	public void setAccYear(Integer accYear) {
		this.accYear = accYear;
	}

}
