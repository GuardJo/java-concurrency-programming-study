package com.guardjo.practice.java.concurrent.chapter05;

public class CriticalSectionTest {
    private static int SHARED_INTEGER = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable r = () -> {
            synchronized (CriticalSectionTest.class) {
                for (int i = 0; i < 10000; i++) { // Critical Section
                    SHARED_INTEGER++;
                }
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("shared integer: " + SHARED_INTEGER);
    }
}
