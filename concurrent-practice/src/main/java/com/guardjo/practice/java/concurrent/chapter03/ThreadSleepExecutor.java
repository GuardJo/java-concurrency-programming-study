package com.guardjo.practice.java.concurrent.chapter03;

public class ThreadSleepExecutor {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(() -> {
            try {
                System.out.println("Enter after 5 sec");
                Thread.sleep(5000);
                System.out.println("Hello Thread!");
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
                throw new RuntimeException(e);
            }
        });

        sleepThread.start();
    }
}
