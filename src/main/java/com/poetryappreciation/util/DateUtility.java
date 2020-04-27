package com.poetryappreciation.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 方法说明：日期操作类
 * @author  AnHZ
 * @date  2019/7/9
 */
public class DateUtility {

	/** 日期格式 yyyy-MM-dd */
	public static final String DateFormat1 = "yyyy-MM-dd";

	/** 日期格式 yyyy年MM月dd日 */
	public static final String DateFormat2 = "yyyy年MM月dd日";

	/** 日期格式 yyyyMMdd HHmmss */
	public static final String DateFormat3 = "yyyyMMddHHmmss";

	/** 日期格式 yyyyMMdd HH:mm:ss */
	public static final String DateFormat4 = "yyyy-MM-dd HH:mm:ss";

	/** 日期格式 yyyy/MM/dd */
	public static final String DateFormat6 = "yyyyMMdd";

	public static String convertDate(String datestr, String format1, String format2) {

		return getFormatDate(getDateFromStr(datestr, format1), format2);
	}

	/**
	 * 根据日期字符返回日期对象
	 *
	 * @param datestr
	 *            比如：2006-02-03
	 * @param format
	 *            比如yyyy-MM-dd
	 * @return 日期对象
	 */
	public static Date getDateFromStr(String datestr, String format) {
		if (datestr == null || "".equalsIgnoreCase(datestr)) {
			return null;
		}

		SimpleDateFormat dateformat = new SimpleDateFormat(format);
		Date result = null;
		try {
			result = dateformat.parse(datestr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 格式化日期
	 *
	 * @param indate
	 *            日期对象
	 * @param format
	 *            比如yyyy-MM-dd
	 * @return 比如：2006-02-03
	 */
	public static String getFormatDate(Date indate, String format) {
		if (indate == null) {
			return "";
		}

		SimpleDateFormat dateformat = new SimpleDateFormat(format);

		return dateformat.format(indate);
	}

	/**
	 * 得到当前时间
	 *
	 * @return 当前时间
	 */
	public static Date getCurrentTime() {
		return new Date();
	}

	/**
	 * 日期加减年数
	 *
	 * @param inDate
	 *            初始日期
	 * @param addYear
	 *            要加的年数(负值代表减)
	 * @return 结果日期
	 */
	public static Date addYearS(final Date inDate, final int addYear) {

		return addDateS(inDate, addYear, Calendar.YEAR);
	}

	/**
	 * 日期加减月数
	 *
	 * @param inDate
	 *            初始日期
	 * @param addMonth
	 *            要加的月数(负值代表减)
	 * @return 结果日期
	 */
	public static Date addMonthS(final Date inDate, final int addMonth) {

		return addDateS(inDate, addMonth, Calendar.MONTH);
	}

	/**
	 * 日期加减天数
	 *
	 * @param inDate
	 *            初始日期
	 * @param addDay
	 *            要加的天数(负值代表减)
	 * @return 结果日期
	 */
	public static Date addDayS(final Date inDate, final int addDay) {

		return addDateS(inDate, addDay, Calendar.DAY_OF_MONTH);
	}

	/**
	 * 日期加减小时数
	 *
	 * @param inDate
	 *            初始日期
	 * @param addDay
	 *            要加的小时数(负值代表减)
	 * @return 结果日期
	 */
	public static Date addHourS(final Date inDate, final int addDay) {

		return addDateS(inDate, addDay, Calendar.HOUR_OF_DAY);
	}

	/**
	 * 日期加减分钟数
	 *
	 * @param inDate
	 *            初始日期
	 * @param addDay
	 *            要加的小时数(负值代表减)
	 * @return 结果日期
	 */
	public static Date addMinuteS(final Date inDate, final int addMinute) {

		return addDateS(inDate, addMinute, Calendar.MINUTE);
	}

	/**
	 * 日期加减秒数
	 *
	 * @param inDate
	 *            初始日期
	 * @param addSecond
	 *            要加的秒数(负值代表减)
	 * @return 结果日期
	 */
	public static Date addSecondS(final Date inDate, final int addSecond) {

		return addDateS(inDate, addSecond, Calendar.SECOND);
	}

	/**
	 * 计算日期加减
	 *
	 * @param inDate
	 *            初始日期
	 * @param addDate
	 *            要加的日期
	 * @param field
	 *            要加的域
	 * @return 结果日期
	 */
	private static Date addDateS(final Date inDate, final int addDate,
                                 final int field) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(inDate);

		calendar.add(field, addDate);

		return calendar.getTime();
	}

	/**
	 * 计算两个日期之间的天数
	 *
	 * @param fromDate
	 *            开始日
	 * @param toDate
	 *            结束日
	 * @param blnAbs
	 *            是否取绝对值
	 * @return 天数
	 */
	public static int calcDays(Date fromDate, Date toDate, boolean blnAbs) {

		long miliSeconds1 = fromDate.getTime();
		long miliSeconds2 = toDate.getTime();

		if (fromDate.compareTo(toDate) > 0 && blnAbs == false) {
			// 不足2天算1天
			return (int) ((miliSeconds2 - miliSeconds1) / 86400000);
		}

		// 超过1天算2天
		return (int) (Math.abs(miliSeconds2 - miliSeconds1 - 1) / 86400000) + 1;
	}

	/**
	 * 计算两个日期之间的天数 （过一个0点算一天）
	 *
	 * @param fromDate
	 *            开始日期
	 * @param toDate
	 *            结束日期
	 * @return 天数
	 */
	public static int calcDays(Date fromDate, Date toDate) {
		long miliSeconds1 = DateUtility.getFirstTimeOfDay(fromDate).getTime();
		long miliSeconds2 = DateUtility.getFirstTimeOfDay(toDate).getTime();

		return (int) ((miliSeconds2 - miliSeconds1) / 86400000);

	}

	/**
	 * 计算两个日期之间的年数
	 *
	 * @param fromDate
	 *            开始日期
	 * @param toDate
	 *            结束日期
	 * @return 年数
	 */
	public static int calcYears(Date fromDate, Date toDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fromDate);
		int years = 0;
		Date temp = null;
		if (fromDate.before(toDate)) {
			temp = DateUtility.addYearS(fromDate, 1);
			while (!temp.after(toDate)) {
				temp = DateUtility.addYearS(temp, 1);
				years++;
			}
		} else {
			temp = DateUtility.addYearS(fromDate, -1);
			while (!temp.before(toDate)) {
				temp = DateUtility.addYearS(temp, -1);
				years--;
			}
		}

		return years;

	}

	/**
	 * 得到本年的开始时间
	 *
	 * @param date
	 *            日期对象
	 * @return 本年开始时间
	 */
	public static Date getFirstDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 得到本月的开始时间
	 *
	 * @param date
	 *            日期对象
	 * @return 本月开始时间
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 得到本天的开始时间
	 *
	 * @param date
	 *            日期对象
	 * @return 本天的开始时间
	 */
	public static Date getFirstTimeOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	/**
	 * 得到日期的年份
	 *
	 * @param date
	 *            日期对象
	 * @return 日期的年份
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 得到日期的月份 (1-12)
	 *
	 * @param date
	 *            日期对象
	 * @return 日期的月份
	 */
	public static int getMonthOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到日期是年中的第几周
	 *
	 * @param date
	 *            日期对象
	 * @return 年中的第几周
	 */
	public static int getWeekOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 得到日期是一年中的第几天 (1-366)
	 *
	 * @param date
	 *            日期对象
	 * @return 年中的第几天
	 */
	public static int getDayOfYear(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.DAY_OF_YEAR);
	}

	/**
	 * 得到日期是一月中的第几天 (1-31)
	 *
	 * @param date
	 *            日期对象
	 * @return 月中的第几天
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 得到日期是一周中的第几天(星期日开始，是1)
	 *
	 * @param date
	 *            日期对象
	 * @return 周中的第几天
	 */
	public static int getDayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 得到时间是一天中的哪个小时
	 *
	 * @param date
	 *            日期对象
	 * @return 天中的哪个小时
	 */
	public static int getHourOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 得到时间是多少分钟
	 *
	 * @param date
	 *            日期对象
	 * @return 天中的哪个小时
	 */
	public static int getMinuteOfHour(Date date) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 设置小时
	 *
	 * @param date
	 *            日期对象
	 * @return 天中的哪个小时
	 */
	public static Date setHourOfDay(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.HOUR_OF_DAY, hour);

		return calendar.getTime();
	}

	/**
	 * 设置分钟
	 *
	 * @param date
	 *            日期对象
	 * @return 天中的哪个小时
	 */
	public static Date setMinuteOfHour(Date date, int minute) {
		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		calendar.set(Calendar.MINUTE, minute);

		return calendar.getTime();
	}

	/**
	 * 根据输入的毫秒数，得到 "星期几",如“星期二”
	 *
	 * @param date
	 *            日期对象
	 * @return 周中的第几天
	 */
	public static String getWeekDay(Date date) {
		SimpleDateFormat weekFormatter = new SimpleDateFormat("E");

		return weekFormatter.format(date);
	}

	/**
	 * 根据数字得到对应的星期名字
	 *
	 * @param i
	 *            数字
	 * @return 对应的星期名字
	 */
	public static String getWeekName(int i) {
		switch (i) {
		case 1:
			return "星期日";
		case 2:
			return "星期一";
		case 3:
			return "星期二";
		case 4:
			return "星期三";
		case 5:
			return "星期四";
		case 6:
			return "星期五";
		case 7:
			return "星期六";

		default:
			return "无效数字";
		}
	}

	/**
	 * 根据数字得到对应的时间段名字
	 *
	 * @param i
	 *            数字
	 * @return 对应的时间段名字
	 */
	public static String getHourInterzone(int i) {
		switch (i) {
		case 0:
			return "00:00-01:00";
		case 1:
			return "01:00-02:00";
		case 2:
			return "02:00-03:00";
		case 3:
			return "03:00-04:00";
		case 4:
			return "04:00-05:00";
		case 5:
			return "05:00-06:00";
		case 6:
			return "06:00-07:00";
		case 7:
			return "07:00-08:00";
		case 8:
			return "08:00-09:00";
		case 9:
			return "09:00-10:00";
		case 10:
			return "10:00-11:00";
		case 11:
			return "11:00-12:00";
		case 12:
			return "12:00-13:00";
		case 13:
			return "13:00-14:00";
		case 14:
			return "14:00-15:00";
		case 15:
			return "15:00-16:00";
		case 16:
			return "16:00-17:00";
		case 17:
			return "17:00-18:00";
		case 18:
			return "18:00-19:00";
		case 19:
			return "19:00-20:00";
		case 20:
			return "20:00-21:00";
		case 21:
			return "21:00-22:00";
		case 22:
			return "22:00-23:00";
		case 23:
			return "23:00-24:00";

		default:
			return "无效数字";
		}
	}

	/**
	 * @描述: 计算时间差精确到分秒
	 */
	public static Long dateDiff(String startTime, String endTime,
                                String format, String str) {
        // 按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;

        long minAcc=0;
        // 获得两个时间的毫秒时间差异
        try {
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
            day = diff / nd;// 计算差多少天
            hour = diff % nd / nh + day * 24;// 计算差多少小时
            min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
            sec = diff % nd % nh % nm / ns;// 计算差多少秒
            // 输出结果
            System.out.println("时间相差：" + day + "天" + (hour - day * 24) + "小时"+ (min - day * 24 * 60) + "分钟" + sec + "秒。");
            System.out.println("hour=" + hour + ",min=" + min);
            if (str.equalsIgnoreCase("h")) {
                return hour;
            } else if (str.equalsIgnoreCase("m")){
                return min;
            } else{
            	minAcc = hour*60+min;
            	System.out.println("相差时间"+minAcc);
            	return minAcc;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (str.equalsIgnoreCase("h")) {
            return hour;
        }else if(str.equalsIgnoreCase("m")){
            return min;
        } else{
        	minAcc = hour*60+min;
        	System.out.println("相差时间"+minAcc);
        	return minAcc;
        }
    }
	/**
	 * @描述: 获取当前日期在一个月中的第几天
	 */
	public static int getDayOfDateByMonth(){
		Calendar now = Calendar.getInstance();
		int days = now.get(Calendar.DAY_OF_MONTH);
		return days;
	}

	public static void main(String args[]){
//		System.out.println(dateDiff("2015-3-12 14:34:00","2015-3-11 16:00:00",DateFormat4,""));
		System.out.println(getDayOfDateByMonth());
	}
}
