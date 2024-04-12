package com.guardjo.practice.java.concurrent.chapter04;

public class DaemonThreadExample {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.setDaemon(true);

        thread.start();
        System.out.println("MainThread Finished");
    }
}
