package com.viewhigh.vadp.framework.util.encrypt;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * MD5信息摘要工具类
 * 版权所属：东软望海科技有限公司。
 * 作者：刘晓平
 * 版本：V1.0
 * 创建日期：  2017年06月23日
 * 修改日期: 2017年06月23日
 */
public class MD5Encrypt {

	
	private static final int STREAM_BUFFER_LENGTH = 1024;

	public static MessageDigest getDigest(final String algorithm) throws NoSuchAlgorithmException {
		return MessageDigest.getInstance(algorithm);
	}

	public static byte[] md5(String txt) {
		return md5(txt.getBytes());
	}

	public static byte[] md5(byte[] bytes) {
		try {
			MessageDigest digest = getDigest("MD5");
			digest.update(bytes);
			return digest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static byte[] md5(InputStream is) throws NoSuchAlgorithmException, IOException {
		return updateDigest(getDigest("MD5"), is).digest();
	}

	public static MessageDigest updateDigest(final MessageDigest digest, final InputStream data) throws IOException {
		final byte[] buffer = new byte[STREAM_BUFFER_LENGTH];
		int read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);

		while (read > -1) {
			digest.update(buffer, 0, read);
			read = data.read(buffer, 0, STREAM_BUFFER_LENGTH);
		}
		return digest;
	}

}
