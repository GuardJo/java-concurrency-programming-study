package com.guardjo.practice.java.concurrent.chapter04;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadStopExample {
    private static final AtomicBoolean running = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (running.get()) {
                System.out.println(Thread.currentThread().getName() + " Running...");
            }
        });

        thread.start();
        Thread.sleep(1000);
        running.set(false);
        thread.join();
        System.out.println("Thread is stopped");
    }
}
