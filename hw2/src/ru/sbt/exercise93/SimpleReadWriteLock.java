package ru.sbt.exercise93;

public class SimpleReadWriteLock implements ReadWriteLock {
    int readers;
    boolean writer;
    Lock readLock, writeLock;

    public SimpleReadWriteLock() {
        writer = false;
        readers = 0;
        readLock = new ReadLock();
        writeLock = new WriteLock();
    }

    @Override
    public Lock readLock() {
        return readLock;
    }

    @Override
    public Lock writeLock() {
        return writeLock;
    }


    class ReadLock implements Lock {
        @Override
        public void lock() {
            synchronized (SimpleReadWriteLock.class) {
                try {
                    while (writer) {
                        SimpleReadWriteLock.class.wait();
                    }
                    readers++;
                } catch (InterruptedException ignored) {
                }
            }
        }

        @Override
        public void unlock() {
            synchronized (SimpleReadWriteLock.class) {
                readers--;
                if (readers == 0) {
                    SimpleReadWriteLock.class.notifyAll();
                }
            }
        }
    }

    protected class WriteLock implements Lock {
        @Override
        public void lock() {
            synchronized (SimpleReadWriteLock.class) {
                try {
                    while (readers > 0) {
                        SimpleReadWriteLock.class.wait();
                    }
                    writer = true;
                } catch (InterruptedException ignored) {
                }
            }
        }

        @Override
        public void unlock() {
            synchronized (SimpleReadWriteLock.class) {
                writer = false;
                SimpleReadWriteLock.class.notifyAll();
            }
        }
    }
}