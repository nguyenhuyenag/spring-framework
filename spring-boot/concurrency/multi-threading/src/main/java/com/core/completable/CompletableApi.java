package com.core.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class CompletableApi {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// runAsync();
		// supplyAsync();
		// handleResult();
		// allOf();
		// anyOf();
		commonPool();
	}

	/**
	 * Chạy bất đồng bộ và không có kết quả trả về
	 */
	public static void runAsync() {
		CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
			System.out.println("ThreadName -> " + Thread.currentThread().getName());
			System.out.println("Asynchronous task is running in a separate thread.");
		});
		// Wait for the asynchronous task to complete
		future.join();
		System.out.println("The asynchronous task has completed.");
	}

	/**
	 * Chạy bất đồng bộ và trả về kết quả
	 */
	public static void supplyAsync() {
		CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			System.out.println("ThreadName -> " + Thread.currentThread().getName());
			return "The result of the asynchronous task";
		});
		// Get the result of the asynchronous task
		String result = future.join();
		System.out.println(result);
	}

	public static int add(int a, int b) {
		int sum = a + b;
		System.out.println(String.format("%d + %d = %d", a, b, sum));
		return sum;
	}

	/*-
	 * Xử lý kết quả trả về
	 * 
	 *  + thenRun()	   -> Làm gì đó khi CompletableFuture hoàn thành (không quan tâm kết quả trả về).
	 *  + thenAccept() -> Xử lý kết quả khi CompletableFuture hoàn thành.
	 *  + handle()	   -> Xử lý kết quả hoặc lỗi khi CompletableFuture hoàn thành.
	 */
	public static void handleResult() throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> add(1, 2), executor);
		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> add(1, 3), executor);
		CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> add(2, 3), executor);
		executor.shutdown();

		f1.thenRun(() -> {
			System.out.println("future1 completed!");
		});

		f2.thenAccept(result -> {
			System.out.println("future2 completed, result = " + result);
		});

		f3.handle((data, error) -> {
			if (error != null) {
				System.out.println("future3 error, error: " + error);
				return null;
			} else {
				System.out.println("future3 completed, result = " + data);
				return data;
			}
		});
	}

//	/**
//	 * Nếu muốn dùng lại kết quả của CompletableFuture thì ta dùng method thenApply
//	 */
//	public static void ex3() throws InterruptedException, ExecutionException {
//		ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//		CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> add(1, 2), executor);
//		future.thenApply(data -> {
//			System.out.println("CompletableFuture 1 done, data = " + data);
//			return CompletableApi.add(2, 3); // (1)
//		}).thenApply(data -> { // (1) value
//			System.out.println("CompletableFuture 2 done, data = " + data);
//			return CompletableApi.add(3, 4); // CompletableFuture 3
//		}).thenAccept(data -> {
//			System.out.println("CompletableFuture 3 done, data = " + data);
//		}).thenRun(() -> {
//			System.out.println("Finished!");
//		});
//		executor.shutdown();
//	}

	/**
	 * allOf(): Bắt sự kiện tất cả các CompletableFuture hoàn thành
	 */
	public static void allOf() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> add(1, 2));
		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> add(2, 3));
		CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> add(3, 4));
		CompletableFuture<Void> allFuture = CompletableFuture.allOf(f1, f2, f3);
		System.out.println(allFuture.get());
		if (allFuture.isDone()) {
			System.out.println("All futures is done");
		} else {
			System.out.println("Futures are not ready");
		}
	}

	/**
	 * anyOf(): Chạy nhiều tác vụ bất đồng bộ cùng một lúc và trả về kết quả của tác
	 * vụ nào hoàn thành trước
	 */
	public static void anyOf() throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> add(1, 2));
		CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> add(2, 3));
		CompletableFuture<Integer> f3 = CompletableFuture.supplyAsync(() -> add(3, 4));
		CompletableFuture<Object> allFuture = CompletableFuture.anyOf(f1, f2, f3);
		System.out.println("Result -> " + allFuture.get());
	}

	/**
	 * Nếu ta không truyền tham số Executor thì nó sẽ mặc định sử dụng pool mặc định
	 * là ForkJoinPool.commonPool() với size là số CPU của máy tính).
	 */
	public static void commonPool() throws InterruptedException {
		System.out.println("ActiveThread (1) -> " + ForkJoinPool.commonPool().getActiveThreadCount());
		CompletableFuture.supplyAsync(() -> add(1, 2));
		CompletableFuture.supplyAsync(() -> add(1, 3));
		CompletableFuture.supplyAsync(() -> add(2, 3));
		System.out.println("ActiveThread (2) -> " + ForkJoinPool.commonPool().getActiveThreadCount());
	}

}
