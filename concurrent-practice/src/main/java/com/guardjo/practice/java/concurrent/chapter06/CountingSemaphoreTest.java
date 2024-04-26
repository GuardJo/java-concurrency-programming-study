package com.guardjo.practice.java.concurrent.chapter06;

import java.util.Arrays;

public class CountingSemaphoreTest {
    public static void main(String[] args) {
        CustomSemaphore binarySemaphore = new CustomSemaphore(10);
        SharedResource resource = new SharedResource(binarySemaphore);

        Thread[] threads = new Thread[20];

        Arrays.stream(threads).map(t -> new Thread(resource::run)).forEach(Thread::start);
    }
}
