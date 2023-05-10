package com.core.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*-
 * - Phương thức invokeAll() gọi tất cả đối tượng Callable trong tập hợp. Phương
 * thức này trả về 1 danh sách các đối tượng Future<T> mà được trả về từ việc
 * thực thi các Callable
 * 
 * 		invokeAll(Collection<?> extends Callable<T> tasks)
 */
public class InvokeAll {

	/**
	 * Thực thi chương trình trên, ta sẽ thấy tất cả các Callable đều được thực thi
	 * và kết quả được lưu vào List<Future>
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Callable<Integer>> callables = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			callables.add(new Worker(i));
		}
		List<Future<Integer>> futures = executor.invokeAll(callables);
		int sum = 0;
		for (Future<Integer> future : futures) {
			sum += future.get();
		}
		System.out.println("Sum all = " + sum);
		System.out.println("Finished all threads ");
		executor.shutdown();
	}

}
