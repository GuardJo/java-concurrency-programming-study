package com.guardjo.practice.java.concurrent.chapter05;

public class CustomRunnable implements Runnable {
    private final CustomTestExecutor customTestExecutor;

    public CustomRunnable(CustomTestExecutor customTestExecutor) {
        this.customTestExecutor = customTestExecutor;
    }

    @Override
    public void run() {
        customTestExecutor.test();
    }
}
