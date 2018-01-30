package com.viewhigh.vadp.framework.common.controler.util.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import com.esotericsoftware.reflectasm.MethodAccess;
/**
 * 测试ASM反射调用
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月23日
 * 修改日期: 2017年06月23日
 */
public class TestReflectasm {

	@Test
	public void testsayHelloString(){
		ITestService service = new TestServiceImpl();
		MethodAccess access = MethodAccess.get(service.getClass());
		access.invoke(service, "sayHello", new Class[] { String.class }, "123");
	}
	
	@Test
	public void testsayHelloStringNull(){
		ITestService service = new TestServiceImpl();
		MethodAccess access = MethodAccess.get(service.getClass());
		List<Object> args  = new ArrayList<>();
		args.add(null);
		access.invoke(service, "sayHello", new Class[] { String.class }, args.toArray());
	}
	
	@Test
	public void testfindMethods(){
		ITestService service = new TestServiceImpl();
		MethodAccess access = MethodAccess.get(service.getClass());
		Class[][] parameterTypes = access.getParameterTypes();
		for (Class[] classes : parameterTypes) {
			System.out.println(ArrayUtils.toString(classes));
		}
		String[] clss = new String[0];
		System.out.println(clss.getClass());
	}
	
}
