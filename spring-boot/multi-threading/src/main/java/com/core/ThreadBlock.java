package com.core;

public class ThreadBlock implements Runnable {

	public void run() {
		for (int i = 1; i <= 5; i++) {
			System.out.println("I'm " + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("I'm about to stop");
				return;
			}
		}
	}

	/**
	 * The current thread (main thread) waits for the thread t1 to complete
	 */
	public static void main(String[] args) {
		Thread t1 = new Thread(new ThreadBlock());
		t1.start();
		boolean flag = false;	// Chạy chương trình với flag = true, false
		if (flag) {
			try {
				t1.join(); 		// Block main thread and wait t1 to complete
			} catch (InterruptedException ex) {

			}
		}
		System.out.println("I'm " + Thread.currentThread().getName());
	}

}
