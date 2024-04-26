package com.guardjo.practice.java.concurrent.chapter06;

public class CustomSemaphore {
    private int signal = 0;

    public CustomSemaphore(int signal) {
        this.signal = signal;
    }

    public synchronized void acquire() {
        if (this.signal == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        this.signal--;
        System.out.println(Thread.currentThread().getName() + " : lock 획득, 현재 signal = " + this.signal);
    }

    public synchronized void release() {
        this.signal++;
        System.out.println(Thread.currentThread().getName() + " : lock 해제, 현재 signal = " + this.signal);
        notify();
    }
}
