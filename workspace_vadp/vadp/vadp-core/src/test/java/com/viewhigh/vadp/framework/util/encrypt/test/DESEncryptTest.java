package com.viewhigh.vadp.framework.util.encrypt.test;

import org.junit.Test;

import com.viewhigh.vadp.framework.util.encrypt.DESEncrypt;

public class DESEncryptTest {
	
	@Test
	public void test() throws Exception {
		String key = "EAED67D6AEE34390";
		String value = DESEncrypt.encode(key, "123");
		System.out.println(value);
		System.out.println(DESEncrypt.decode(key, value));
	}

}
