package com.controller.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*-
 * - Phương thức invokeAny() trả về một Future, nhưng trả về kết quả của một trong
 * những đối tượng Callable. Nó không đảm bảo về kết quả bạn sẽ nhận được từ
 * callable nào, chỉ cần một trong số chúng hoàn thành. Tức là ko cần tất cả các
 * thread hoàn thành, chỉ cần 1 task hoàn thành phương thức get() sẽ nhận được
 * kết quả.
 * 
 * - Nếu 1 trong số task hoàn thành hoặc ném ra 1 ngoại lệ, sẽ dừng luôn ThreadPool
 * 
 * 		invokeAny(Collection<?> extends Callable<T> tasks)
 *
 */
public class InvokeAny {

	/**
	 * Thực thi chương trình trên sẽ in ra các kết quả được trả về từ 1 trong 10
	 * Callable từ danh sách callables. Mỗi lần chạy sẽ nhận được một kết quả khác
	 * nhau
	 */
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		List<Callable<Integer>> callables = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			callables.add(new Worker(i));
		}
		int result = executor.invokeAny(callables);
		System.out.println("Result = " + result);
		System.out.println("Finished all threads ");
		executor.shutdown();
	}

}
