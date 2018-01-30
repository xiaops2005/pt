package com.viewhigh.vadp.framework.data.persistence.pagination;

import java.io.Serializable;
import java.util.List;

public abstract interface Page extends Serializable {
	
	public static final String PAGE_CONTEXT_KEY = "VIEWHIGH_PAGE";

	public abstract int getSize();

	public abstract int getPageNumber();

	public abstract String getCondition();

	public abstract void setCondition(String paramString);

	public abstract List getConditionValues();

	public abstract void setConditionValues(List paramList);

	public abstract List getOrders();

	public abstract String getRowSetName();
}
