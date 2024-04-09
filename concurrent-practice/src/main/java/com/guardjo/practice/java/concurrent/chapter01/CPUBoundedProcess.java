package com.guardjo.practice.java.concurrent.chapter01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CPUBoundedProcess {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();

        ExecutorService executorService = Executors.newFixedThreadPool(cores);
        List<Future<?>> futures = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < cores; i++) {
            Future<?> future = executorService.submit(() -> {
                int n = 0;
                
                // CPU Burst 라고 가정
                for (int j = 0; j < 100000000; j++) {
                    n++;
                }

                System.out.println(Thread.currentThread().getName() + " : " + n);

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            futures.add(future);
        }

        futures.forEach((f) -> {
            try {
                f.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long endTime = System.currentTimeMillis();

        System.out.println("Execute Time : " + (endTime - startTime) + "ms");

        executorService.shutdown();
    }
}
