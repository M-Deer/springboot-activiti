package boot.deer.component.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * @ClassName: DateUtil.java 
 * @Author: Mr_Deer
 * @Date: 2019年4月2日 上午9:38:26 
 * @Description: 日期工具类
 */
public class DateUtil {

	private static final String FORMAT_DATE = "yyyy-MM-dd";
	private static final String FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期转字符串（不包含时分秒）
	 * 
	 * @param date 被格式化日期
	 * @return 格式化后字符串
	 */
	public static String date2String(Date date) {
		if (GlobalUtil.isEmpty(date))
			return null;
		else
			return new SimpleDateFormat(FORMAT_DATE).format(date);

	}

	/**
	 * 日期转字符串（包含时分秒）
	 * 
	 * @param date 被格式化日期
	 * @return 格式化后字符串
	 */
	public static String dateTime2String(Date date) {
		if (GlobalUtil.isEmpty(date))
			return null;
		else
			return new SimpleDateFormat(FORMAT_DATE_TIME).format(date);
	}

	/**
	 * 日期转字符串（自定义格式）
	 * 
	 * @param date 被格式化日期
	 * @return 格式化后字符串
	 */
	public static String date2String(Date date, String format) {
		if (GlobalUtil.isEmpty(date) || GlobalUtil.isEmpty(format))
			return null;
		else
			return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 毫秒数转秒
	 * 
	 * @param milliseconds 被转格式毫秒数
	 * @return 秒
	 */
	public static long milliseconds2Second(long milliseconds) {
		return milliseconds / 1000;
	}

	/**
	 * 毫秒数转分
	 * 
	 * @param milliseconds 被转格式毫秒数
	 * @return 分
	 */
	public static long milliseconds2Minute(long milliseconds) {

		return (milliseconds2Second(milliseconds) / 60);
	}

	/**
	 * 毫秒数转小时
	 * 
	 * @param milliseconds 被转格式毫秒数
	 * @return 小时
	 */
	public static long milliseconds2Hour(long milliseconds) {

		return (milliseconds2Minute(milliseconds) / 60);
	}

}
