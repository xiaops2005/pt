package com.viewhigh.entity;

import com.viewhigh.excel.domain.Base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "bd_Assets")
public class BdAssets   extends Base {

    @Id
    @Column(name = "pk_assets")
  private String pkAssets;
    @Column(name="pk_hospital")
  private String pkHospital;
    @Column(name="assets_code")
  private String assetsCode;
    @Column(name="assets_name")
  private String assetsName;
    @Column(name="unit_name")
  private String unitName;
    @Column(name="assets_type")
  private String assetsType;
    @Column(name="assets_desc")
  private String assetsDesc;
    @Column(name="model")
  private String model;
    @Column(name="original_value")
  private Double originalValue;
    @Column(name="start_date")
  private Date startDate;
    @Column(name="durable_years")
  private String durableYears;
    @Column(name="capital_source_id")
  private String capitalSourceId;
    @Column(name="bunkfee_type_id")
  private String bunkfeeTypeId;
    @Column(name="is_stop")
  private Integer isStop;
    @Column(name="pk_charge_class")
  private String pkChargeClass;

  // 是否标准（0：否；1：是）
  @Column(name="is_standard")
  private Integer isStandard;

    @Column(name="remark")
  private String remark;
    @Column(name="acc_year")
  private String accYear;
    @Column(name="creator")
  private String creator;
    @Column(name="creationtime")
  private Date creationtime;
    @Column(name="modifier")
  private String modifier;
    @Column(name="modifiedtime")
  private Date modifiedtime;
    @Column(name="dr")
  private String dr;
    @Column(name="ts")
  private Date ts;

  public String getPkAssets() {
    return pkAssets;
  }

  public void setPkAssets(String pkAssets) {
    this.pkAssets = pkAssets;
  }

  public String getPkHospital() {
    return pkHospital;
  }

  public void setPkHospital(String pkHospital) {
    this.pkHospital = pkHospital;
  }

  public String getAssetsCode() {
    return assetsCode;
  }

  public void setAssetsCode(String assetsCode) {
    this.assetsCode = assetsCode;
  }

  public String getAssetsName() {
    return assetsName;
  }

  public void setAssetsName(String assetsName) {
    this.assetsName = assetsName;
  }

  public String getUnitName() {
    return unitName;
  }

  public void setUnitName(String unitName) {
    this.unitName = unitName;
  }

  public String getAssetsType() {
    return assetsType;
  }

  public void setAssetsType(String assetsType) {
    this.assetsType = assetsType;
  }

  public String getAssetsDesc() {
    return assetsDesc;
  }

  public void setAssetsDesc(String assetsDesc) {
    this.assetsDesc = assetsDesc;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Double getOriginalValue() {
    return originalValue;
  }

  public void setOriginalValue(Double originalValue) {
    this.originalValue = originalValue;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getDurableYears() {
    return durableYears;
  }

  public void setDurableYears(String durableYears) {
    this.durableYears = durableYears;
  }

  public String getCapitalSourceId() {
    return capitalSourceId;
  }

  public void setCapitalSourceId(String capitalSourceId) {
    this.capitalSourceId = capitalSourceId;
  }

  public String getBunkfeeTypeId() {
    return bunkfeeTypeId;
  }

  public void setBunkfeeTypeId(String bunkfeeTypeId) {
    this.bunkfeeTypeId = bunkfeeTypeId;
  }

  public Integer getIsStop() {
    return isStop;
  }

  public void setIsStop(Integer isStop) {
    this.isStop = isStop;
  }

  public String getPkChargeClass() {
    return pkChargeClass;
  }

  public void setPkChargeClass(String pkChargeClass) {
    this.pkChargeClass = pkChargeClass;
  }

  public Integer getIsStandard() {
    return isStandard;
  }

  public void setIsStandard(Integer isStandard) {
    this.isStandard = isStandard;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getAccYear() {
    return accYear;
  }

  public void setAccYear(String accYear) {
    this.accYear = accYear;
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

  public String getDr() {
    return dr;
  }

  public void setDr(String dr) {
    this.dr = dr;
  }

  public Date getTs() {
    return ts;
  }

  public void setTs(Date ts) {
    this.ts = ts;
  }
}
