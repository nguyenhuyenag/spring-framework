package com.controller.barrier.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	private final static Semaphore semaphore = new Semaphore(1);

	public static void main(String[] args) {
		for (int i = 1; i <= 6; i++) {
			WorkerThread worker = new WorkerThread(semaphore, i);
			worker.start();
		}
	}

}
