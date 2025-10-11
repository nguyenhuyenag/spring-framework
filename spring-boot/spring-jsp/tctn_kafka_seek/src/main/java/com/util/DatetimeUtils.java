package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DatetimeUtils {
	
	public static String format(Date date) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static String formatJSDate(String stringDate) {
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = sdf.parse(stringDate.replace("T", " "));
			return format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
	
	public static Date lastyear() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.YEAR, -1);
	    return cal.getTime();
	}
	
}
