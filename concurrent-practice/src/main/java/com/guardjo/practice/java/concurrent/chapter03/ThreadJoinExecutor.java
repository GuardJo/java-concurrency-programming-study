package com.guardjo.practice.java.concurrent.chapter03;

public class ThreadJoinExecutor {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            System.out.println("Join() Test");
            System.out.println(Thread.currentThread().getName() + " state : " + Thread.currentThread().getState());
            System.out.println(mainThread.getName() + " state : " + mainThread.getState());
        });

        thread.start();

        try {
            System.out.println("Main Thread Start");
            System.out.println("SubThread Join");
            thread.join();
            System.out.println("Main Thread Restart");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
