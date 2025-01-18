package src;

public class setPrior extends Thread {

    setPrior(String name){
        super(name);
    }

    @Override
    public void run() {
        try {
            for(int i=0;i<10000;i++){
                System.out.println(this.getName() + " priority is " + this.getPriority());
            }
        } catch (Exception e) {
            
        }
    }
    public static void main(String[] args) {

        setPrior t1 = new setPrior("h1");
        t1.setPriority(Thread.MAX_PRIORITY);

        setPrior t2 = new setPrior("h2");
        t2.setPriority(Thread.NORM_PRIORITY);

        setPrior t3 = new setPrior("h3");
        t3.setPriority(Thread.MIN_PRIORITY);

        t1.start();
        t2.start();
        t3.start();
        // System.out.println(t2.getName() + " priority is " + t2.getPriority());
        // System.out.println(t3.getName() + " priority is " + t3.getPriority());
    }
}
