package com.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetimeUtils {

	public static String format(Date date) {
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format(date);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return "";
	}

}
