package com.core.barrier.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

	private final static Semaphore semaphore = new Semaphore(1);

	public static void main(String[] args) {
		for (int i = 1; i <= 6; i++) {
			com.controller.barrier.semaphore.WorkerThread worker = new com.controller.barrier.semaphore.WorkerThread(semaphore, i);
			worker.start();
		}
	}

}
