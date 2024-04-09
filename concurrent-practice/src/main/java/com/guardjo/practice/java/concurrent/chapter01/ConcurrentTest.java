package com.guardjo.practice.java.concurrent.chapter01;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ConcurrentTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int cores = Runtime.getRuntime().availableProcessors() * 2;
        cores = 13;
        List<Integer> tasks = new ArrayList<>();

        for (int i = 0; i < cores; i++) {
            tasks.add(i + 1);
        }

        System.out.println("cores = " + cores);
        System.out.println("코어 별 순차 실행");
        nonConcurrencyTaskRun(tasks);

        System.out.println("코어 별 동시 실행");
        concurrencyTaskRun(tasks);

        System.out.println();
    }

    /*
    병렬 처리
     */
    private static void nonConcurrencyTaskRun(List<Integer> tasks) {
        long startTime = System.currentTimeMillis();
        int result = tasks.parallelStream()
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
    병렬 처리 및 동시 처리
     */
    private static void concurrencyTaskRun(List<Integer> tasks) throws InterruptedException, ExecutionException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(tasks.size());
        List<Callable<Integer>> callables = tasks.stream()
                .map(TestTask::new)
                .collect(Collectors.toUnmodifiableList());

        long startTime = System.currentTimeMillis();
        List<Future<Integer>> futures = executor.invokeAll(callables);
        long finishTime = System.currentTimeMillis();

        int result = 0;
        for (Future<Integer> future : futures) {
            int i = future.get();
            result += i;
        }

        System.out.println("result : " + result);
        System.out.println("Execute Time : " + (finishTime - startTime) + "ms");

        executor.shutdown();
    }

    static class TestTask implements Callable<Integer> {
        private final int taskNum;
        public TestTask(int taskNum) {
            this.taskNum = taskNum;
        }
        @Override
        public Integer call() throws Exception {
            Thread.sleep(500);

            return taskNum;
        }
    }
}
