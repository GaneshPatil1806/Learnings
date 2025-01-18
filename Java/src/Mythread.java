package src;

public class Mythread extends Thread {

    @Override
    public void run() {
        System.out.println("Running in myt1");
        try {
            Thread.sleep(3000); // Pauses the Myt1 t1 for 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        
        // new state
        Mythread t1 = new Mythread();
        System.out.println(t1.getState());

        // runnable running
        t1.start();
        System.out.println(t1.getState());

        System.out.println("Bro main t1 is running");
        // it puts the t1 of currently executing method to sleep i.e the main t1
        Thread.sleep(1000);
        System.out.println(t1.getState());

        // it will wait for the above to finish then only it will execute
        t1.join();
        System.out.println("Hello");
    }

}