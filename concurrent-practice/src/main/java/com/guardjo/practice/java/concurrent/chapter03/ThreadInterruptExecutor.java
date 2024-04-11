package com.guardjo.practice.java.concurrent.chapter03;

public class ThreadInterruptExecutor {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Thread Running...");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                // InterruptedException에 의해 interrupted 가 초기화됨
                System.out.println("Thread interrupted = " + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                // Interrupt() 메소드 호출로 interrupted 가 true가 됨
                System.out.println("Thread interrupted = " + Thread.interrupted());
            }
            // 앞선 interrupted 조회 시 interrupted() 메소드 호출로 인해 false로 초기화됨
            System.out.println("Thread interrupted = " + Thread.currentThread().isInterrupted());
        });

        thread.start();
        Thread.sleep(5000);
        System.out.println("Request Interrupt");
        thread.interrupt();
    }
}
