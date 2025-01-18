package src;

public class Yield extends Thread {
    
    @Override
    public void run() {
        for(int i=0;i<100;i++){
            System.out.println(Thread.currentThread().getName());

            // it gives the hint to the CPU that the current method is willing
            // to give up the current use of CPU

            Thread.yield();
        }
    }
    public static void main(String[] args) {

        Yield t1 = new Yield();
        Yield t2 = new Yield();

        t1.start();
        t2.start();
    }
}