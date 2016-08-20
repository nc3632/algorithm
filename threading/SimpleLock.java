import java.util.concurrent.TimeUnit;

/**
 * Implement a simple lock that support reentrance.
 */

public class SimpleLock {
    private boolean isLocked = false;
    private Thread lockedBy;
    private int count;

    public synchronized void acquire() throws InterruptedException {
        // Need to verify who is the owner of the lock
        // If it was locked by itself, then we need to pass it
        while (isLocked && lockedBy != Thread.currentThread()) {
            wait();
        }
        isLocked = true;
        lockedBy = Thread.currentThread();
        count++;
    }

    public synchronized void release() {
        if (lockedBy == Thread.currentThread()) {
            count--;

            if (count == 0) {
                isLocked = false;
                notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        SimpleLock lock = new SimpleLock();

        System.out.println("Starting thread 1");
        new Thread("thread1") {
            public void run() {
                try {
                    lock.acquire();

                    System.out.println(getName() + " is sleeping ...");
                    TimeUnit.SECONDS.sleep(5);

                    System.out.println("This is " + getName());
                } catch (InterruptedException e) {
                } finally {
                    lock.release();
                }
            }
        }.start();

        System.out.println("Starting thread 2");
        new Thread("thread2") {
            public void run() {
                try {
                    lock.acquire();
                    System.out.println("This is " + getName());
                }catch (InterruptedException e) {
                } finally {
                    lock.release();
                }
            }
        }.start();
    }
}
