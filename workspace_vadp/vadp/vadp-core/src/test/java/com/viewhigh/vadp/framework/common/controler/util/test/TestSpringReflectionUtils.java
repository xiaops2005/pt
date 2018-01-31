package com.viewhigh.vadp.framework.common.controler.util.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;
/**
 * 测试Spring发射工具类ReflectionUtils
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月23日
 * 修改日期: 2017年06月23日
 */
public class TestSpringReflectionUtils {
	
	@Test
	public void testfindMethod(){
		ITestService service = new TestServiceImpl();
		Method findMethod = ReflectionUtils.findMethod(service.getClass(), "sayHello", new Class[] { String.class });
		List<Object> args  = new ArrayList<>();
		args.add(null);
		ReflectionUtils.invokeMethod(findMethod, service, args.toArray());
		
		Method findMethod2 = ReflectionUtils.findMethod(service.getClass(), "sayHello");
		System.out.println(findMethod2);
	}

}
