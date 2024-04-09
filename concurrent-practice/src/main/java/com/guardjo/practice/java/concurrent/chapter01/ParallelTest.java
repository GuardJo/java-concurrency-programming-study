package com.guardjo.practice.java.concurrent.chapter01;

import java.util.ArrayList;
import java.util.List;

public class ParallelTest {
    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();

        List<Integer> tasks = new ArrayList<>();

        for (int i = 0; i < cores; i++) {
            tasks.add(i + 1);
        }

        System.out.println("cores = " + cores);

        System.out.println("순차 처리 수행");
        nonParallelTaskRun(tasks);

        System.out.println();

        System.out.println("병렬 처리 수행");
        parallelTaskRun(tasks);
    }

    /*
    하나의 Thread에서 작업 수행
     */
    private static void nonParallelTaskRun(List<Integer> tasks) {
        long startTime = System.currentTimeMillis();

        int result = tasks.stream()
                .mapToInt((task) -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return task;
                })
                .sum();

        long finishTime = System.currentTimeMillis();

        System.out.println("result : " + result);
        System.out.println("Execute Time : " + (finishTime - startTime) + "ms");
    }

    /*
    병렬성 작업 처리 수행
     */
    private static void parallelTaskRun(List<Integer> tasks) {
        long startTime = System.currentTimeMillis();

        int result = tasks.parallelStream()
                .mapToInt((task) -> {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    return task * task;
                })
                .sum();

        long finishTime = System.currentTimeMillis();

        System.out.println("result : " + result);
        System.out.println("Execute Time : " + (finishTime - startTime) + "ms");
    }
}
