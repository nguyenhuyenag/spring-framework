package com.util;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

	public static final long ONE_SECOND				=	Duration.ofSeconds(1).toMillis();
	public static final long ONE_MINUTE				=	TimeUnit.MINUTES.toMillis(1);
	public static final long ONE_HOUR				=	3600000;
	public static final long ONE_DAY				=	86400000;

	public static String getNowByPattern(String pattern) throws DateTimeException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return now.format(formatter);
	}

	public static Date getLaterDate(long amounts) {
		return new Date(System.currentTimeMillis() + amounts);
	}
	
	/**
	 * Thay thế cho Date constructor bị lỗi @Deprecated
	 * @param year, month, date
	 * @return Date
	 */
	public static Date asDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, date);
		return calendar.getTime();
	}
	
}