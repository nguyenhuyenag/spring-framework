package com.util;

import java.util.concurrent.TimeUnit;

import com.google.common.base.Stopwatch;

public class TimeWatch {

	public static void wasteTime() {
		Stopwatch watch = Stopwatch.createStarted();
		// delay for 2 seconds
		while (watch.elapsed(TimeUnit.SECONDS) < 1) {
			int i = Integer.MIN_VALUE;
			while (i < Integer.MAX_VALUE) {
				i++;
			}
		}
	}

}
