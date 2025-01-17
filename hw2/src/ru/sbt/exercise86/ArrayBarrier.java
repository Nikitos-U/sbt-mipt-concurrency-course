package ru.sbt.exercise86;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.onSpinWait;

public class ArrayBarrier implements Barrier {
    private final int numberOfThreads;
    private volatile int[] array;
    private final AtomicInteger number;

    public ArrayBarrier(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        this.array = new int[numberOfThreads];
        this.number = new AtomicInteger(0);
    }

    @Override
    public void await() {
        int threadNumber = number.getAndIncrement();
        if (threadNumber == 0) {
            array[threadNumber] = 1;
        } else if (array[threadNumber - 1] == 1) {
            array[threadNumber] = 1;
        }
        if (threadNumber == numberOfThreads - 1) {
            array[threadNumber] = 2;
            return;
        }
        while (array[threadNumber + 1] != 2) {
            onSpinWait();
        }
        array[threadNumber] = 2;
    }
}
