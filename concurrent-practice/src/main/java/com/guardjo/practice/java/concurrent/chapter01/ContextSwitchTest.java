package com.guardjo.practice.java.concurrent.chapter01;

public class ContextSwitchTest {
    public static void main(String[] args) {
        Thread thread1 = makeThread("Thread 1");
        Thread thread2 = makeThread("Thread 2");
        Thread thread3 = makeThread("Thread 3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    private static Thread makeThread(String name) {
        return new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                System.out.println(name + " : " + i);
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
