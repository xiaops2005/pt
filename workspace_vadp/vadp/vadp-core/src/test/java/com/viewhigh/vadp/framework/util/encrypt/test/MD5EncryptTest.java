package com.viewhigh.vadp.framework.util.encrypt.test;

import org.junit.Test;

import com.viewhigh.vadp.framework.util.encrypt.MD5Encrypt;

public class MD5EncryptTest {

	@Test
	public void test(){
		System.out.println(MD5Encrypt.md5("123"));
	}
	
}
