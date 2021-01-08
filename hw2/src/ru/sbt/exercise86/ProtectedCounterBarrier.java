package ru.sbt.exercise86;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.onSpinWait;

public class ProtectedCounterBarrier implements Barrier {
    private final int numberOfThreads;
    private final TestNTestNSetLock tntnsLock;
    private final AtomicInteger counter;

    public ProtectedCounterBarrier(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        counter = new AtomicInteger(0);
        tntnsLock = new TestNTestNSetLock();
    }

    @Override
    public void await() {
        tntnsLock.lock();
        try {
            counter.incrementAndGet();
        } finally {
            tntnsLock.unlock();
        }
        while (counter.get() < numberOfThreads) {
            onSpinWait();
        }
    }

}
