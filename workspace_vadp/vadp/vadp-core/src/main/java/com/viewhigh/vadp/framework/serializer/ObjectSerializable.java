package com.viewhigh.vadp.framework.serializer;
/**
 * 
 * 对象序列化接口
 * 版权所属：东软望海科技有限公司。
 * 作者：梁国华
 * 版本：V1.0
 * 创建日期：2017年5月31日
 * 修改日期: 2017年5月31日
 */
public interface ObjectSerializable {

	public Object serial(Object object);

	public Object unserial(Object value);
}
