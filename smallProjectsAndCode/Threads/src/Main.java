public class Main {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Colors.ANSI_PURPLE + "main thread: " + Thread.currentThread().getName());

        AnotherThread t1 = new AnotherThread();
        t1.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(Colors.ANSI_GREEN + "anonymous class thread: " + currentThread().getName());
            }
        }.start();

        Thread rt1 = new Thread(new MyRunnable(){
            @Override
            public void run() {
                super.run();
                System.out.println(Colors.ANSI_RED + "runnable overrided: " + Thread.currentThread().getName());
            }
        });
        rt1.start();
        rt1.join(7500);

        System.out.println(Colors.ANSI_PURPLE + "main thread: " + Thread.currentThread().getName());
    }
}
