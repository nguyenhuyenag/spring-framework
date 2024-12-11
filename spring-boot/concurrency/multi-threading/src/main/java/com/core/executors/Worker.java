package com.controller.executors;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Worker implements Callable<Integer> {

    private final int num;
    private boolean flag;

    public Worker(int num) {
        this.num = num;
    }

//    public Worker(int num, boolean flag) {
//        this.num = num;
//        this.flag = flag;
//    }

    @Override
    public Integer call() throws Exception {
        int result = num * num;
        if (flag) {
            String threadName = Thread.currentThread().getName();
            String out = String.format("%s -> %d ^ %d = %d", threadName, num, num, result);
            System.out.println(out);
        }
        TimeUnit.SECONDS.sleep(1);
        return result;
    }

}
