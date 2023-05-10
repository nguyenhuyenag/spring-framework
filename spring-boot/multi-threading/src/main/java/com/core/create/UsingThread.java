package com.core.create;

/**
 * Có 2 cách tạo thread là `extends Thread` hoặc `implements Runnable`
 */
public class UsingThread extends Thread {

	@Override
	public void run() {
		System.out.println("ThreadName -> " + Thread.currentThread().getName());
		try {
			for (int i = 5; i > 0; i--) {
				System.out.println(i);
				Thread.sleep(100); // thread sleep
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void cach2() {
		Thread task = new Thread() {
			@Override
			public void run() {
				System.out.println("ThreadName -> " + Thread.currentThread().getName());
				try {
					for (int i = 5; i > 0; i--) {
						System.out.println(i);
						Thread.sleep(100); // thread sleep
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		Thread t = new Thread(task);
		t.start();
	}

	public static void cach1() {
		UsingThread myThread = new UsingThread();
		Thread t = new Thread(myThread);
		t.start();
	}

	public static void main(String args[]) {
		System.out.println("ThreadName -> " + Thread.currentThread().getName());
		cach1();
		cach2();
	}

}
