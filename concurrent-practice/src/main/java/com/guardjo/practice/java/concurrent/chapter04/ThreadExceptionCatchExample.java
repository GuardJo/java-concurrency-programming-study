package com.guardjo.practice.java.concurrent.chapter04;

public class ThreadExceptionCatchExample {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + " Start");
            throw new RuntimeException("This is a test");
        };

        Thread thread1 = new Thread(runnable);

        Thread thread2 = new Thread(runnable);

        Thread.UncaughtExceptionHandler handler = (t, e) -> {
            System.out.println(t.getName() + " caught " + e);
        };

        Thread.setDefaultUncaughtExceptionHandler(handler);

        thread2.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + "의 예외는 " + e);
        });

        thread1.start();
        thread2.start();
    }
}
