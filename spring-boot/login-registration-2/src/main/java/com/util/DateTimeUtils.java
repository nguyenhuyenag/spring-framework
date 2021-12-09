package com.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

	public static final long ONE_SECOND				=	Duration.ofSeconds(1).toMillis();
	public static final long ONE_MINUTE				=	TimeUnit.MINUTES.toMillis(1);
	public static final long ONE_HOUR				=	3600000;
	public static final long ONE_DAY				=	86400000;

//	public static String getCurrentDateTimeByPattern(String pattern) throws DateTimeException {
//		LocalDateTime now = LocalDateTime.now();
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//		return now.format(formatter);
//	}
//	
//	public static String getCurrentYear() {
//		return getCurrentDateTimeByPattern("yyyy");
//	}
//	
//	public static String changeDateFormat(String strDate, String currentPattern, String newPattern) {
//		try {
//			DateFormat sdf = new SimpleDateFormat(currentPattern);
//			Date date = sdf.parse(strDate);
//			sdf = new SimpleDateFormat(newPattern);
//			return sdf.format(date);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return "";
//	}

	/**
	 * Get later date
	 * <pre>
	 * getLaterDate(ONE_HOURS)		= 1 giờ sau
	 * getLaterDate( ONE_DAY )		= 1 ngày sau
	 * </pre>
	 * @param amounts là thời gian tính bằng mili giây
	 * @return Date
	 */
	public static Date getLaterDate(long amounts) {
		return new Date(System.currentTimeMillis() + amounts);
	}
	
	public static String toString(Date date, String pattern) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return now.format(formatter);
	}
	
//	/**
//	 * Thay thế cho Date constructor bị lỗi @Deprecated
//	 * @param year, month, date
//	 * @return Date
//	 */
//	public static Date asDate(int year, int month, int date) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.YEAR, year);
//		calendar.set(Calendar.MONTH, month);
//		calendar.set(Calendar.DATE, date);
//		return calendar.getTime();
//	}
	
}