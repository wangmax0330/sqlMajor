package com.pikia.component.web.util;

import java.util.Random;
import java.util.UUID;

/**
 * 生成唯一key
 * 
 * @author methew
 * 
 */
public class SimpleKey {
	public static String[] chars = { "a", "b", "c", "d", "e", "f", "g", "h",
			"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u",
			"v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7",
			"8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
			"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z" };

	public static char[] hexChars = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static String genShortUuid() {
		return genShortUuid(8);
	}

	public static String genShortUuid(int bit) {
		StringBuffer shortBuffer = new StringBuffer();
		// UID.randomUUID().toString()是javaJDK提供的一个自动生成主键的方法。
		// UUID(Universally Unique Identifier)
		// 全局唯一标识符,是指在一台机器上生成的数字，它保证对在同一时空中的所有机器都是唯一的，
		// 是由一个十六位的数字组成,表现出来的形式。共36位
		String uuid = UUID.randomUUID().toString().replace("-", "");
		// for 0<=bit<=8
		for (int i = 0; i < bit; i++) {
			String str = uuid.substring(i * 4, i * 4 + 4);
			System.out.println(uuid);
			System.out.println(str);
			int x = Integer.parseInt(str, 16);// 16进制转换成十进制
			// x%62 求余,余数肯定比除数小
			shortBuffer.append(chars[(x % 62)]);
		}

		return shortBuffer.toString();
	}

	public static String genHexKey(int bit) {
		StringBuilder sb = new StringBuilder(bit);
		Random random = new Random((int) Math.random() * 9999);
		for (int i = 0; i < bit; i++) {
			sb.append(hexChars[(Math.abs(random.nextInt()) % 16)]);
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(SimpleKey.genShortUuid());
		System.out.println(SimpleKey.genHexKey(32));
		System.out.println("-------------begin-------");
		System.out.println(SimpleKey.genShortUuid(2));
		System.out.println("--------------end------");
		System.out.println(SimpleKey.genShortUuid(8));
	}
}
