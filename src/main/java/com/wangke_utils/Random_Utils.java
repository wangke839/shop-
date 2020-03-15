package com.wangke_utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Random_Utils {
	/**
	 * 
	    * @Title: randomDate
	    * @Description:    获取随机日期
	    * @param @param beginDate
	    * @param @param endDate
	    * @param @return    参数
	    * @return Date    返回类型
	    * @throws
	 */
	public static Date randomDate(String beginDate, String endDate){
	  try {
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	   Date start = format.parse(beginDate);// 构造开始日期
	   Date end = format.parse(endDate);// 构造结束日期
	   // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
	   if (start.getTime() >= end.getTime()) {
	    return null;
	   }
	   long date = random(start.getTime(), end.getTime());

	   return new Date(date);
	  } 
	  catch (Exception e) 
	  {
	   e.printStackTrace();
	  }
	  return null;
	 }

	private static long random(long begin, long end) 
	{
	  long rtn = begin + (long) (Math.random() * (end - begin));
	  // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
	  if (rtn == begin || rtn == end) {
	   return random(begin, end);
	  }
	  return rtn;
	 }
	//获取一个范围内随机一个int数，不包括0
	public static int  getRandom(int end){
		Random random = new Random();
		int nextInt = random.nextInt(end);
		while(nextInt<0){
			nextInt = random.nextInt(end);
		}
		return nextInt;
	}
	//获取随机一个channel的id，并获取对应的category的id
	public static int[]  getRandomForCMS(int end){
		Random random = new Random();
		int nextInt = random.nextInt(end);
		int nextInt2 = 1;
		while(nextInt==0){
			nextInt = random.nextInt(end);
		}
		switch (nextInt) {
		case 1:
			nextInt2 = random.nextInt(4);
			while(nextInt2==0){
				nextInt2 = random.nextInt(4);
			}
			break;
		case 2:
			nextInt2 = random.nextInt(9);
			while(nextInt2<4){
				nextInt2 = random.nextInt(9);
			}
			break;
		case 3:
			nextInt2 = random.nextInt(13);
			while(nextInt2<9){
				nextInt2 = random.nextInt(13);
			}
			break;
		case 4:
			nextInt2 = random.nextInt(17);
			while(nextInt2<13){
				nextInt2 = random.nextInt(17);
			}
			break;
		case 5:
			nextInt2 = random.nextInt(21);
			while(nextInt2<17){
				nextInt2 = random.nextInt(21);
			}
			break;
		case 6:
			nextInt2 = random.nextInt(25);
			while(nextInt2<21){
				nextInt2 = random.nextInt(25);
			}
			break;
		case 7:
			nextInt2 = random.nextInt(29);
			while(nextInt2<25){
				nextInt2 = random.nextInt(29);
			}
			break;
		case 8:
			nextInt2 = random.nextInt(32);
			while(nextInt2<29){
				nextInt2 = random.nextInt(32);
			}
			break;

		default:
			break;
		}
		int [] arr = new int[]{nextInt,nextInt2};
		return arr;
	}
}

