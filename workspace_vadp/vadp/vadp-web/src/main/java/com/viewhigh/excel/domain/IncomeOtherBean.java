package com.viewhigh.excel.domain;
/**
 * 收入明细
 */
public class IncomeOtherBean extends Base {

	private String pkIncomeDetail;//主键
	private Integer accYear;//年
	private String incomeTypeId; // 收入类型
	private String incomeType; // 收入类型
	private String pkDeptStart; // 开单科室编码
	private String pkDeptStartCode; // 开单科室编码
	private String pkDeptName; // 开单科室名称
	private String pkDeptExecute; //执行科室编码
	private String pkDeptExecuteCode; //执行科室编码
	private String pkDeptExecuteName;//执行科室名称
	private String pkDeptCost; //成本科室编码
	private String pkDeptCostCode; //成本科室编码
	private String pkDeptCostName; //成本科室名称
	private String pkChargesHos;//收费项目编码
	private String pkChargesHosCode;//收费项目编码
	private String pkChargesHosName; //收费项目名称
	private String chargesType;//明细分类
	private String medicalIdentify; //医护标识
	private String medicalIdentifyId; //医护标识
	private String unitPrice; //单价
	private String quantity; //数量
	private String amount; //金额
	private String belongOutpatient; //归属门诊
	private String doctorName; //医生姓名
	public Integer getAccYear() {
		return accYear;
	}
	public void setAccYear(Integer accYear) {
		this.accYear = accYear;
	}
	public String getIncomeTypeId() {
		return incomeTypeId;
	}
	public void setIncomeTypeId(String incomeTypeId) {
		this.incomeTypeId = incomeTypeId;
	}
	public String getPkDeptStart() {
		return pkDeptStart;
	}
	public void setPkDeptStart(String pkDeptStart) {
		this.pkDeptStart = pkDeptStart;
	}
	public String getPkDeptName() {
		return pkDeptName;
	}
	public void setPkDeptName(String pkDeptName) {
		this.pkDeptName = pkDeptName;
	}
	public String getPkDeptExecute() {
		return pkDeptExecute;
	}
	public void setPkDeptExecute(String pkDeptExecute) {
		this.pkDeptExecute = pkDeptExecute;
	}
	public String getPkDeptExecuteName() {
		return pkDeptExecuteName;
	}
	public void setPkDeptExecuteName(String pkDeptExecuteName) {
		this.pkDeptExecuteName = pkDeptExecuteName;
	}
	public String getPkDeptCost() {
		return pkDeptCost;
	}
	public void setPkDeptCost(String pkDeptCost) {
		this.pkDeptCost = pkDeptCost;
	}
	public String getPkDeptCostName() {
		return pkDeptCostName;
	}
	public void setPkDeptCostName(String pkDeptCostName) {
		this.pkDeptCostName = pkDeptCostName;
	}
	public String getPkChargesHos() {
		return pkChargesHos;
	}
	public void setPkChargesHos(String pkChargesHos) {
		this.pkChargesHos = pkChargesHos;
	}
	public String getPkChargesHosName() {
		return pkChargesHosName;
	}
	public void setPkChargesHosName(String pkChargesHosName) {
		this.pkChargesHosName = pkChargesHosName;
	}
	public String getChargesType() {
		return chargesType;
	}
	public void setChargesType(String chargesType) {
		this.chargesType = chargesType;
	}
	public String getMedicalIdentifyId() {
		return medicalIdentifyId;
	}
	public void setMedicalIdentifyId(String medicalIdentifyId) {
		this.medicalIdentifyId = medicalIdentifyId;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBelongOutpatient() {
		return belongOutpatient;
	}
	public void setBelongOutpatient(String belongOutpatient) {
		this.belongOutpatient = belongOutpatient;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	public String getPkDeptStartCode() {
		return pkDeptStartCode;
	}
	public void setPkDeptStartCode(String pkDeptStartCode) {
		this.pkDeptStartCode = pkDeptStartCode;
	}
	public String getPkDeptExecuteCode() {
		return pkDeptExecuteCode;
	}
	public void setPkDeptExecuteCode(String pkDeptExecuteCode) {
		this.pkDeptExecuteCode = pkDeptExecuteCode;
	}
	public String getPkDeptCostCode() {
		return pkDeptCostCode;
	}
	public void setPkDeptCostCode(String pkDeptCostCode) {
		this.pkDeptCostCode = pkDeptCostCode;
	}
	public String getPkChargesHosCode() {
		return pkChargesHosCode;
	}
	public void setPkChargesHosCode(String pkChargesHosCode) {
		this.pkChargesHosCode = pkChargesHosCode;
	}
	public String getPkIncomeDetail() {
		return pkIncomeDetail;
	}
	public void setPkIncomeDetail(String pkIncomeDetail) {
		this.pkIncomeDetail = pkIncomeDetail;
	}
	public String getMedicalIdentify() {
		return medicalIdentify;
	}
	public void setMedicalIdentify(String medicalIdentify) {
		this.medicalIdentify = medicalIdentify;
	}

}
