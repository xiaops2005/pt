package com.viewhigh.vadp.framework.ds;

import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;
import com.viewhigh.vadp.framework.exception.CoreException;
/**
 * DataStore是RowSet的超集，一个DataStore中至多包含一个RowSet.包含数据信息,如分页大小、记录条数等。
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class DataStore {

	private String name;
	private Integer pageNumber = 0; // 默认第一页
	private Integer pageSize = 100; // 默认100条数据
	private Integer recordCount = 0;// 总行数
	private MetaData metaData = new MetaData();
	private RowSet rowSet = new RowSet();
	private String rowSetName = "";// DataSet名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public String getRowSetName() {
		return rowSetName;
	}

	public void setRowSetName(String rowSetName) {
		this.rowSetName = rowSetName;
	}

	public void setRowSet(RowSet rowSet) {
		this.rowSet = rowSet;
	}

	public void parse(JsonObject jsonObj) throws CoreException {
		if (jsonObj.has("name")) {
			this.name = jsonObj.get("name").getAsString();
		}
		if (jsonObj.has("pageNumber")) {
			this.pageNumber = jsonObj.get("pageNumber").getAsInt();
		}
		if (jsonObj.has("pageSize")) {
			this.pageSize = jsonObj.get("pageSize").getAsInt();
		}
		if (jsonObj.has("recordCount")) {
			this.recordCount = jsonObj.get("recordCount").getAsInt();
		}
		if (jsonObj.has("rowSetName")) {
			this.rowSetName = jsonObj.get("rowSetName").getAsString();
		}
		if (jsonObj.has("rowSet") && jsonObj.get("rowSet").isJsonObject()) {
			this.rowSet.parse(jsonObj.get("rowSet").getAsJsonObject());
		}
	}
	
	public void clearRowSet() {
		this.rowSet.getPrimary().clear();
	}

	public void deleteRow(int paramInt) {
		if (this.rowSet.getPrimary().size() == 0 || paramInt > (this.rowSet.getPrimary().size() - 1)) {
			return;
		}
		this.rowSet.getPrimary().remove(paramInt);
	}

	public RowSet getRowSet() {
		return rowSet;
	}
	
	public List<?> getRowDatas() {
		return this.rowSet.getPrimary();
	}
	
	public void addRowData(Map paramMap) {
		this.rowSet.getPrimary().add(paramMap);
	}
	
	
}
