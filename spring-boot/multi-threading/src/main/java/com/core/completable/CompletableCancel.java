package com.core.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Hủy bỏ CompletableFuture
 */
public class CompletableCancel {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		AtomicBoolean cancelled = new AtomicBoolean(false);
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			while (true) {
				if (cancelled.get()) {
					return "Cancelled";
				}
				try {
					TimeUnit.SECONDS.sleep(1);
					System.out.println("Thread runing, id = " + Thread.currentThread().getId());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		TimeUnit.SECONDS.sleep(4);
		try {
			future.get(1, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			cancelled.set(true);
		}
		System.out.println(future.get());
	}

}
