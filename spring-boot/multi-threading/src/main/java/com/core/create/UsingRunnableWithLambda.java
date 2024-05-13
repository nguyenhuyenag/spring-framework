package com.core.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingRunnableWithLambda {

	public static void main(String[] args) {
		System.out.println("ThreadName -> " + Thread.currentThread().getName());
		ExecutorService executor = Executors.newSingleThreadExecutor();
		String message = "Hello from my parameterized lambda Runnable!";
		Runnable task = () -> {
			System.out.println("ThreadName -> " + Thread.currentThread().getName());
			System.out.println(message);
		};
		executor.execute(task);
		executor.shutdown();
	}

}
