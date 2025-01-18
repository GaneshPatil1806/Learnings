package LockBro;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLock {

    private final Lock lock = new ReentrantLock();
    private int balance = 1000;

    public void withdraw(int amount) {
        try {
            // Attempt to acquire the lock within the specified time
            if (lock.tryLock(10000, TimeUnit.MILLISECONDS)) {
                try {
                    if (balance >= amount) {
                        System.out.println(Thread.currentThread().getName() + " is proceeding to withdraw...");
                        Thread.sleep(3000); // Simulate processing time
                        balance -= amount;
                        System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
                    } else {
                        System.out.println(Thread.currentThread().getName() + " - Insufficient balance.");
                    }
                } finally {
                    lock.unlock(); // Ensure the lock is released
                }
            } else {
                System.out.println(Thread.currentThread().getName() + " could not acquire the lock.");
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore the interrupted status
            System.out.println(Thread.currentThread().getName() + " was interrupted.");
        }
    }

    public static void main(String[] args) {
        TryLock tryLock = new TryLock();

        // Create multiple threads to simulate concurrent withdrawals
        Runnable task = () -> tryLock.withdraw(500);

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
            Thread.currentThread().interrupt();
            System.out.println("Main thread was interrupted.");
        }

        System.out.println("Final balance: " + tryLock.balance);
    }
}

/*

Blocking Behavior - Always blocks until the lock is acquired.
Timeout Support - Not supported.
Interruptibility - Cannot interrupt a thread waiting for a lock.
Fairness - No control over thread scheduling (unfair).

wait()
notify()
notifyAll()

lambda function will see 

Thread executors - bro

 */