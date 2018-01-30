package com.viewhigh.vadp.framework.util.encrypt.test;

import org.junit.Test;

import com.viewhigh.vadp.framework.util.encrypt.RSAEncrypt;

public class RSAEncryptTest {

	
	@Test
	public void test() throws Exception {
		RSAEncrypt s = new RSAEncrypt();
		s.genKeyPair();
		System.out.println(s.encrypt("test".getBytes()));
		
	}
	
}
