package com.viewhigh.vadp.framework.data.persistence.pagination;

/**
 * 分页信息，用于线程变量中
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年4月25日
 * 修改日期: 2017年4月25日
 */
public class PageInfo {
	private String pageSize;
	private String pageNum;
	private String calc;
	
	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageNum() {
		return pageNum;
	}

	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}

	public String getCalc() {
		return calc;
	}

	public void setCalc(String calc) {
		this.calc = calc;
	}

}
