package synchronization;

public class Test {
    public static void main(String[] args) {

        Counter count = new Counter();
        MyThread t1 = new MyThread(count);
        MyThread t2 = new MyThread(count);

        t1.start();
        t2.start();

        // as they are not synchronized t1 and t2 might be called at a time 
        // so the counter might be increased only one time by two threads

        // this ensures that both the threads have completed their execution
        try {
            t1.join();
            t2.join();  
        } catch (Exception e){
            // good practice to write like this
            Thread.currentThread().interrupt();
            //System.out.println(e);
        }

        System.out.println(count.getCount());
    }
}

/* 

There are two types of locks
1.Intrinsic - inbuilt used using synchronised keyword
2.Explicit - can control using the class java.util.concurrent.locks
 
The explicit locks gives more control to the user that
lets say there are three children and a notebook and a children 
wants to write in a notebook so which children will write that
would be decided by the user

Write down the disadvantages of synchronization
fairness, blocking, interruptibility, read/write locking
*/