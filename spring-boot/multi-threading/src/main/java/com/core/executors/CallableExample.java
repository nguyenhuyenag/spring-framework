package com.core.executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Callable tương tự như Runnable, nhưng nó trả về một giá trị từ một thread
 * thông qua phương thức call()
 */
public class CallableExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		example();
	}

	/**
	 * - Kết quả hiển thị không theo thứ tự khi submit vào executor vì chạy bất đồng
	 * bộ.
	 * 
	 * - Khi gọi f.get() thì nó sẽ block main thread để khi nào đối tượng c1 thực
	 * hiện xong và trả về kết quả.
	 * 
	 * - Trường hợp đối tượng Callable mất quá nhiều thời gian để tính toán số thì
	 * cả chương trình sẽ bị delay. Giải pháp cho trường hợp này là sử dụng timeout:
	 * 
	 * f.get(5, TimeUnit.SECONDS)
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	@SuppressWarnings("unused")
	public static void example() throws InterruptedException, ExecutionException {
		int nThread = 5;
		ExecutorService executor = Executors.newFixedThreadPool(nThread);
		for (int i = 0; i < nThread; i++) {
			Worker w = new Worker(i);
			Future<Integer> f = executor.submit(w);
			// System.out.println("Get -> " + f.get());
		}
		executor.shutdown();
	}

}
