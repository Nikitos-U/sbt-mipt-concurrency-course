package ru.sbt.exercise93;

public interface ReadWriteLock {
    Lock readLock();
    Lock writeLock();
}
