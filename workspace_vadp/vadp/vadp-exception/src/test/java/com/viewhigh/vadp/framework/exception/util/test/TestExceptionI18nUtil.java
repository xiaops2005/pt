package com.viewhigh.vadp.framework.exception.util.test;

import java.util.Locale;

import org.junit.Test;

import com.viewhigh.vadp.framework.exception.CoreException;
import com.viewhigh.vadp.framework.exception.util.ExceptionI18nUtil;

public class TestExceptionI18nUtil {
	
	@Test
	public void test(){
		System.out.println(ExceptionI18nUtil.getI18nInfo(new CoreException("12007",new String[]{"test"}), null ));
	}
	
	@Test
	public void test2(){
		System.out.println(ExceptionI18nUtil.has(new CoreException("12023",new String[]{"test2"}), null ));
	}

}
