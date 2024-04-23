package com.guardjo.practice.java.concurrent.chapter05;

import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger sum = new AtomicInteger();
        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                sum.getAndIncrement();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 500; i < 1000; i++) {
                sum.getAndIncrement();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long end = System.currentTimeMillis();

        System.out.println("sum = " + sum);
        System.out.println("elapsed = " + (end - start) + "ms");
    }
}
