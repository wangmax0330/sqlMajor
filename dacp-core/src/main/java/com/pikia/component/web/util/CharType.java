package com.pikia.component.web.util;

public enum CharType {
	/**
	 * 非字母截止字符，例如，．）（　等等　（ 包含U0000-U0080）
	 */
	DELIMITER,
	/**
	 * 2字节数字１２３４
	 */
	NUM,
	/**
	 * gb2312中的，例如:ＡＢＣ，2字节字符同时包含 1字节能表示的 basic latin and latin-1
	 */
	LETTER,
	/**
	 * 其他字符
	 */
	OTHER,
	/**
	 * 中文字
	 */
	CHINESE;
}
