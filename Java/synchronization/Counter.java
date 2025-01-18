package synchronization;

public class Counter {

    private int count = 0;

    // public synchronized void increament(){
    //     this.count++;
    // }

    public void increament(){
        synchronized(this){
            count++;
        }
    }

    public int getCount(){
        return this.count;
    }
}
