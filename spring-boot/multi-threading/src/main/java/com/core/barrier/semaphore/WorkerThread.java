package com.core.barrier.semaphore;

import java.util.concurrent.Semaphore;

/*-
	- Semaphore là một cơ chế giúp quản lý các nguồn chia sẻ và đảm bảo access không bị tắc nghẽn.

	- Có hai loại semaphore: binary semaphore và counting semaphore.

		+ Binary semaphore (Mutex): Dùng làm lock vì nó chỉ có 2 giá trị là 0 và 1 (lock/unlock).
		
		+ Counting semaphore: Đếm resource để cho biết mức độ sẵn sàng của resource

				// Tạo một Semaphore có 5 ticket
				Semaphore semaphore = new Semaphore(5);
				
				// Yêu cầu lấy 1 ticket để sử dụng
				semaphore.acquire(); // 5 - 1 = 4
				
				// Đếm về số lượng ticket có sẵn
				int numberOfAvailableTickets = semaphore.availablePermits(); // 4
				
				// Trả 1 ticket về Semaphore
				semaphore.release(); // 4 + 1 = 5
				
				// Đếm về số lượng ticket có sẵn
				semaphore.availablePermits(); // 5
	
	- Mutex là một Semaphore với bộ đếm là 1
*/
public class WorkerThread extends Thread {

	private final Semaphore semaphore;
	private int name;

	public WorkerThread(Semaphore semaphore, int name) {
		this.semaphore = semaphore;
		this.name = name;
	}

	@Override
	public void run() {
		try {
			System.out.println("AMT " + name + ": Locked");
			System.out.println("AMT " + name + ": Available Semaphore: " + semaphore.availablePermits());
			semaphore.acquire();
			System.out.println("AMT " + name + ": Got the permit!");
			System.out.println("AMT " + name + ": Available Semaphore: " + semaphore.availablePermits());
			try {
				System.out.println("AMT " + name + ": Do something...");
				Thread.sleep(2000);
			} finally {
				semaphore.release();
				System.out.println("AMT " + name + ": Release");
				System.out.println("AMT " + name + ": Available Semaphore: " + semaphore.availablePermits());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
