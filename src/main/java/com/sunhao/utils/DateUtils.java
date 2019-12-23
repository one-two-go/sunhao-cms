package com.sunhao.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
	//
	
	public static void main(String[] args) {
		
		Date birth = new Date(100, 0, 6);
		int age = calAge(birth);
		System.out.println("age is " + age);
		
		System.out.println("今天的月初是 " + getMonthStart(new Date()));
		
		
		System.out.println("今天的月末是 " + getMonthEnd(new Date(119, 1, 23)));
		
		
	}
	//随机生成时间
	public static Date randomHireday2() {
		int startYear=2019;
		int endYear=2019;
		int year = (int)(Math.random()*(endYear-startYear+1))+startYear;	//随机年
		int month= (int)(Math.random()*12)+1;								//随机月
		Calendar c = Calendar.getInstance();				//创建Calendar对象
		c.set(year, month, 0);								//设定日期
		int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);		//获取对应年月有几天
		int day=(int)(Math.random()*dayOfMonth+1)	;		//产生随机日
		Date hireday=java.sql.Date.valueOf(year+"-"+month+"-"+day);	//通过valueOf方法生成Date对象
		return hireday;
	}
	
	/**
	 * 
	 * @param birthday
	 * @return
	 */
	public static  int calAge(Date birthday) {
		 
		Calendar cal = Calendar.getInstance();  
		cal.setTime(birthday);
		//获取出生的年月日
		int birthYear = cal.get(Calendar.YEAR);
		int birthMonth = cal.get(Calendar.MONTH);
		int birthDate = cal.get(Calendar.DAY_OF_MONTH);
		
		// 获取今天的年月日
		cal.setTime(new Date());
		int currentYear = cal.get(Calendar.YEAR);
		int currentMonth = cal.get(Calendar.MONTH);
		int currentDate = cal.get(Calendar.DAY_OF_MONTH);
		
		int age = currentYear-birthYear;
		if(currentMonth<birthMonth)
			age--;
		else if (currentMonth==birthMonth){
			if(currentDate<birthDate)
				age--;
		}
		return age;
	}
	
	
	/**
	 * 判断是否为今年
	 * @param date
	 * @return
	 */
	public static boolean isThisYear(Date date) {
		SimpleDateFormat smt = new SimpleDateFormat("yyyy");
		
		String dateStr = smt.format(date);
		String todayStr = smt.format(new Date());
		return dateStr.equals(todayStr);
	}
	
	
	/** 
	 *  
	 * 给定时间对象，初始化到该月初的1日0时0分0秒0毫秒
	 * 例如  给定 2017-08-23 13:24:16  返回 2017-08-01 00:00:00 
	 * @return
	 */
	public static Date getMonthStart(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//设置0秒
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM,Calendar.AM);
		cal.set(Calendar.DAY_OF_MONTH,1);
		return cal.getTime();
	}
	
	/**
	 * 求月末
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//设置0秒
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.AM_PM,Calendar.AM);
		cal.set(Calendar.DAY_OF_MONTH,1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.SECOND, -1);
		return cal.getTime();
	}

	/**
	 * 一天有多少毫秒
	 */
	static final long millionSecondsPerDay = 1000 * 60 * 60 * 24;

	/**
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int compare(Date date1, Date date2) {

		if (date1 == null || date2 == null)
			throw new RuntimeException("参数不能为空");

		return date1.compareTo(date2);
	}

	/**
	 * 计算年龄
	 *
	 * @return
	 */
	public static int calculateAge(Date birthday) {

		if (birthday.compareTo(new Date()) > 0) {
			throw new RuntimeException("给定的生日不能大于当前日期 " + birthday);
		}

		// 计算生日的 年 月 日
		Calendar canlendar = Calendar.getInstance();
		canlendar.setTime(birthday);

		int bdYear = canlendar.get(Calendar.YEAR);// 出生的年份
		int bdMonth = canlendar.get(Calendar.MONTH);// 出生的月份
		int bdDay = canlendar.get(Calendar.DAY_OF_MONTH);// 出生的日

		System.out.println(" bdYear: " + bdYear + " bdMonth:" + bdMonth + " bdDay: " + bdDay);

		// 计算当前日期 年 月 日
		canlendar.setTime(new Date());
		int currentYear = canlendar.get(Calendar.YEAR);// 当前的年份
		int currentMonth = canlendar.get(Calendar.MONTH);// 当前的月份
		int currentDay = canlendar.get(Calendar.DAY_OF_MONTH);// 当前的日

		System.out.println(
				" currentYear: " + currentYear + " currentMonth:" + currentMonth + " currentDay: " + currentDay);

		int age = currentYear - bdYear;
		if (currentMonth < bdMonth) {
			age--;
		} else if (currentMonth == bdMonth && currentDay < bdDay) {
			age--;
		}

		return age;
	}

	/**
	 * 计算到将来的一个日期 还剩余多少天
	 *
	 * @param futureDate
	 *            未来的某一天
	 * @return
	 * @throws CmsException
	 */
	public static int remainDays(Date futureDate) throws CmsException {
		/**
		 * 给定的参数是否合法，小于当前日期则认为不合法，抛出异常
		 */
		if (futureDate.compareTo(new Date()) < 0) {
			throw new CmsException("未来日期不能小于当前日期 ： " + futureDate);
		}

		// 计算有多少天
		int days = (int) ((futureDate.getTime() - new Date().getTime()) / millionSecondsPerDay);

		return days;

	}

	/**
	 * 判断是否为今天
	 *
	 * @param date
	 * @return
	 */
	public static boolean isToday(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 获取日期的字符串
		String dateStr = sdf.format(date);

		// 获取日期的字符串
		String todayStr = sdf.format(new Date());

		return (dateStr.equals(todayStr));

	}

	/**
	 * 判断是否在本周
	 * @param date
	 * @return
	 */
	public static boolean isThisWeek(Date date) {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());

		firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);

		firstDayOfWeek.add(Calendar.DATE, -day + 1 + 1);// 后面的+1是因为从周日开始

		// 本周一的日期

		System.out.println(format.format(firstDayOfWeek.getTime()));

		Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());

		lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);

		day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);

		lastDayOfWeek.add(Calendar.DATE, 7 - day + 1);

		// 本周星期天的日期

		System.out.println(format.format(lastDayOfWeek.getTime()));

		return (date.getTime() < lastDayOfWeek.getTime().getTime()
				&& date.getTime() > firstDayOfWeek.getTime().getTime());

	}

	/**
	 *
	 * @param date
	 * @return
	 */
	public boolean isThisMonth(Date date) {
		// 自己去完成吧。。。

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// 获取日期的字符串
		String dateStr = sdf.format(date);

		// 获取日期的字符串
		String todayStr = sdf.format(new Date());

		return (dateStr.equals(todayStr));
	}



	/**
	 *  给定时间对象，初始化到该月初的1日0时0分0秒0毫秒
	 *  获取月初的时间  BOM（begin of the month）
	 * @param date
	 * @return
	 *
	 */
	public static Date getBOM(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();

	}

	/**
	 *  先求出下一个月的月初  然后减一秒。就是本月月末的时间
	 *
	 * @param date
	 * @return
	 */
	public static Date getEOM(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.SECOND, -1);

		return calendar.getTime();

	}


}
