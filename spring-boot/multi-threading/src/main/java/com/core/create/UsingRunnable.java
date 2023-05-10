package com.core.create;

public class UsingRunnable implements Runnable {

	@Override
	public void run() {
		System.out.println("ThreadName -> " + Thread.currentThread().getName());
		System.out.println("Thread running");
	}

	/**
	 * Không tạo ra thread mới, phương thức run() vẫn thực thi trên chính thread
	 * hiện tại
	 */
	public static void incorrect() {
		System.out.println("ThreadName -> " + Thread.currentThread().getName());
		Runnable task = new UsingRunnable();
		task.run();
	}

	public static void correct() {
		System.out.println("ThreadName -> " + Thread.currentThread().getName());
		Runnable task = new UsingRunnable();
		Thread t = new Thread(task);
		t.start();
	}

	public static void main(String args[]) {
		correct();
		// incorrect();
	}

}
