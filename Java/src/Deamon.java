package src;

public class Deamon extends Thread {

    @Override
    public void run() {
        while (true) {
            System.out.println("Thread is running....");
        }
    }
    public static void main(String[] args) {
        Deamon d = new Deamon();
        d.setDaemon(true);
        d.start();
        System.out.println("Main samapt");
    }
}


// USER THREADS: the threads we are actually creating are known as the user threads
// DAEMON THREADS: Background threads - the JVM does not wait for the Deamon threads
