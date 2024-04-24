package com.guardjo.practice.java.concurrent.chapter05;

public class CustomTestExecutor {
    private String testName;
    private int testCount;

    public CustomTestExecutor(String testName) {
        this.testName = testName;
    }

    public void test() {
        testName = Thread.currentThread().getName();
        testCount++;
        
        System.out.println("Running " + testName + " count : " + testCount);
    }
}
