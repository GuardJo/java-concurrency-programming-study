package com.guardjo.practice.java.concurrent.chapter02;

public class ThreadCreator {
    static class WorkerThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }

    static class WorkerTask implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }

    public static void main(String[] args) {
        Thread t1 = new WorkerThread();
        Thread t2 = new Thread(new WorkerTask());
        Thread t3 = new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running"));

        t1.start();
        t2.start();
        t3.start();
        new Thread(() -> System.out.println(Thread.currentThread().getName() + " is running")).start();
    }
}
