package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	private TimeUtils() {

	}

	public static Date now() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	public static BeforeDateTime before() {
		return BeforeDateTime.getInstance();
	}

	public static AfterDateTime after() {
		return AfterDateTime.getInstance();
	}
	
	public static String format(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static void main(String[] args) {
		Date after = TimeUtils.after().day(1);
		Date before = TimeUtils.before().day(1);
		System.out.println(after);
		System.out.println(before);
	}

}
