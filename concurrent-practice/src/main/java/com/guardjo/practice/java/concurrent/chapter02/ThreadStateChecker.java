package com.guardjo.practice.java.concurrent.chapter02;

public class ThreadStateChecker {
    public static void main(String[] args) throws InterruptedException {
        // 공유 자원
        Object lock = new Object();

        // NEW 상태 확인
        Thread thread1 = new Thread(() -> {
            System.out.println("Test");
        });

        // RUNNABLE 상태 확인
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                if (i == Integer.MAX_VALUE - 1) {
                    System.out.println(Thread.currentThread().getName() + " state : " + Thread.currentThread().getState());
                }
            }
        });

        // WAITING 상태 확인
        Thread thread3 = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        // TIMED_WAITING 상태 확인
        Thread thread4 = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // BLOCKED 상태 확인
        Thread thread5 = new Thread(() -> {
            synchronized (lock) {
                System.out.println("Lock");
            }
        });

        Thread lockThread = new Thread(() -> {
            synchronized (lock) {
                while (true) {
                    // lock 계속 점유중
                }
            }
        });

        System.out.println(thread1.getName() + " State : " + thread1.getState());
        thread2.start();

        thread3.start();
        Thread.sleep(1000);
        System.out.println(thread3.getName() + " State : " + thread3.getState());

        thread4.start();
        Thread.sleep(100);
        System.out.println(thread4.getName() + " State : " + thread4.getState());

        lockThread.start();
        Thread.sleep(100);
        thread5.start();
        Thread.sleep(100);
        System.out.println(thread5.getName() + " State : " + thread5.getState());
    }
}
