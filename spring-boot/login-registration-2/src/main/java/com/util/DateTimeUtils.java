package com.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

	public static final long ONE_SECOND					=	Duration.ofSeconds(1).toMillis();
	public static final long ONE_MINUTE					= 	TimeUnit.MINUTES.toMillis(1);
	public static final long ONE_HOUR					=	3600000;
	public static final long ONE_DAY					=	86400000;
	public static final String MYSQL_DATETIME_PATTERN	=	"yyyy-MM-dd HH:mm:ss";

	public static Date getLaterDate(long amounts) {
		return new Date(System.currentTimeMillis() + amounts);
	}
	
//	public static String dateToString(Date date, String pattern) {
//		SimpleDateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : YYYYMMDDHHMMSS);
//		return sdf.format(date);
//	}
	
	public static Date stringToDate(String date, String pattern) {
		try {
			DateFormat sdf = new SimpleDateFormat(pattern != null ? pattern : MYSQL_DATETIME_PATTERN);
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static boolean lockAttempt(String strDate) {
		Date now = new Date();
		Date date = stringToDate(strDate, MYSQL_DATETIME_PATTERN);
		return now.before(date);
	}
	
}