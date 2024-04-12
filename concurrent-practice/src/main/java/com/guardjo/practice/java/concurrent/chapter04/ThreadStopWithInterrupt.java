package com.guardjo.practice.java.concurrent.chapter04;

public class ThreadStopWithInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " is Running...");
            }
        });

        Thread sleepThread = new Thread(() -> {
            boolean isRunning = true;
            while (isRunning) {
                try {
                    System.out.println(Thread.currentThread().getName() + " is Running...");
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    isRunning = false;
                }
            }
        });

        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
        thread.join();
        System.out.println("Thread Stopped");

        sleepThread.start();
        Thread.sleep(1000);
        sleepThread.interrupt();
        sleepThread.join();
        System.out.println("SleepThread Stopped");
    }
}
