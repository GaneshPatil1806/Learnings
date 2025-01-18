package LockBro;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockSample {

    // fair = true ensures that the longest-waiting thread gets the lock first
    private final Lock lock = new ReentrantLock(true);

    void accessResource() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " acquired a lock.");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        } finally {
            System.out.println(Thread.currentThread().getName() + " released the lock.");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LockSample lockSample = new LockSample();

        // Create multiple threads to demonstrate lock fairness
        Runnable task = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    lockSample.accessResource();
                }
            }
        };        
        
        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
        }

        System.out.println("All threads have completed execution.");
    }
}