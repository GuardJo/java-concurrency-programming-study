package com.guardjo.practice.java.concurrent.chapter01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class IOBoundedProcess {
    public static void main(String[] args) {
        int nThread = Runtime.getRuntime().availableProcessors() * 2;

        ExecutorService executorService = Executors.newFixedThreadPool(nThread);
        List<Future<?>> futures = new ArrayList<>();

        long start = System.currentTimeMillis();
        for (int i = 0; i < nThread; i++) {
            Future<?> future = executorService.submit(() -> {
                int n = 0;
                // I/O Burst 라고 가정
                for (int j = 0; j < 5; j++) {
                    try {
                        Files.readAllLines(Path.of("C:\\Users\\LDCC\\Desktop\\works\\git\\java-concurrency-programming-study\\concurrent-practice\\src\\main\\resources\\test.txt"));
                        n++;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }

                System.out.println(Thread.currentThread().getName() + " : " + n);
            });

            futures.add(future);
        }

        futures.forEach((t) -> {
            try {
                t.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long finish = System.currentTimeMillis();

        System.out.println("Execute time: " + (finish - start) + "ms");

        executorService.shutdown();
    }
}
