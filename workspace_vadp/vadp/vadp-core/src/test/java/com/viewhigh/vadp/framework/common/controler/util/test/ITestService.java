package com.viewhigh.vadp.framework.common.controler.util.test;

import java.util.List;
/**
 * 测试使用ITestService
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月23日
 * 修改日期: 2017年06月23日
 */
public interface ITestService {
	
	
	public void sayHello();
	
	public void sayHello(List<String> list);
	
	public void sayHello(String value);
	
	public void sayHello(String value, Object... objects);
	
	public void sayHello(String[] values);

}
