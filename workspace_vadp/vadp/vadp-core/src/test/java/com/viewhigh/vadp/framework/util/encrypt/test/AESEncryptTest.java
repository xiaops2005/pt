package com.viewhigh.vadp.framework.util.encrypt.test;

import org.junit.Test;

import com.viewhigh.vadp.framework.util.encrypt.AESEncrypt;

public class AESEncryptTest {

	@Test
	public void test() throws Exception {
		System.out.println(AESEncrypt.encode("EAED67D6AEE34390","test"));
		System.out.println(AESEncrypt.decode("EAED67D6AEE34390","8TCN+dTpnElLAyyvKTRlrA=="));
	}
	
}
