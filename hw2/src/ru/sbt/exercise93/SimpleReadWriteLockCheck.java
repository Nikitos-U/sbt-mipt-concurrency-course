package ru.sbt.exercise93;

import java.util.ArrayDeque;

public class SimpleReadWriteLockCheck {

    public static void main(String[] args) {
        SimpleReadWriteLock simpleReadWriteLock = new SimpleReadWriteLock();
        ArrayDeque<String> testQueue = new ArrayDeque<>();
        for (int i = 0; i < 3; i++){
            testQueue.addFirst("Initial string " + i);
        }
        for (int i = 1; i < 10; i++){
            if (i % 3 == 0) {
                System.out.println("NEW WRITER");
                new Thread(() -> {
                    writeToQueue(simpleReadWriteLock, testQueue);
                }).start();
            } else {
                System.out.println("NEW READER");
                new Thread(() -> {
                    readFromQueue(simpleReadWriteLock, testQueue);
                }).start();
            }
        }
    }

    private static void readFromQueue(SimpleReadWriteLock simpleReadWriteLock, ArrayDeque<String> testQueue) {
        simpleReadWriteLock.readLock.lock();
        System.out.println(1);
        System.out.println(1);
        System.out.println(1);
        System.out.println(testQueue.pollFirst());
        simpleReadWriteLock.readLock.unlock();
    }

    private static void writeToQueue(SimpleReadWriteLock simpleReadWriteLock, ArrayDeque<String> testQueue) {
        simpleReadWriteLock.writeLock.lock();
        testQueue.addFirst("I am Thread and I wrote this down");
        System.out.println("written some text, releasing the lock");
        simpleReadWriteLock.writeLock.unlock();

    }
}
