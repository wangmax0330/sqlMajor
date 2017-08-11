package com.pikia.component.web.util;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES 加密
 * 
 * @author Methew
 * 
 */
public class AESUtil {
	public static String encrypt(String content, String passwd) {
		try {
			// ----------------AES/ECB/PKCS5Padding 算法/模式/填充
			Cipher aesECB = Cipher.getInstance("AES/ECB/PKCS5Padding");
			SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
			aesECB.init(Cipher.ENCRYPT_MODE, key);
			byte[] result = aesECB.doFinal(content.getBytes());
			return new BASE64Encoder().encode(result);
		} catch (Exception e) {
			return null;
		}
	}

	public static String decrypt(String content, String passwd) {

		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// 创建密码器
			SecretKeySpec key = new SecretKeySpec(passwd.getBytes(), "AES");
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = new BASE64Decoder().decodeBuffer(content);
			return new String(cipher.doFinal(result)); // 解密

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
