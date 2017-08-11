package com.pikia.component.web.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ValidateUtil {

	private static final Logger logger = Logger.getLogger(ValidateUtil.class);

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		logger.info(m.matches() + "---");
		return m.matches();
	}

	public static boolean isEmail(String email) {
		String str = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		logger.info(m.matches() + "---");
		return m.matches();
	}

	public static void main(String[] args) throws IOException {
		 //DOMConfigurator.configure("E:/study/log4j/log4j.xml");//加载.xml文件
		// InputStream inputStream = this.getClass().getClassLoader()
		// .getResourceAsStream("/config/config.properties");
		 PropertyConfigurator.configure("E:/study/log4j/log4j.properties");//加载.properties文件
		System.out.println(ValidateUtil.class.getClassLoader());
		System.out.println(new ValidateUtil().getClass().getClassLoader()
				.getResource("config/config.properties").getPath());
		FileUtil.copyFolder(
				new ValidateUtil().getClass().getClassLoader()
						.getResource("config/config.properties").getPath(), "E:/");
		System.out.println(ValidateUtil.isEmail("121212121212@12-12.co-m.cn"));
	}

	// public static void main(String[] args) {
	// 1.java验证IP地址：
	// Pattern pattern =
	// Pattern.compile("\\b((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\.((?!\\d\\d\\d)\\d+|1\\d\\d|2[0-4]\\d|25[0-5])\\b");
	// Matcher matcher = pattern.matcher("127.400.600.2");
	// //以验证127.400.600.2为例
	// System.out.println(matcher.matches());

	// 2. java验证日期时间，解决润月：
	// Pattern pattern = Pattern
	// .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
	// Matcher matcher = pattern.matcher("209099-02-25 23:59:59");
	// System.out.println(matcher.matches());

	// 3.java验证邮箱格式：

	// Pattern pattern =
	// Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
	// Matcher matcher = pattern.matcher("a@aa.com");
	// System.out.println(matcher.matches());
	// }

}
