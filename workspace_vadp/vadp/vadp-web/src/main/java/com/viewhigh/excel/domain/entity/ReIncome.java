package com.viewhigh.excel.domain.entity;

import com.viewhigh.excel.domain.Base;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name = "re_income")
public class ReIncome extends Base {
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
	@GeneratedValue(generator = "idGenerator")
	@XmlElement(name="收入报表主键")
	@Column(name = "pk_income")
	private String pkIncome;

	@XmlElement(name="所属医院ID")
	@Column(name = "pk_hospital")
	private String pkHospital;

	@XmlElement(name="年份")
	@Column(name = "acc_year")
	private Integer accYear;

	@XmlElement(name="月份")
	@Column(name = "acc_month")
	private Integer accMonth;

	@XmlElement(name="收入类型")
	@Column(name = "income_type_id")
	private String incomeTypeId;

	@XmlElement(name="收入项目")
	@Column(name = "income_item")
	private String incomeItem;

	@XmlElement(name="收入本年累计数")
	@Column(name = "income_amount_sum")
	private BigDecimal incomeAmountSum;

	@XmlElement(name="成本类型")
	@Column(name = "cost_type")
	private String costType;

	@XmlElement(name="成本项目")
	@Column(name = "cost_item")
	private String costItem;

	@XmlElement(name="成本本年累计数")
	@Column(name = "cost_amount_sum")
	private BigDecimal costAmountSum;


	@XmlElement(name="创建人")
	@Column(name = "creator")
	private String creator;

	@XmlElement(name="创建时间")
	@Column(name = "creationtime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationtime;

	@XmlElement(name="删除标志")
	@Column(name = "dr")
	private Integer dr;

	@XmlElement(name="时间戳")
	@Column(name = "ts")
	private Date ts;

	public ReIncome() {
	}

	public String getPkIncome() {
		return pkIncome;
	}

	public void setPkIncome(String pkIncome) {
		this.pkIncome = pkIncome;
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

	public Integer getAccMonth() {
		return accMonth;
	}

	public void setAccMonth(Integer accMonth) {
		this.accMonth = accMonth;
	}

	public String getIncomeTypeId() {
		return incomeTypeId;
	}

	public void setIncomeTypeId(String incomeTypeId) {
		this.incomeTypeId = incomeTypeId;
	}

	public String getIncomeItem() {
		return incomeItem;
	}

	public void setIncomeItem(String incomeItem) {
		this.incomeItem = incomeItem;
	}

	public BigDecimal getIncomeAmountSum() {
		return incomeAmountSum;
	}

	public void setIncomeAmountSum(BigDecimal incomeAmountSum) {
		this.incomeAmountSum = incomeAmountSum;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public String getCostItem() {
		return costItem;
	}

	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}

	public BigDecimal getCostAmountSum() {
		return costAmountSum;
	}

	public void setCostAmountSum(BigDecimal costAmountSum) {
		this.costAmountSum = costAmountSum;
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
