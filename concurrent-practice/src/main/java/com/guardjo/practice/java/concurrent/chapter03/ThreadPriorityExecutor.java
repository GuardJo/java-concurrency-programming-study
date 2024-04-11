package com.guardjo.practice.java.concurrent.chapter03;

public class ThreadPriorityExecutor {
    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println(Thread.currentThread().getName() + " priority = " + Thread.currentThread().getPriority());
        };

        Thread thread1 = new Thread(r);
        thread1.setPriority(Thread.MAX_PRIORITY);
        Thread thread2 = new Thread(r);
        thread2.setPriority(Thread.MIN_PRIORITY);
        Thread thread3 = new Thread(r);
        thread3.setPriority(Thread.NORM_PRIORITY);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
