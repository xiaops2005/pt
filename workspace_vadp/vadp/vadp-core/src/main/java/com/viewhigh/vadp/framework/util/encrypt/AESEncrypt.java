package com.viewhigh.vadp.framework.util.encrypt;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * AES加密工具类
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月23日
 * 修改日期: 2017年06月23日
 */
@SuppressWarnings("all")
public class AESEncrypt {

    /**
     * 密钥算法
     */
    private static final String ALGORITHM = "AES";
    /**
     * 加解密算法/工作模式/填充方式
     */
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";


    /**
     * AES加密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String encode(String hexKey,String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR); // 创建密码器
        cipher.init(Cipher.ENCRYPT_MODE,  new SecretKeySpec(hexKey.getBytes(), ALGORITHM));// 初始化
        return new sun.misc.BASE64Encoder().encode(cipher.doFinal(data.getBytes()));
    }

    /**
     * AES解密
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(String hexKey,String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(hexKey.getBytes(), ALGORITHM));
        return new String(cipher.doFinal(new sun.misc.BASE64Decoder().decodeBuffer(data)));
    }

    /**
     * hex字符串 转 byte数组
     *
     * @param s
     * @return
     */
    private static byte[] hex2byte(String s) {
        if (s.length() % 2 == 0) {
            return hex2byte(s.getBytes(), 0, s.length() >> 1);
        } else {
            return hex2byte("0" + s);
        }
    }

    private static byte[] hex2byte(byte[] b, int offset, int len) {
        byte[] d = new byte[len];
        for (int i = 0; i < len * 2; i++) {
            int shift = i % 2 == 1 ? 0 : 4;
            d[i >> 1] |= Character.digit((char) b[offset + i], 16) << shift;
        }
        return d;
    }

    /**
     * Unicode 转中文
     *
     * @param ori
     * @return
     */
    private String convertUnicode(String ori) {
        char aChar;
        int len = ori.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = ori.charAt(x++);
            if (aChar == '\\') {
                aChar = ori.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = ori.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }

    
}

