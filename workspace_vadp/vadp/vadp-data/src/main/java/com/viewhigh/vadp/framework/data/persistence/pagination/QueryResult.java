package com.viewhigh.vadp.framework.data.persistence.pagination;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class QueryResult {
	
	private List result;
	private int recordCount;
	private int pageSize;
	private int pageNumber;
	private PageContext pageContext;
	private boolean autoCalcCount = false;

	public QueryResult() {
		this.result = new ArrayList();
		this.recordCount = 0;
	}

	public QueryResult(List paramList, int paramInt1, int paramInt2, int paramInt3) {
		this.result = paramList;
		this.recordCount = paramInt1;
		this.pageNumber = paramInt2;
		this.pageSize = paramInt3;
	}

	public void setRecordCount(int paramInt) {
		this.recordCount = paramInt;
	}

	public int getRecordCount() {
		return this.recordCount;
	}

	public void setPageNumber(int paramInt) {
		this.pageNumber = paramInt;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageSize(int paramInt) {
		this.pageSize = paramInt;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public List getResult() {
		return this.result;
	}

	public void setResult(List paramList) {
		this.result = paramList;
	}

	public void setPageContext(PageContext paramPageContext) {
		this.pageContext = paramPageContext;
	}

	public PageContext getPageContext() {
		return this.pageContext;
	}

	public void setAutoCalcCount(boolean paramBoolean) {
		this.autoCalcCount = paramBoolean;
	}

	public boolean isAutoCalcCount() {
		return this.autoCalcCount;
	}

}
