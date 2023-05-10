package com.core;

/*_
 
 	Vòng đời của thread
 
	  - NEW: Thread được khởi tạo nhưng chưa start

	  - RUNNABLE: Thread được start. Sau start thì thread vẫn chưa chạy liền mà còn
	  chờ cấp phát tài nguyên, do đó chúng ta có thể chia thành 2 trạng thái nhỏ:
	  
	  		+ Ready to Run (chờ đợi cấp phát tài nguyên)
			+ Running (đã chính thức chạy).
	  
	  		- Trong quá trình chạy, nếu có bất kỳ tác động nào, ngoại trừ làm kết thúc
	  		vòng đời của thread (TERMINATED), nó sẽ vào trạng thái dưới đây
	  
	  - BLOCKED: Là trạng thái khi thread nó không có đủ điều kiện để chạy. Ví dụ 1
	  phương thức được đánh dấu synchronized, ở 1 thời điểm chỉ có 1 thread dùng nó
	  và các thread khác sẽ bị rơi vào trạng thái BLOCKED
	  
	  - WAITING: Là trạng thái khi thread phải đợi thread nào đó hoàn thành, với
	  một khoảng thời gian không xác định trước. Trạng thái này khác với BLOCKED. Ở
	  trên kia là các thread bị hệ thống khoá lại khi cùng truy xuất chung đến một
	  tài nguyên hệ thống. Còn trạng thái này là giữa các Thread tự điều đình với
	  nhau.
	  
	  - TIMED_WAITING: A thread that is waiting for another thread to perform an
	  actionfor up to a specified waiting time is in this state.
	  
	  - TERMINATED: A thread that has exited is in this state.
 */
public class ThreadLifeCycle extends Thread {

	@Override
	public void run() {
		System.out.println("Thread running...");
	}

	public static void main(String[] args) {
		Thread t = new ThreadLifeCycle();
		System.out.println(t.getState()); 		// NEW
		t.start();
		System.out.println(t.getState()); 		// RUNNABLE
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(t.getState()); 	// TERMINATED
	}

}
