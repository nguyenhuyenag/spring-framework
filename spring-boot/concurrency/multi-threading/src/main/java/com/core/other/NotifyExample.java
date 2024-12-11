package com.controller.other;

/*-
 	- Khi sử dụng Thread.sleep() method nó sẽ tự động đánh thức sau một khoảng thời
 	gian được chỉ định nếu không bị gián đoạn.

 	- Đối với wait(), khi wait() method được gọi, thread hiện tại sẽ rơi vào trạng
 	thái chờ cho đến khi một thread khác gọi notify() hay notifyAll() trên cùng
 	một object. Vì thế wait() method phải được đặt trong monitor object (là một
 	khối code truy cập vào các object được sử dụng bởi nhiều thread, chúng được
 	bảo vệ bởi synchronized để đảm bảo rằng chỉ có 1 thread truy cập vào object
 	tại cùng một thời điểm).
 */
class Sum implements Runnable {

	public int sum = 0;

	@Override
	public void run() {
		synchronized (this) {
			int i = 0;
			while (i < 100000) {
				sum += i;
				i++;
			}
			notifyAll();
		}
	}

}

public class NotifyExample {

	public static void main(String[] agrs) throws InterruptedException {
		Sum sum = new Sum();
		sum.run();
		synchronized (sum) {
			while (sum.sum == 0) {
				System.out.println("Waiting for ThreadB to complete...");
				sum.wait();
			}
			System.out.println("ThreadB has completed. " + "Sum from that thread is: " + sum.sum);
		}
	}

}
