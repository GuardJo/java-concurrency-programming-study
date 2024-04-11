package com.guardjo.practice.java.concurrent.chapter02;

public class ThreadStackDebugger {
    static class MyThread extends Thread {
        public void first() {
            System.out.println(Thread.currentThread().getName() + " : first()");
            second();
        }

        public void second() {
            System.out.println(Thread.currentThread().getName() + " : second()");
        }

        @Override
        public void run() {
            first();
        }
    }

    public static void main(String[] args) {
        Thread thread = new MyThread();
        thread.start();
    }
}
