package com.pikia.component.web.util;

import java.util.Random;

/**
 * 
 * @author Methew
 * 
 */
public class CharUtil {
	// 1、其中48～57为0到9十个阿拉伯数字；
	// 2、65～90为26个大写英文字母；
	// 3、97～122号为26个小写英文字母。
	
	public static String getTaskCode(String str) {
		Random rand = new Random();
		if (str.length() == 1) {
			str = String.valueOf(Math.abs(rand.nextInt()) % (99999 - 10000 + 1) + 10000) + str;
		} else if (str.length() == 2) {
			str = String.valueOf(Math.abs(rand.nextInt()) % (9999 - 1000 + 1) + 1000) + str;
		} else if (str.length() == 3) {
			str = String.valueOf(Math.abs(rand.nextInt()) % (999 - 100 + 1) + 100) + str;
		} else if (str.length() == 4) {
			str = String.valueOf(Math.abs(rand.nextInt()) % (99 - 10 + 1) + 10) + str;
		} else if (str.length() == 5) {
			str = String.valueOf(Math.abs(rand.nextInt()) % (9 - 1 + 1) + 1) + str;
		}
		return str;
	}

	public static String getThreeBitChar(String str) {
		if (str.length() == 1) {
			str = "00" + str;
		} else if (str.length() == 2) {
			str = "0" + str;
		}
		return str;
	}

	public static String getSixBitChar(String str) {
		if (str.length() == 1) {
			str = "00000" + str;
		} else if (str.length() == 2) {
			str = "0000" + str;
		} else if (str.length() == 3) {
			str = "000" + str;
		} else if (str.length() == 4) {
			str = "00" + str;
		} else if (str.length() == 5) {
			str = "0" + str;
		}

		return str;
	}

	/**
	 * 判断字符串是否全部为字母
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isLetter(String str) {
		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[a-zA-Z]+");
		java.util.regex.Matcher m = pattern.matcher(str);
		return m.matches();
	}

	/**
	 * 判断输入char类型变量的字符类型
	 * 
	 * @param c
	 *            char类型变量
	 * @return CharType 字符类型
	 */
	public static CharType checkType(char c) {
		CharType ct = null;
		// 中文，编码区间0x4e00-0x9fbb
		if ((c >= 0x4e00) && (c <= 0x9fbb)) {
			ct = CharType.CHINESE;
		}
		// Halfwidth and Fullwidth Forms， 编码区间0xff00-0xffef
		else if ((c >= 0xff00) && (c <= 0xffef)) { // 2字节英文字
			if (((c >= 0xff21) && (c <= 0xff3a)) || ((c >= 0xff41) && (c <= 0xff5a))) {
				ct = CharType.LETTER;
			}
			// 2字节数字
			else if ((c >= 0xff10) && (c <= 0xff19)) {
				ct = CharType.NUM;
			}
			// 其他字符，可以认为是标点符号
			else
				ct = CharType.DELIMITER;
		}
		// basic latin，编码区间 0000-007f
		else if ((c >= 0x0021) && (c <= 0x007e)) { // 1字节数字
			if ((c >= 0x0030) && (c <= 0x0039)) {
				ct = CharType.NUM;
			} // 1字节字符
			else if (((c >= 0x0041) && (c <= 0x005a)) || ((c >= 0x0061) && (c <= 0x007a))) {
				ct = CharType.LETTER;
			}
			// 其他字符，可以认为是标点符号
			// (c >= 0x2000) && (0x206F) 常用标点区间
			else
				ct = CharType.DELIMITER;
		}
		// latin-1，编码区间0080-00ff
		else if ((c >= 0x00a1) && (c <= 0x00ff)) {
			if ((c >= 0x00c0) && (c <= 0x00ff)) {
				ct = CharType.LETTER;
			} else
				ct = CharType.DELIMITER;
		} else
			ct = CharType.OTHER;
		return ct;
	}

	// Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS 4E00-9FBF：CJK 统一表意符号
	// Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS ：F900-FAFF：CJK 兼容象形文字
	// CJK Unified Ideographs Extension WikipediaUnicode扩展汉字
	// Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
	// ：3400-4DBF：CJK统一表意符号扩展 A
	// CJK Unified Ideographs Extension B 中日韩统一表意文字扩展区B
	// 根据Unicode编码完美的判断中文汉字
	private static boolean isChineseWord(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) {
			return true;
		}
		return false;
	}

	// Character.UnicodeBlock.GENERAL_PUNCTUATION ：2000-206F：常用标点
	// Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION ：3000-303F：CJK 符号和标点。
	// 〃 〄 々 〆 〇 〈 〉 《 》 「 」】 〒 〓 〔 〕 〖 〗 〘 〙 〚 〛
	// Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS ：FF00-FFEF：半角及全角形式
	// 根据Unicode编码完美的判断中文标点符号
	private static boolean isChinesePunctuation(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

	public static void main(String[] args) {
		String str = " 地址：inm陀fe    ^     ";
		char[] cword = str.toCharArray();
		for (char c : cword) {
			CharType type = checkType(c);
			System.out.println(c + "   " + type);
		}
	}
}
