package src;

public class Hello {
    public static void main(String[] args){
        // System.out.println("hello");
        // System.out.println(Thread.currentThread().getName());

        Word word = new Word();
        word.start();

        for(;;){
            System.out.println("hello "+Thread.currentThread().getName());
        }
    }
}