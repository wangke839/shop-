package com.wangke_utils;

import java.text.SimpleDateFormat;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.wangke_utils.wrong.MyException;

public class Date_Utils {

	/**
	 * 
	    * @Title: compareDate
	    * @Description: 比较两个日期
	    * @param @param d1
	    * @param @param d2
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public static int compareDate(Date d1,Date d2){
		if(d1 == null || d2 == null ){
			throw new RuntimeException("参数不能为空");
		}
		return d1.compareTo(d2);
	}
	/**
	 * @throws MyException 
	 * 
	    * @Title: getAge
	    * @Description: 由出生日期获得年龄  
	    * @param @param birthday
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public static int getAge(Date birthday) throws MyException{
		if(birthday.compareTo(new Date())>0){
			throw new MyException("不能大于当前系统时间"+birthday);
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);
//		出生年
		int birthYear = calendar.get(Calendar.YEAR);
//		出生月
		int birthMonth = calendar.get(Calendar.MONTH);
//		出生日
		int birthDay = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("出生年："+birthYear+"出生月： "+birthMonth+"出生日:"+birthDay);
		calendar.setTime(new Date());
//		当前年
		int nowYear = calendar.get(Calendar.YEAR);
//		当前月
		int nowMonth = calendar.get(Calendar.MONTH);
//		当前日
		int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("当前年："+nowYear+"当前月： "+nowMonth+"当前日:"+nowDay);
		int age = nowYear-birthYear;
		if(nowMonth < birthMonth){
			age --;
		}else if(nowMonth == birthMonth && nowDay < birthDay){
			age --;
		}
		return age;
	}
	/**
	 * @throws MyException 
	 * 
	    * @Title: remainDays
	    * @Description:计算到将来的一个日期 还剩余多少天
	    * @param @param futureDate
	    * @param @return    参数
	    * @return int    返回类型
	    * @throws
	 */
	public static int remainDays(Date futureDate) throws MyException{
		if(futureDate.compareTo(new Date())<0){
			throw new MyException("不能小于当前系统时间"+futureDate);
		}
		final  long  oneDayMS = 1000*60*60*24;
		int days =(int) ((futureDate.getTime() - new Date().getTime())/oneDayMS);
		return days;
	}
	/**
	 * 
	    * @Title: isToday
	    * @Description: 3.4.4	判断是否为当天
	    * @param @param date
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public static boolean isToday(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (sdf.format(date).toString().equals(sdf.format(new Date()).toString())) {
		// 格式化为相同格式
					return true;
				} else {
					return false;
				}
}
	/**|
	 * 
	    * @Title: isThisWeek
	    * @Description: 3.4.5	判断是否在本周
	    * @param @param date
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public static boolean isThisWeek(Date date) {
		
	    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
	    Calendar firstDayOfWeek = Calendar.getInstance(Locale.getDefault());
	    firstDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);
	    int day = firstDayOfWeek.get(Calendar.DAY_OF_WEEK);
	    firstDayOfWeek.add(Calendar.DATE, -day+1+1);// 后面的+1是因为从周日开始
	    // 本周一的日期
	    System.out.println(format.format(firstDayOfWeek.getTime()));
	     
	    Calendar lastDayOfWeek = Calendar.getInstance(Locale.getDefault());
	    lastDayOfWeek.setFirstDayOfWeek(Calendar.MONDAY);
	    day = lastDayOfWeek.get(Calendar.DAY_OF_WEEK);
	    lastDayOfWeek.add(Calendar.DATE, 7-day+1);
	    // 本周星期天的日期
	    System.out.println(format.format(lastDayOfWeek.getTime()));
	    
	    return (date.getTime()<lastDayOfWeek.getTime().getTime() && date.getTime()>firstDayOfWeek.getTime().getTime() );
	}
	/**
	 * 
	    * @Title: isThisMonth
	    * @Description: 3.4.6	判断是否在本月
	    * @param @param date
	    * @param @return    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public static boolean isThisMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int getMonth = calendar.get(Calendar.MONTH);
		calendar.setTime(new Date());
		int thisMonth = calendar.get(Calendar.MONTH);
		if(getMonth == thisMonth){
			return true;
		}
		
		return false;
	}

	/**
	 * 
	    * @Title: initDate
	    * @Description: 3.4.7	给定时间对象，初始化到该月初的1日0时0分0秒0毫秒
	    * @param @param date
	    * @param @return    参数
	    * @return Date    返回类型
	    * @throws
	 */
	public static Date initDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		return calendar.getTime();
	}
	/**
	 * 
	    * @Title: endDate
	    * @Description: 先求出下一个月的月初  然后减一秒。就是本月月末的时间
	    * @param @param date
	    * @param @return    参数
	    * @return Date    返回类型
	    * @throws
	 */
	public static Date endDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.SECOND, -1);
		
		return calendar.getTime();
	}
	
	
}
