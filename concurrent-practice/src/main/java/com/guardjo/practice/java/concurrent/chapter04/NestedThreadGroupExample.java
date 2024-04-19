package com.guardjo.practice.java.concurrent.chapter04;

public class NestedThreadGroupExample {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup group = new ThreadGroup("group");
        ThreadGroup child = new ThreadGroup(group, "child");

        Runnable updateParentPriority = () -> {
            System.out.println(Thread.currentThread().getName() + ": updateParentPriority");
            Thread.currentThread().getThreadGroup().setMaxPriority(3);
        };

        Runnable updateChildPriority = () -> {
            System.out.println(Thread.currentThread().getName() + ": updateChildPriority");
            Thread.currentThread().getThreadGroup().setMaxPriority(2);
        };

        Thread thread = new Thread(group, updateParentPriority);
        Thread thread2 = new Thread(child, updateChildPriority);

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        // 이미 Thread의 작업중에 ThreadGroup의 priority가 변경된건 반영되지 않음
        System.out.println(thread.getName() + " priority = " + thread.getPriority());
        System.out.println(thread2.getName() + " priority = " + thread2.getPriority());

        // ThreadGroup의 priority가 변경된 후에 생성된 Thread들은 제한 사항이 적용됨
        Thread newThread = new Thread(group, () -> {
            System.out.println(Thread.currentThread().getName() + " group = " + Thread.currentThread().getThreadGroup());
        });

        newThread.start();
        newThread.join();

        System.out.println(newThread.getName() + " priority = " + newThread.getPriority());
    }
}
