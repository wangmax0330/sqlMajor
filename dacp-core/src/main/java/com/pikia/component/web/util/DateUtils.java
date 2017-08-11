package com.pikia.component.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.httpclient.methods.GetMethod;

/**
 * 日期格式化类
 * 
 * @author Methew
 * 
 */
public class DateUtils {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
	public static final String DEFAULT_TIMESTAMP_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String getTime(java.util.Date date) {
		DateFormat df = new SimpleDateFormat(DEFAULT_TIMESTAMP_PATTERN);
		if (date != null) {
			String s = df.format(date);
			return s;
		}
		return "";
	}

	public static java.util.Date string2Date(String str) {
		java.util.Date date = null;
		if ((str != null) && (!"".equals(str))) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				date = sdf.parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return date;
	}

	public static String date2Str(java.util.Date date) {
		if (date == null) return "";
		return date2Str(date, "yyyy-MM-dd");
	}

	public static String date2Str(java.util.Date date, String pattern) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 加时间
	 * 
	 * @param date
	 * @param type
	 *            Calendar自带,ps,时,分,秒
	 * @param num
	 * @return
	 */
	public static java.util.Date addTime(java.util.Date date, int type, int num) {
		// Calendar中的Month是从0-11的
		// 取某个时间点后的整点时刻
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		// calendar.add(Calendar.HOUR_OF_DAY, 1);//小时上加1
		calendar.add(type, num);
		return calendar.getTime();
	}

	public static java.util.Date getTime() {
		return null;

	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	public static String getWeek(java.util.Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDaysBetween2Times(String date1, String date2) {
		if (date1 == null || date1.equals("")) return 0;
		if (date2 == null || date2.equals("")) return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDaysBetween2Times(Date begindate, Date enddate) {
		long day = (begindate.getTime() - enddate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 计算当月最后一天,返回字符串
	 * 
	 * @return
	 */
	public static Date getLastDayOfThisMonth() {
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		return lastDate.getTime();
	}

	/**
	 * 今年又多少天
	 * 
	 * @return
	 */
	private static int getTotalDaysOfThisYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	/**
	 * 今年已经过去多少天
	 * 
	 * @return
	 */
	private static int getPastDaysOfThisYear() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);// 今年又多少天
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat(DEFAULT_TIMESTAMP_PATTERN);
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static String datetime2Str(java.util.Date date) {
		if (date == null) return "";
		return date2Str(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String getRelTime(java.util.Date createTime) {
		long n = (System.currentTimeMillis() - createTime.getTime()) / 1000L;
		String val = date2Str(createTime, "yyyy-MM-dd HH:mm:ss");
		if (n < 60L) {
			val = "刚刚";
		} else if (n < 3600L) {
			long $min = new Long(n / 60L).longValue();
			val = $min + "分钟前";
		} else if (n < 86400L) {
			long $h = new Long(n / 3600L).longValue();
			val = $h + "小时前 ";
		} else if (n < 604800L) {
			long $d = new Long(n / 86400L).longValue();
			val = $d + "天前";
		} else if (n < 2592000L) {
			long $d = new Long(n / 604800L).longValue();
			val = $d + "周前";
		} else if (n < 31536000L) {
			long $d = new Long(n / 2592000L).longValue();
			val = $d + "月前";
		} else if (n > 31536000L) {
			long $d = new Long(n / 31536000L).longValue();
			val = $d + "年前";
		}
		return val;
	}

	public static void main(String[] args) {
		System.out.println("今天星期几: " + getWeek(new Date()));
		System.out.println("今年有多少天: " + getTotalDaysOfThisYear());
		System.out.println("今年已经过去多少天: " + getPastDaysOfThisYear());
		System.out.println("当月最后一天: " + date2Str(getLastDayOfThisMonth()));
	}
}
