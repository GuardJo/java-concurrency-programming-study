package com.guardjo.practice.java.concurrent.chapter04;

public class ThreadAllInterruptExecutor {
    public static void main(String[] args) throws InterruptedException {
        Runnable exceptionRun = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + " is Running...");
            }

            System.out.println(Thread.currentThread().getName() + " is Stopping...");
        };

        Thread thread = new Thread(exceptionRun);
        Thread thread2 = new Thread(exceptionRun);

        thread.start();
        thread2.start();
        Thread.sleep(1000);
        Thread.currentThread().getThreadGroup().interrupt();
        System.out.println("All Interrupted!");
    }
}
