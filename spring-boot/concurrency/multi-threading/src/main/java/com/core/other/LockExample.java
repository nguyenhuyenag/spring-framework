package com.core.other;

/*-
 	- wait() là một instance method được sử dụng để đồng bộ hoá. Nó có thể được
 	gọi bởi bất kỳ object nào vì nó đã được định nghĩa sẵn trong Object class.

 	- Tuy nhiên chú ý rằng wait() method chỉ được gọi trong synchronized block. Khi
 	wait() method được gọi, thread hiện tại sẽ rơi vào trạng thái chờ cho đến khi
 	một thread khác đánh thức nó, hoặc tự đánh thức sau một khoảng thời gian được
 	chỉ định

 	- Thread.sleep() là một static method có thể gọi ở bất cứ đâu.
 */
public class LockExample {

	private static Object LOCK = new Object();

	public static void main(String[] args) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("Thread '" + Thread.currentThread().getName() + "' is woken after sleeping for 1 second");
		synchronized (LOCK) {
			LOCK.wait(1000);
			System.out.println("Object '" + LOCK + "' is woken after" + " waiting for 1 second");
		}
	}

}
