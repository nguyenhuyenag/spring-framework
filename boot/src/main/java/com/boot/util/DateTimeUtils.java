package com.boot.util;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTimeUtils {

	public static final long ONE_SECONDS			=	Duration.ofSeconds(1).toMillis();
	public static final long ONE_MINUTES			=	TimeUnit.MINUTES.toMillis(1);
	public static final long ONE_HOURS				=	60 * ONE_MINUTES;
	public static final long ONE_DAYS				=	24 * ONE_HOURS;

	public static final String HH_MM_SS				=	"HH:mm:ss";
	public static final String YYYY_MM_DD			=	"yyyy-MM-dd";
	public static final String YYYY_MM_DD_HH_MM_SS	=	"yyyy-MM-dd HH:mm:ss";

	/**
	 * Get current date time
	 * @param pattern
	 * @return Current date time
	 * @throws DateTimeException
	 */
	public static String getCurrentDateTimeByPattern(String pattern) throws DateTimeException {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return now.format(formatter);
	}

	public static String getCurrentDateTime() {
		return getCurrentDateTimeByPattern(YYYY_MM_DD_HH_MM_SS);
	}

	/**
	 * Get later date
	 * <pre>
	 * getLaterDate(ONE_HOURS)		= 1 giờ sau
	 * getLaterDate( ONE_DAYS )		= 1 ngày sau
	 * </pre>
	 * @param amounts là thời gian tính bằng mili giây
	 * @return Date
	 */
	public static Date getLaterDate(long amounts) {
		return new Date(System.currentTimeMillis() + amounts);
	}
	
}