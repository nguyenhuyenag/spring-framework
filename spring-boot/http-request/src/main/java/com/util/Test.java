package com.util;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.methods.HttpGet;

public class Test {

	public static void main(String[] args) {
		HttpGet getMethod = new HttpGet("http://localhost:8082/httpclient-simple/api/bars/1");

		int hardTimeout = 5000; // milliseconds
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				getMethod.abort();
//			}
//		};
		Timer timer = new Timer(true);
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				getMethod.abort();
			}
		}, hardTimeout);
	}

}
