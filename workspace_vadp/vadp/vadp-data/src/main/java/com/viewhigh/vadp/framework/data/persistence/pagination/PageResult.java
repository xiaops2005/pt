package com.viewhigh.vadp.framework.data.persistence.pagination;

import java.util.List;

public abstract interface PageResult {
	
	public abstract List getResultSet();

	public abstract long getTotalCount();

	public abstract long getTotalPage();

	public abstract Page getPage();
}