package com.guardjo.practice.java.concurrent.chapter05;

public class ThreadSafeTest {
    public static void main(String[] args) {
        executeNonThreadSafe();
        executeThreadSafe();
    }

    public static void executeNonThreadSafe() {
        CustomTestExecutor testExecutor = new CustomTestExecutor("Test1");

        // 동일한 인스턴스를 주입받아 생성되었기에 Thread Safe 하지 않다.
        Thread t1 = new Thread(new CustomRunnable(testExecutor), "non-safe-1");
        Thread t2 = new Thread(new CustomRunnable(testExecutor), "non-safe-2");

        t1.start();
        t2.start();
    }

    public static void executeThreadSafe() {
        // 별도 인스턴스들을 생성하였기에 Thread Safe 하다.
        Thread t1 = new Thread(new CustomRunnable(new CustomTestExecutor("Test1")), "safe-1");
        Thread t2 = new Thread(new CustomRunnable(new CustomTestExecutor("Test2")), "safe-2");

        t1.start();
        t2.start();
    }
}
