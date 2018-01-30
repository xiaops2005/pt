package com.viewhigh.excel.domain.entity;


import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "re_assets_original")
public class ReAssetsOriginal  extends Base {

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator="idGenerator")
    @Column(name="pk_assets_original")
  private String pkAssetsOriginal;
    @Column(name="status_id")
  private String statusId;
    @Column(name="pk_hospital")
  private String pkHospital;
    @Column(name="acc_year")
  private BigDecimal accYear;
    @Column(name="acc_month")
  private BigDecimal accMonth;
    @Column(name="pk_dept")
  private String pkDept;

    private String assetsCode;
    private String assetsName;
    private Date startDate;
    private String durableYears;
    private String assetsType;
    private Double originalValue;

    private String CBCSDeptCode;
    private String CBCSDeptName;
    private String EQUIDeptCode;
    private String EQUIDeptName;

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

    public String getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(String assetsType) {
        this.assetsType = assetsType;
    }

    public Double getOriginalValue() {
        return originalValue;
    }

    public void setOriginalValue(Double originalValue) {
        this.originalValue = originalValue;
    }

    public String getCBCSDeptCode() {
        return CBCSDeptCode;
    }

    public void setCBCSDeptCode(String CBCSDeptCode) {
        this.CBCSDeptCode = CBCSDeptCode;
    }

    public String getCBCSDeptName() {
        return CBCSDeptName;
    }

    public void setCBCSDeptName(String CBCSDeptName) {
        this.CBCSDeptName = CBCSDeptName;
    }

    public String getEQUIDeptCode() {
        return EQUIDeptCode;
    }

    public void setEQUIDeptCode(String EQUIDeptCode) {
        this.EQUIDeptCode = EQUIDeptCode;
    }

    public String getEQUIDeptName() {
        return EQUIDeptName;
    }

    public void setEQUIDeptName(String EQUIDeptName) {
        this.EQUIDeptName = EQUIDeptName;
    }

    @Column(name="pk_assets")
  private String pkAssets;
    @Column(name="depreciation")
  private Double depreciation;
    @Column(name="capital_source_id")
  private String capitalSourceId;
    @Column(name="ratio")
  private Double ratio;
    @Column(name="is_bunkfee_id")
  private String isBunkfeeId;
    @Column(name="pk_dept_merge")
  private String pkDeptMerge;
    @Column(name="pk_dept_cost")
  private String pkDeptCost;
    @Column(name="pk_charge_class")
  private String pkChargeClass;
    @Column(name="creator")
  private String creator;
    @Column(name="creationtime")
  private Date creationtime;
    @Column(name="dr")
  private BigDecimal dr;
    @Column(name="ts")
  private Date ts;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getPkAssetsOriginal() {
    return pkAssetsOriginal;
  }

  public void setPkAssetsOriginal(String pkAssetsOriginal) {
    this.pkAssetsOriginal = pkAssetsOriginal;
  }

  public String getPkHospital() {
    return pkHospital;
  }

  public void setPkHospital(String pkHospital) {
    this.pkHospital = pkHospital;
  }

  public BigDecimal getAccYear() {
    return accYear;
  }

  public void setAccYear(BigDecimal accYear) {
    this.accYear = accYear;
  }

  public BigDecimal getAccMonth() {
    return accMonth;
  }

  public void setAccMonth(BigDecimal accMonth) {
    this.accMonth = accMonth;
  }

  public String getPkDept() {
    return pkDept;
  }

  public void setPkDept(String pkDept) {
    this.pkDept = pkDept;
  }

  public String getPkAssets() {
    return pkAssets;
  }

  public void setPkAssets(String pkAssets) {
    this.pkAssets = pkAssets;
  }

  public Double getDepreciation() {
    return depreciation;
  }

  public void setDepreciation(Double depreciation) {
    this.depreciation = depreciation;
  }

  public String getCapitalSourceId() {
    return capitalSourceId;
  }

  public void setCapitalSourceId(String capitalSourceId) {
    this.capitalSourceId = capitalSourceId;
  }

  public Double getRatio() {
    return ratio;
  }

  public void setRatio(Double ratio) {
    this.ratio = ratio;
  }

  public String getIsBunkfeeId() {
    return isBunkfeeId;
  }

  public void setIsBunkfeeId(String isBunkfeeId) {
    this.isBunkfeeId = isBunkfeeId;
  }

  public String getPkDeptMerge() {
    return pkDeptMerge;
  }

  public void setPkDeptMerge(String pkDeptMerge) {
    this.pkDeptMerge = pkDeptMerge;
  }

  public String getPkDeptCost() {
    return pkDeptCost;
  }

  public void setPkDeptCost(String pkDeptCost) {
    this.pkDeptCost = pkDeptCost;
  }

  public String getPkChargeClass() {
    return pkChargeClass;
  }

  public void setPkChargeClass(String pkChargeClass) {
    this.pkChargeClass = pkChargeClass;
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

  public BigDecimal getDr() {
    return dr;
  }

  public void setDr(BigDecimal dr) {
    this.dr = dr;
  }

  public Date getTs() {
    return ts;
  }

  public void setTs(Date ts) {
    this.ts = ts;
  }
}
