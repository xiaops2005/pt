package com.viewhigh.vadp.framework.ds;

import java.util.LinkedHashSet;
import java.util.Set;
/**
 * 元数据信息对象 
 * 	元数据封装了DataStore中列的信息，包括主键、数据类型、是否可以为空等信息
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class MetaData {

	private Set<Column> columns = new LinkedHashSet<Column>();
	private Order order = new Order();
	private String condition;

	public Set<Column> getColumns() {
		return columns;
	}

	public void setColumns(Set<Column> columns) {
		this.columns = columns;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

}
