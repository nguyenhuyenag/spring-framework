package com.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * - Khi một biến được đánh dấu là volatile, các giá trị của nó được lưu vào bộ
 * nhớ chính trực tiếp, không thông qua bộ nhớ đệm của CPU. Điều này đảm bảo
 * rằng nếu một thread đọc giá trị của biến từ bộ nhớ chính, nó sẽ nhận được giá
 * trị mới nhất, thay vì giá trị trong bộ nhớ đệm của nó.
 * 
 * - Vì vậy, volatile được sử dụng để đảm bảo tính toàn vẹn của dữ liệu trong
 * một ứng dụng đa luồng, đặc biệt là trong các trường hợp khi các biến có thể
 * được truy cập và sửa đổi bởi nhiều thread cùng một lúc. Tuy nhiên, việc sử
 * dụng volatile không đảm bảo tính an toàn của các hoạt động đọc/ghi đa luồng
 * trong mọi trường hợp, do đó các khối lệnh nên được đồng bộ hóa bằng các cơ
 * chế khác như synchronized hoặc Lock khi cần thiết.
 * 
 * - Như đã đề cập ở trên thì chúng ta chỉ sử dụng volatile khi có hai thread
 * trong đó một thread đọc và một thread ghi vào biến volatile còn các trường
 * hợp khác cần sử dụng đến synchronized
 */
public class Volatile {

	// private static int count = 0;
	private static volatile int count = 0;

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(new Maker());
		executor.submit(new Listener());
		executor.shutdown();
	}

	public static class Maker implements Runnable {
		@Override
		public void run() {
			for (int i = 0; i < 6; i++) {
				System.out.println("Maker -> count = " + (i + 1));
				count = i;
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * - Giá trị của biến `count` chỉ được lưu trong bộ nhớ đệm của CPU, và không
	 * được đồng bộ hóa giữa các thread. Do đó, khi một thread thay đổi giá trị của
	 * biến count, thread khác không thể nhận biết được sự thay đổi này, và do đó sẽ
	 * không bao giờ thoát khỏi vòng lặp while.
	 * 
	 * - Khi thêm lệnh in ra màn hình trong vòng lặp của thread Listener, chương
	 * trình sẽ dừng lại do hiện tượng "busy-waiting" (chờ đợi bằng cách lặp vô tận
	 * một phép kiểm tra). Tuy nhiên, việc in ra giá trị của biến count có thể khiến
	 * cho giá trị đó được đọc từ bộ nhớ chính thay vì bộ nhớ đệm của CPU, do đó giá
	 * trị được đọc sẽ là giá trị mới nhất được ghi vào bởi thread Maker
	 */
	public static class Listener implements Runnable {
		@Override
		public void run() {
			System.out.println("Listener -> start");
			while (count < 5) {
				// System.out.println("Listener value = " + value + ", count = " + count);
			}
			System.out.println("Listener -> stop");
		}
	}
}
