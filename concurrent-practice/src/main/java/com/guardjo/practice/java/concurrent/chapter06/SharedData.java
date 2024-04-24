package com.guardjo.practice.java.concurrent.chapter06;

public class SharedData {
    private int sharedNumber = 0;
    private final Mutex mutex;

    public SharedData(Mutex mutex) {
        this.mutex = mutex;
    }

    public void sum() {
        try {
            mutex.acquired();
            for (int i = 0; i < 10000000; i++) {
                sharedNumber++;
            }
        } finally {
            // lock 획득 후 예기치 못하게 Exception 발생 활 수 있으니 finally로 lock 해제 유도
            mutex.release();
        }
    }

    public int getSharedNumber() {
        return sharedNumber;
    }
}
