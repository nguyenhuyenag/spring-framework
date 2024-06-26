package com.core.barrier;

import lombok.RequiredArgsConstructor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatch1 {

    @RequiredArgsConstructor
    private static class Service1 extends Thread {

        private final CountDownLatch countDownLatch;

        @Override
        public void run() {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Thread 1 done, Id = " + Thread.currentThread().getId());
            this.countDownLatch.countDown();
        }

    }

    @RequiredArgsConstructor
    private static class Service2 extends Thread {

        private final CountDownLatch countDownLatch;

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Thread 2 done, Id = " + Thread.currentThread().getId());
            this.countDownLatch.countDown();
        }

    }

    @RequiredArgsConstructor
    private static class Service3 extends Thread {

        private final CountDownLatch countDownLatch;

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            System.out.println("Thread 3 done, Id = " + Thread.currentThread().getId());
            this.countDownLatch.countDown();
        }

    }

    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(3);

        Thread t1 = new Thread(new Service1(latch));
        Thread t2 = new Thread(new Service2(latch));
        Thread t3 = new Thread(new Service3(latch));

        t1.start();
        t2.start();
        t3.start();

        try {
            System.out.println("Waiting for 3 services have started ... ");
            latch.await(30, TimeUnit.SECONDS); //
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Starting main Thread, id = " + Thread.currentThread().getId());
    }

}
