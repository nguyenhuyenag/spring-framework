package com.core.barrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// A class to represent threads for which the main thread waits. 
class Worker extends Thread {

	private int delay;
	private CountDownLatch latch;

	public Worker(int delay, CountDownLatch latch, String name) {
		super(name);
		this.delay = delay;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			TimeUnit.SECONDS.sleep(delay);
			latch.countDown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " -> finished");
	}

}

public class CountDownLatch2 {

	public static void main(String args[]) throws InterruptedException {
		int count = 4;
		
		final CountDownLatch latch = new CountDownLatch(count);
		
		ExecutorService executor = Executors.newFixedThreadPool(count);
		
		for (int i = 0; i < count; i++) {
			Worker w = new Worker(i, latch, "WORKER-" + (i + 1));
			executor.submit(w);
		}

		executor.shutdown();

		// The main task waits for four threads
		latch.await();

		// Main thread has started
		System.out.println(Thread.currentThread().getName() + " -> finished");
	}
}
