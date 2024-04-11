package com.guardjo.practice.java.concurrent.chapter03;

public class ThreadInfoExecutor {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " Running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "New Thread");

        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " Running...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread2.setName("New Thread2");

        thread1.start();
        thread2.start();

        System.out.println(thread1.getName() + " alive = " + thread1.isAlive());
        System.out.println(thread2.getName() + " alive = " + thread2.isAlive());
        
        System.out.println(Thread.currentThread().getName() + " Running...");
    }
}
