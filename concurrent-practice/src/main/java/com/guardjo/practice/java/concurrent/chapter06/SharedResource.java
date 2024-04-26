package com.guardjo.practice.java.concurrent.chapter06;

public class SharedResource {
    private final CustomSemaphore semaphore;
    private int sharedNumber = 0;

    public SharedResource(CustomSemaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            semaphore.acquire();
            for (int i = 0; i < 10000000; i++) {
                sharedNumber++;
            }
        } finally {
            semaphore.release();
        }
    }

    public int getSharedNumber() {
        return this.sharedNumber;
    }
}
