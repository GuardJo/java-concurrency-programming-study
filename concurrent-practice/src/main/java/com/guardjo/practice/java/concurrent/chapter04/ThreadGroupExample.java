package com.guardjo.practice.java.concurrent.chapter04;

public class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup subGroup = new ThreadGroup(mainGroup, "Sub Group");

        Runnable runnable = () -> {
            ThreadGroup group = Thread.currentThread().getThreadGroup();

            System.out.println(Thread.currentThread().getName() + " in " + group.getName() + " of " + group.getParent().getName());
            System.out.println();
        };

        Thread thread = new Thread(runnable);
        Thread subThread = new Thread(subGroup, runnable, "Sub Thread");

        System.out.println(mainGroup.getName());
        thread.start();
        subThread.start();
    }
}
