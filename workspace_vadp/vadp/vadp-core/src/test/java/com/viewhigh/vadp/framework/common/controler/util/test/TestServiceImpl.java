package com.viewhigh.vadp.framework.common.controler.util.test;

import java.text.MessageFormat;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
/**
 * 测试使用TestServiceImpl
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月23日
 * 修改日期: 2017年06月23日
 */
public class TestServiceImpl implements ITestService {

	@Override
	public void sayHello() {
		System.out.println("sayHello");
		System.out.println("sayHello...");
	}

	@Override
	public void sayHello(List<String> list) {
		System.out.println("sayHello(List<String> list)");
		System.out.println(MessageFormat.format("sayHello...{0}", list));
	}

	@Override
	public void sayHello(String value) {
		System.out.println("sayHello(String value)");
		System.out.println(MessageFormat.format("sayHello...{0}", value));
	}

	@Override
	public void sayHello(String value, Object... objects) {
		System.out.println("sayHello(String value, Object... objects)");
		System.out.println(MessageFormat.format("sayHello...{0},{1}", value, objects));
	}

	@Override
	public void sayHello(String[] values) {
		System.out.println("sayHello(String[] values)");
		System.out.println(MessageFormat.format("sayHello...{0}",ArrayUtils.toString(values)));
	}

}
