package com.viewhigh.vadp.framework.ds;
/**
 * 排序信息
 * 		包括排序的类型、排序的字段
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月16日
 * 修改日期: 2017年06月16日
 */
public class Order {

	private String orderStyle;
	private String propertyName;

	public String getOrderStyle() {
		return orderStyle;
	}

	public void setOrderStyle(String orderStyle) {
		this.orderStyle = orderStyle;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

}
