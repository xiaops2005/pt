package com.viewhigh.vadp.framework.data.persistence.pagination;

import java.io.Serializable;

public class PageContext implements Serializable {

	private static final long serialVersionUID = 865535310750464253L;
	
	private String key;
	private String type;
	private String queryString;
	private Object[] params;
	private String pojo;
	private String dataSourceID;
	private String sessionFactoryId;

	public String getSessionFactoryId() {
		return this.sessionFactoryId;
	}

	public void setSessionFactoryId(String paramString) {
		this.sessionFactoryId = paramString;
	}

	public String getKey() {
		return this.key;
	}

	public void setKey(String paramString) {
		this.key = paramString;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String paramString) {
		this.type = paramString;
	}

	public void setQueryString(String paramString) {
		this.queryString = paramString;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public Object[] getParams() {
		return this.params;
	}

	public void setParams(Object[] paramArrayOfObject) {
		this.params = paramArrayOfObject;
	}

	public void setPojo(String paramString) {
		this.pojo = paramString;
	}

	public String getPojo() {
		return this.pojo;
	}

	public void setDataSourceID(String paramString) {
		this.dataSourceID = paramString;
	}

	public String getDataSourceID() {
		return this.dataSourceID;
	}
}