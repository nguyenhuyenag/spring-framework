package com.core.other;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ea.async.Async;

/**
 * Await CompletableFuture
 */
public class AwaitCompleteFuture {

	public static int add(int a, int b) {
		int sum = a + b;
		System.out.println(a + " + " + b + " = " + sum);
		return sum;
	}

	/**
	 * Thứ tự các dòng in ra sẽ khác nhau do chạy trên các thread khác nhau
	 */
	public static void withoutAsync() {
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletableFuture.supplyAsync(() -> add(1, 2), executor);
		CompletableFuture.supplyAsync(() -> add(2, 3), executor);
		CompletableFuture.supplyAsync(() -> add(3, 4), executor);
		System.out.println("Done");
		executor.shutdown();
	}

	public static void withAsync() {
		Async.init(); // enable async/await, chỉ cần gọi hàm này 1 lần duy nhất trong project
		ExecutorService executor = Executors.newFixedThreadPool(10);
		CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> add(1, 2), executor);
		Async.await(future1); // Chờ future1 thực hiện xong mới chạy tới dòng code tiếp theo
		CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> add(2, 3), executor);
		Async.await(future2);
		CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> add(3, 4), executor);
		Async.await(future3);
		System.out.println("Done");
		executor.shutdown();
	}

	public static void main(String[] args) {
		withAsync();
		// withoutAsync();
	}

}
