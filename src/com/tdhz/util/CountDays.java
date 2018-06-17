package com.tdhz.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CountDays {
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 计算两个字符串日期之前相隔的天数
	 * @param
	 * @return
     */
	public  String getDays(String beginTime,String endTime){
		long  day=0;
		try
		{
			Date beginDate =sdf.parse(beginTime);
			Date endDate= sdf.parse(endTime);
			day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);

		} catch (Exception e)
		{

			e.printStackTrace();
		}
			return day+"";
		}

	
}
