package com.Emma.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertUtils {
	public static String  ConvertDateToString(Date date){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String  format = sdf.format(date);
		return format;
		
	}
	
	public static Date ConvertStringToDate(String str){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date2 = sdf.parse(str);
			return date2;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
