package com.runnable;

import com.entity.Data;

import javax.annotation.PostConstruct;
import java.util.*;

public class Worker implements Runnable {

    private static List<Data> data = new ArrayList<>();
    private static final Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());

    @PostConstruct
    public void init() {
        for (int i = 0; i < 1_000; i++) {
            data.add(Data.builder()
                    .id(UUID.randomUUID().toString())
                    .time(System.currentTimeMillis())
                    .build());
        }
    }


    @Override
    public void run() {
        // Khi duyệt qua Set, cần đồng bộ hóa:
        synchronized (synchronizedSet) {
            for (String data : synchronizedSet) {
                // System.out.println(data.get);
            }
        }
    }

}
