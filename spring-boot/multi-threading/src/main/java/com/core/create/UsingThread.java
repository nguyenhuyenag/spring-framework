package com.core.create;

/**
 * Có 2 cách tạo thread là `extends Thread` hoặc `implements Runnable`
 * <p>
 * Cách implements Runnable được ưa chuộng hơn vì chỉ có thể extends được 1 class
 */
public class UsingThread extends Thread {

    @Override
    public void run() {
        System.out.println("ThreadName -> " + Thread.currentThread().getName());
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(i);
                Thread.sleep(100); // thread sleep
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void cach1() {
        UsingThread myThread = new UsingThread();
        myThread.start(); // Dùng start() mới chính xác
        // myThread.run(); // Không tạo ra luồng mới, chương trình vẫn chạy trên luồng hiện tại
    }

    public static void cach2() {
        Thread task = new Thread(() -> {
            System.out.println("ThreadName -> " + Thread.currentThread().getName());
            try {
                for (int i = 5; i > 0; i--) {
                    System.out.println(i);
                    Thread.sleep(100); // thread sleep
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        task.start();
    }

    public static void main(String[] args) {
        System.out.println("ThreadName -> " + Thread.currentThread().getName());
        cach1();
        // cach2();
    }

}
