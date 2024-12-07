package com.core.create;

import lombok.extern.slf4j.Slf4j;

/**
 * Có 2 cách tạo thread là `extends Thread` hoặc `implements Runnable`.
 *
 * Cách implements Runnable được ưa chuộng hơn vì chỉ có thể extends được 1 class.
 */
@Slf4j
public class UsingThread extends Thread {

    private static void showThreadName(String threadName) {
        System.out.println("ThreadName -> " + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        showThreadName(Thread.currentThread().getName());
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(100); // thread sleep
            }
        } catch (InterruptedException e) {
            log.error("Error: {}", e.getMessage());
        }
    }

    public static void cach1() {
        UsingThread myThread = new UsingThread();
        myThread.start(); // Dùng start() mới chính xác
        // myThread.run(); // Không tạo ra luồng mới, chương trình vẫn chạy trên luồng hiện tại
    }

    public static void cach2() {
        Thread task = new Thread(() -> {
            showThreadName(Thread.currentThread().getName());
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println(i);
                    Thread.sleep(100); // thread sleep
                }
            } catch (InterruptedException e) {
                log.error("Exception: {}", e.getMessage());
            }
        });
        task.start();
    }

    public static void main(String[] args) {
        showThreadName(Thread.currentThread().getName());
        // cach1();
        cach2();
    }

}
