package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtils {

	private TimeUtils() {

	}

	public static Date now() {
		return new Date();
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

	static class Handle {

		private Date get(int timeType, int amount) {
			Calendar calendar = Calendar.getInstance();
			calendar.add(timeType, amount);
			return calendar.getTime();
		}

		public Date day(int amount) {
			return get(Calendar.DATE, amount);
		}
		
		public Date hour(int amount) {
			return get(Calendar.HOUR, amount);
		}

		public Date minute(int amount) {
			return get(Calendar.MINUTE, amount);
		}

		public Date second(int amount) {
			return get(Calendar.SECOND, amount);
		}

	}

	public static class AfterDateTime extends Handle {

		private AfterDateTime() {

		}

		private static class SingletonHelper {
			private static final AfterDateTime INSTANCE = new AfterDateTime();
		}

		static AfterDateTime getInstance() {
			return SingletonHelper.INSTANCE;
		}

	}

	public static class BeforeDateTime extends Handle {

		private BeforeDateTime() {

		}

		private static class SingletonHelper {
			private static final BeforeDateTime INSTANCE = new BeforeDateTime();
		}

		static BeforeDateTime getInstance() {
			return SingletonHelper.INSTANCE;
		}
	}

//	public static void main(String[] args) {
//		Date after = TimeUtils.after().day(1);
//		Date before = TimeUtils.before().day(1);
//		System.out.println(after);
//		System.out.println(before);
//	}

}
