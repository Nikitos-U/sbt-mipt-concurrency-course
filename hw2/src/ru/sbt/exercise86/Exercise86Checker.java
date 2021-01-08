package ru.sbt.exercise86;

import static java.lang.Thread.sleep;

public class Exercise86Checker {
    private static final int numberOfThreads = 10;

    public static void main(String[] args) throws InterruptedException {
        Barrier barrier = new ProtectedCounterBarrier(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            new Thread(() -> {
                foo();
                barrier.await();
                bar();
            }).start();
        }
        sleep(1000);
        System.out.println();
        System.out.println("Let's separate barriers");
        System.out.println();
        Barrier barrier2 = new ArrayBarrier(numberOfThreads);
        for (int i = 0; i < numberOfThreads; ++i) {
            new Thread(() -> {
                foo();
                barrier2.await();
                bar();
            }).start();
        }
    }

    public static void foo() {
        System.out.println("executing foo");
    }

    public static void bar() {
        System.out.println("yep, now i can do bar");
    }

}
