package com.viewhigh.excel.domain.dict;
/**
 * 收入费用明细表
 * @author xiaops
 *
 */
public class ReportIncome {
	//年度
	private int year;
	//收入类型
	private String incomeType;
	//收入项目
	private String incomeItem;
	//收入本年累计数
	private double incomeAmountSum;
	//成本项目
	private String costItem;
	//成本本年累计数
	private double costAmountSum;
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getIncomeType() {
		return incomeType;
	}
	public void setIncomeType(String incomeType) {
		this.incomeType = incomeType;
	}
	public String getIncomeItem() {
		return incomeItem;
	}
	public void setIncomeItem(String incomeItem) {
		this.incomeItem = incomeItem;
	}
	public double getIncomeAmountSum() {
		return incomeAmountSum;
	}
	public void setIncomeAmountSum(double incomeAmountSum) {
		this.incomeAmountSum = incomeAmountSum;
	}
	public String getCostItem() {
		return costItem;
	}
	public void setCostItem(String costItem) {
		this.costItem = costItem;
	}
	public double getCostAmountSum() {
		return costAmountSum;
	}
	public void setCostAmountSum(double costAmountSum) {
		this.costAmountSum = costAmountSum;
	}
	
}
