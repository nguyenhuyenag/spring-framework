//package com.util;
//
//import java.util.Calendar;
//import java.util.Date;
//
//public class BeforeDateTime {
//	
//	private BeforeDateTime() {
//
//	}
//	
//	private static class SingletonHelper {
//		private static final BeforeDateTime INSTANCE = new BeforeDateTime();
//	}
//
//	public static BeforeDateTime getInstance() {
//		return SingletonHelper.INSTANCE;
//	}
//
//	private Date get(int timeType, int amount) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(timeType, -amount);
//		return calendar.getTime();
//	}
//
//	public Date minute(int amount) {
//		return get(Calendar.MINUTE, amount);
//	}
//
//	public Date day(int amount) {
//		return get(Calendar.DATE, amount);
//	}
//
//}
