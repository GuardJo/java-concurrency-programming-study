package com.guardjo.practice.java.concurrent.chapter04;

public class InheritableThreadLocalExample {
    private final static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("Parent");

        Thread thread = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.set("child");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
        });

        thread.start();
        thread.join();

        System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
    }
}
