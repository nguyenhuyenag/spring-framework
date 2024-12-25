package com.core;

/**
 * Tạm ngắt (interrupted) thread
 */
public class ThreadInterrupt implements Runnable {

	// Thread.interrupted()
	public void run() {
		for (int i = 1; i <= 10; i++) {
			System.out.println("This is message #" + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				System.out.println(Thread.currentThread().getName() + " interrupted");
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new ThreadInterrupt());
		t1.start();
		Thread.sleep(3000);
		t1.interrupt();
		System.out.println("I'm " + Thread.currentThread().getName());
	}
}
