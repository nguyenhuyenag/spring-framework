package com.controller.executors;

import java.util.concurrent.*;

/**
 * Callable tương tự như Runnable, nhưng nó trả về một giá trị từ một thread
 * thông qua phương thức call()
 */
public class CallableExample {

    /*-
        - Kết quả hiển thị không theo thứ tự khi submit vào executor vì chạy bất đồng bộ.

        - Khi gọi f.get() thì nó sẽ block main thread để khi nào đối tượng c1 thực
          hiện xong và trả về kết quả.

        - Trường hợp đối tượng Callable mất quá nhiều thời gian để tính toán số thì
          cả chương trình sẽ bị delay. Giải pháp cho trường hợp này là sử dụng timeout:

        f.get(5, TimeUnit.SECONDS)
     */
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int threads = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < threads; i++) {
            // Worker w = new Worker(i);
            Future<Integer> f = executor.submit(new Worker(i));
            System.out.println("Get -> " + f.get());
        }
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
    }

}
