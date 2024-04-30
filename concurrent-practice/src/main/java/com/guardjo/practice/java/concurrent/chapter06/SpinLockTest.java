package com.guardjo.practice.java.concurrent.chapter06;

public class SpinLockTest {
    public static void main(String[] args) {
        SpinLock spinLock = new SpinLock();

        Runnable r = () -> {
            spinLock.lock();
            System.out.println(Thread.currentThread().getName() + " : Lock 획득");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                System.out.println(Thread.currentThread().getName() + " : Lock 해제");
                spinLock.unlock();
            }
        };

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(r);

        t1.start();
        t2.start();
        t3.start();
    }
}
