public class AnotherThread extends Thread {

    @Override
    public void run() {
        System.out.println(Colors.ANSI_BLUE + "another thread: " + currentThread().getName());

        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            System.out.println(Colors.ANSI_BLUE + "another thread woke me up. I'm awake");
        }

        System.out.println(Colors.ANSI_BLUE + "5 seconds passed and I'm awake now: " + Thread.currentThread().getName());
    }
}
