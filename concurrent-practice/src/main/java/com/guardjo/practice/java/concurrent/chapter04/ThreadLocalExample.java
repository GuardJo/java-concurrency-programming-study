package com.guardjo.practice.java.concurrent.chapter04;

public class ThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("Test");

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        });

        thread.start();
        thread.join();

        System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
    }
}
