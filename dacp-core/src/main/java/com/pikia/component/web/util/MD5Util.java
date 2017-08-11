package com.pikia.component.web.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* MD5加密解密及字符串对比工具类
 * @author jacob_ye
 * http://www.blog.csdn.net/zranye
 */
public class MD5Util {

	/**
	 * 获取字符串的MD5
	 * 
	 * @param origString
	 * @return
	 */
	public static String getMD5ofStr(String origString) {
		String origMD5 = null;
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] result = md5.digest(origString.getBytes());
			origMD5 = byteArray2HexStr(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return origMD5;
	}

	private static String byteArray2HexStr(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		for (byte b : bs) {
			sb.append(byte2HexStr(b));
		}
		return sb.toString();
	}

	private static String byte2HexStr(byte b) {
		String hexStr = null;
		int n = b;
		if (n < 0) {
			n = b & 0x7F + 111;
		}
		hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
		return hexStr.toUpperCase();
	}

	/**
	 * 获取经过几次再加密后的字符串MD5值,1次再加密,MD5相当于加密了两次
	 * 
	 * @param origString
	 * @param times
	 * @return
	 */
	public static String getMD5ofStr(String origString, int times) {
		String md5 = getMD5ofStr(origString);
		for (int i = 0; i < times - 1; i++) {
			md5 = getMD5ofStr(md5);
		}
		return md5;
	}

	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a',
			'b', 'c', 'd', 'e', 'f' };
	protected static MessageDigest messageDigest = null;
	static {
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.err.println("初始化失败，MessageDigest不支持MD5Util.");
			e.printStackTrace();
		}
	}

	/**
	 * 计算文件的MD5
	 * 
	 * @param fileName
	 *            文件的绝对路径
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(String fileName) throws IOException {
		File f = new File(fileName);
		return getFileMD5String(f);
	}

	/**
	 * 计算文件的MD5，重载方法
	 * 
	 * @param file
	 *            文件对象
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(File file) throws IOException {
		FileInputStream in = new FileInputStream(file);
		return getFileMD5String(in, file.length());
	}

	/**
	 * 计算文件的MD5，重载方法
	 * 
	 * @param file
	 *            文件对象
	 * @return
	 * @throws IOException
	 */
	public static String getFileMD5String(FileInputStream in, long fileSize) throws IOException {
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, fileSize);
		messageDigest.update(byteBuffer);
		return bufferToHex(messageDigest.digest());
	}

	public static String getFileMD5String(InputStream in) {
		try {
			byte[] bytes = new byte[in.available()];
			in.read(bytes, 0, bytes.length);
			messageDigest.update(bytes);
			return bufferToHex(messageDigest.digest());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	private static String bufferToHex(byte bytes[]) {
		return bufferToHex(bytes, 0, bytes.length);
	}

	private static String bufferToHex(byte bytes[], int m, int n) {
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static void main(String[] args) throws IOException {
		String fileName = "E:/vcredist.bmp";
		File file = new File(fileName);
		System.out.println(getFileMD5String(file));
		System.out.println("----------------");
		System.out.println(getMD5ofStr(fileName,1));
	}
}
