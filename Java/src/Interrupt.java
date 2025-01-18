package src;
public class Interrupt extends Thread {
    
    @Override
    public void run() {
        try {
            Thread.sleep(6000);
            System.out.println("Thread is running.....");
        } catch (InterruptedException e) {
            System.out.println("Thread inturrupted "+e);
        };
    }
    public static void main(String[] args) {

        Interrupt t1 = new Interrupt();
        t1.start();
        t1.interrupt();
    }
}
