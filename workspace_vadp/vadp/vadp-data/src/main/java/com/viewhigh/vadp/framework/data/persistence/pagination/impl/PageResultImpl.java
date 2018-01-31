package com.viewhigh.vadp.framework.data.persistence.pagination.impl;

import java.util.List;

import com.viewhigh.vadp.framework.data.persistence.pagination.Page;
import com.viewhigh.vadp.framework.data.persistence.pagination.PageResult;

public class PageResultImpl implements PageResult {
	
	private Page page = null;
	private List resultSet = null;
	private long totalCount = 0L;
	private long totalPage = 0L;

	public Page getPage() {
		return this.page;
	}

	public long getTotalPage() {
		return this.totalPage;
	}

	public Page setPage(Page paramPage) {
		return this.page = paramPage;
	}

	public void setTotalPage(long paramLong) {
		this.totalPage = paramLong;
	}

	public List getResultSet() {
		return this.resultSet;
	}

	public long getTotalCount() {
		return this.totalCount;
	}

	public void setResultSet(List paramList) {
		this.resultSet = paramList;
	}

	public void setTotalCount(long paramLong) {
		this.totalCount = paramLong;
	}
}