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
@Table(name = "bd_charges_hos")
public class BdChargesHos extends Base{
	@Id
//	@GenericGenerator(name = "idGenerator", strategy = "uuid") // 这个是hibernate的注解/生成32位UUID
//	@GeneratedValue(generator = "idGenerator")
	@Column(name = "pk_charges_hos")
	private String  pkChargesHos;
	@Column(name = "pk_hospital")
	private String  pkHospital;
	@Column(name = "charges_code")
	private String  chargesCode;
	@Column(name = "charges_name")
	private String  chargesName;
	@Column(name = "charges_desc")
	private String  chargesDesc;
	@Column(name = "charges_type")
	private String  chargesType;
	@Column(name = "unit_name")
	private String  unitName;
	@Column(name = "unit_price")
	private double unitPrice;
	@Column(name = "is_outside_id")
	private String   isOutsideId;
	@Column(name = "isCheckId")
	private String   isCheckId;
	@Column(name = "is_stop_id")
	private String   isStopId;
	@Column(name = "pk_charges_sta")
	private String   pkChargesSta;
	@Column(name = "pk_charge_class")
	private String   pkChargeClass;
	@Column(name = "remark")
	private String   remark;
	@Column(name = "acc_year")
	private Integer  accYear;
	@Column(name = "creator")
	private String   creator;
	@Column(name = "modifier")
	private String   modifier;
	@Column(name = "modifiedtime")
	private Date  modifiedtime;
	@Column(name = "dr")
	private Integer  dr;
	@Column(name = "ts")
	private  Date ts;
	public String getPkChargesHos() {
		return pkChargesHos;
	}
	public void setPkChargesHos(String pkChargesHos) {
		this.pkChargesHos = pkChargesHos;
	}
	public String getPkHospital() {
		return pkHospital;
	}
	public void setPkHospital(String pkHospital) {
		this.pkHospital = pkHospital;
	}
	public String getChargesCode() {
		return chargesCode;
	}
	public void setChargesCode(String chargesCode) {
		this.chargesCode = chargesCode;
	}
	public String getChargesName() {
		return chargesName;
	}
	public void setChargesName(String chargesName) {
		this.chargesName = chargesName;
	}
	public String getChargesDesc() {
		return chargesDesc;
	}
	public void setChargesDesc(String chargesDesc) {
		this.chargesDesc = chargesDesc;
	}
	public String getChargesType() {
		return chargesType;
	}
	public void setChargesType(String chargesType) {
		this.chargesType = chargesType;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getIsOutsideId() {
		return isOutsideId;
	}
	public void setIsOutsideId(String isOutsideId) {
		this.isOutsideId = isOutsideId;
	}
	public String getIsCheckId() {
		return isCheckId;
	}
	public void setIsCheckId(String isCheckId) {
		this.isCheckId = isCheckId;
	}
	public String getIsStopId() {
		return isStopId;
	}
	public void setIsStopId(String isStopId) {
		this.isStopId = isStopId;
	}
	public String getPkChargesSta() {
		return pkChargesSta;
	}
	public void setPkChargesSta(String pkChargesSta) {
		this.pkChargesSta = pkChargesSta;
	}
	public String getPkChargeClass() {
		return pkChargeClass;
	}
	public void setPkChargeClass(String pkChargeClass) {
		this.pkChargeClass = pkChargeClass;
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
