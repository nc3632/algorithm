import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 1. Support reentrance
 * 2. Read can happen only there is no pending write or write going
 * 3. Write can only happen when there is no any reads or writes
 */

public class ReadWriteLock {
    private Map<Thread, Integer> readingThreads = new HashMap<>();
    private Thread writingThread = null;
    private int numPendingWrites = 0;
    private int numWrites = 0;

    public synchronized void lockRead() throws InterruptedException {
        Thread currThread = Thread.currentThread();
        while (!canGrantReadAccess(currThread)) {
            wait();
        }

        readingThreads.put(currThread, readingThreads.getOrDefault(currThread, 0) + 1);
    }

    private boolean canGrantReadAccess(Thread callingThread) {
        if (writingThread != null) {
            return callingThread == writingThread;
        } else {
            if (readingThreads.containsKey(callingThread)) {
                return true;
            } else {
                return numPendingWrites == 0;
            }
        }
    }

    public synchronized void unlockRead() {
        Thread currThread = Thread.currentThread();
        int numReads = readingThreads.get(currThread) - 1;
        if (numReads == 0) {
            readingThreads.remove(currThread);
        } else {
            readingThreads.put(currThread, numReads);
        }

        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        numPendingWrites++;

        Thread currThread = Thread.currentThread();
        while (!canGrantWriteAccess(currThread)) {
            wait();
        }

        numPendingWrites--;
        numWrites++;
        writingThread = currThread;
    }

    private boolean canGrantWriteAccess(Thread callingThread) {
        if (writingThread != null) {
            return callingThread == writingThread;
        } else {
            return readingThreads.isEmpty() || readingThreads.size() == 1 && readingThreads.containsKey(callingThread);
        }
    }

    public synchronized void unlockWrite() {
        numWrites--;
        if (numWrites == 0) {
            writingThread = null;
            notifyAll();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock sol = new ReadWriteLock();

        System.out.println("Starting thread write-1 ...");
        new Thread("write-1") {
            public void run() {
                try {
                    sol.lockWrite();

                    System.out.println(getName() + " is sleeping");
                    TimeUnit.SECONDS.sleep(5);

                    System.out.println("This is " + getName());
                } catch (InterruptedException e) {
                } finally {
                    sol.unlockWrite();
                }
            }
        }.start();

        System.out.println("Starting thread read-1 ...");
        new Thread("read-1") {
            public void run() {
                try {
                    sol.lockRead();

                    System.out.println(getName() + " is sleeping");
                    TimeUnit.SECONDS.sleep(1);

                    System.out.println("This is " + getName());
                } catch (InterruptedException e) {
                } finally {
                    sol.unlockRead();
                }
            }
        }.start();

        System.out.println("Starting thread read-2 ...");
        new Thread("read-2") {
            public void run() {
                try {
                    sol.lockRead();
                    System.out.println("This is " + getName());
                } catch (InterruptedException e) {
                } finally {
                    sol.unlockRead();
                }
            }
        }.start();

        System.out.println("Starting thread write-2 ...");
        new Thread("write-2") {
            public void run() {
                try {
                    sol.lockWrite();
                    System.out.println("This is " + getName());
                } catch (InterruptedException e) {
                } finally {
                    sol.unlockWrite();
                }
            }
        }.start();
    }
}
