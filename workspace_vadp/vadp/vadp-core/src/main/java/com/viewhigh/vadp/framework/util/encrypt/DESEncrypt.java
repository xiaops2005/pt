package com.viewhigh.vadp.framework.util.encrypt;


import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
/**
 * DES加解密
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月23日
 * 修改日期: 2017年06月23日
 */
public class DESEncrypt {
    public static final String ALGORITHM_DES = "DES/CBC/PKCS5Padding";

    /**
     * @Title: encode   
     * @Description: DES加密
     * @param: @param key
     * @param: @param data
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public static String encode(String key, String data) throws Exception {
    	// 判断Key是否正确  
        if (key == null) {  
			throw new Exception("Key为空null");
        }  
        // 判断Key是否为16位  
        if (key.length() != 16) {  
            throw new Exception("Key长度不是16位");
        }  
        if (data == null)
            return null;
        DESKeySpec dks = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // key的长度不能够小于8位字节
        Key secretKey = keyFactory.generateSecret(dks);
        Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
        IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
        AlgorithmParameterSpec paramSpec = iv;
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
        byte[] bytes = cipher.doFinal(data.getBytes());
        return byte2hex(bytes);
    }

    /**
     * @Title: decode   
     * @Description:DES解密 
     * @param: @param key
     * @param: @param data
     * @param: @return
     * @param: @throws Exception      
     * @return: String      
     * @throws
     */
    public static String decode(String key, String data) throws Exception {
    	// 判断Key是否正确  
        if (key == null) {  
			throw new Exception("Key为空null");
        }  
        // 判断Key是否为16位  
        if (key.length() != 16) {  
            throw new Exception("Key长度不是16位");
        }  
        
		if (data == null)
			return null;
		
		DESKeySpec dks = new DESKeySpec(key.getBytes());
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		Key secretKey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance(ALGORITHM_DES);
		IvParameterSpec iv = new IvParameterSpec("12345678".getBytes());
		AlgorithmParameterSpec paramSpec = iv;
		cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
		return new String(cipher.doFinal(hex2byte(data.getBytes())));
       
    }

    private static String byte2hex(byte[] b) {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b != null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException();
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
}