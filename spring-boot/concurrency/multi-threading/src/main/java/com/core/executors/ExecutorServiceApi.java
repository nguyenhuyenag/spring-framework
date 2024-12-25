package com.core.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/*-
	- Các phương thức của ExecutorService

		+ execute(): Thêm các Runnable vào ThreadPool và khởi chạy chúng theo kiểu
		bất đồng bộ. Do đó không biết được khi nào các Runnable kết thúc.
		
		+ submit(Callable): Tương tự execute() nhưng có trả về kết quả thông qua
		Future.get(). Phương thực get() được thực thi đồng bộ (asynchronous - tức là
		sau khi callable hoàn thành nhiệm vụ kết quả được trả về nó mới được thực thi).
		
		+ submit(Runnable): Tương tự submit(Callable) nhưng trả kết quả về một cách
		đồng bộ. Tức là khi Thread kết thúc thì null mới được trả về thông qua
		Future.get(), nên dù được gọi rất sớm, nhưng chúng chỉ in ra null khi nào
		thread kết thúc.
		
		+ invokeAny(): Tương tự execute() hay submit() nhưng cho phép ta thể truyền
		vào chúng danh sách các Callable. Nếu có bất kỳ Callable nào hoàn thành trong
		danh sách các Callable truyền vào thì ThreadPool sẽ chấm dứt các Thread còn
		lại, dù cho chúng đã được đưa vào Pool và đang chờ thực thi.
		
		+ invokeAll(): Thực thi tất cả các Callable và chờ nhận các kết quả trả về
		của các Callable này thông qua danh sách các đối tượng Future.
		
		+ shutdown(): Tắt executor khi không còn task nào ở bên trong (các task đã
		hoàn thành). Nếu không shutdown thì chương trình sẽ chạy mãi vì luôn có một
		thread kiểm tra task trong executor để thực thi. Lệnh shutdown() giúp đóng
		ThreadPool lại và từ chối nhận thêm task nữa và giúp ES kết thúc sau khi nó 
		hoàn thành nhiệm vụ.
		
		+ shutdownNow(): Cũng có công năng như vậy, chỉ khác phương thức này buộc ES
		kết thúc ngay khi được gọi, lúc này đây các Thread chưa được thực thi sẽ bị
		buộc phải kết thúc theo ES.
*/
public class ExecutorServiceApi {

	private static class MyTask implements Runnable {

		private final String name;

		public MyTask(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			System.out.println(name + " running...");
			try {
				Thread.sleep(1000); // Giả lập thời gian chạy của Runnable mất 1 giây
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(name + " stop");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		execute();
		// submit();
	}

	public static void execute() {
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 1; i <= 10; i++) {
			Runnable r = new MyTask("Thread " + i);
			executor.execute(r);
		}

		// Sau khi thêm task vào queue cần phải đóng ExecutorService  để ngăn chặn rò rỉ tài nguyên.
		// Đóng ExecutorService sẽ không ngay lập tức dừng các task đang chạy mà đợi chúng hoàn thành
		executor.shutdown();

		// Để đợi cho đến khi tất cả các task hoàn thành trước khi tiếp tục, bạn có thể sử dụng
		// executor.awaitTermination()
		try {
			if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
	}

	public static void submit() {
		List<Future<?>> futures = new ArrayList<>(); // Khởi tạo danh sách các Future
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 1; i <= 10; i++) {
			Runnable r = new MyTask("Thread " + i);
			Future<?> f = executor.submit(r);
			futures.add(f); // Từng Future sẽ quản lý một Runnable
		}
		for (Future<?> f : futures) {
			try {
				System.out.println(f.get()); // Khi có thread nào đó kết thúc, get() sẽ trả về null
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		// Sau khi thêm task vào queue cần phải đóng ExecutorService  để ngăn chặn rò rỉ tài nguyên.
		// Đóng ExecutorService sẽ không ngay lập tức dừng các task đang chạy mà đợi chúng hoàn thành
		executor.shutdown();

		// Để đợi cho đến khi tất cả các task hoàn thành trước khi tiếp tục, bạn có thể sử dụng
		// executor.awaitTermination()
		try {
			if (!executor.awaitTermination(1, TimeUnit.SECONDS)) {
				executor.shutdownNow();
			}
		} catch (InterruptedException e) {
			executor.shutdownNow();
		}
	}

}
