package com.guardjo.practice.java.concurrent.chapter05;

public class SingleThreadTest {
    public static void main(String[] args) throws InterruptedException {
        int sum = 0;

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            sum++;

            Thread.sleep(1);
        }
        long end = System.currentTimeMillis();

        System.out.println("sum = " + sum);
        System.out.println("elapsed = " + (end - start) + "ms");
    }
}
