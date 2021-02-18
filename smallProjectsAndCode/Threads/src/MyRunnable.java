public class MyRunnable implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Colors.ANSI_RED + "runnable class: " + Thread.currentThread().getName() );
    }
}
