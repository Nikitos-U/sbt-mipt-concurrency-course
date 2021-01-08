package ru.sbt.exercise86;

import ru.sbt.exercise93.Lock;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.onSpinWait;

public class TestNTestNSetLock implements Lock {
    private final AtomicBoolean isLocked;

    public TestNTestNSetLock() {
        isLocked = new AtomicBoolean(false);
    }

    @Override
    public void lock() {
        while (true) {
            while (isLocked.get()) {
                onSpinWait();
            }
            if (!isLocked.getAndSet(true)) {
                return;
            }
        }
    }

    @Override
    public void unlock() {
        isLocked.set(false);
    }
}
