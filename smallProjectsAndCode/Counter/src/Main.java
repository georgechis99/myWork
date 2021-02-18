public class Main {

    public static void main(String[] args) {

        Countdown countdown = new Countdown();

        ThreadCountdown t1 = new ThreadCountdown(countdown);
        t1.setName("Thread 1");
        ThreadCountdown t2 = new ThreadCountdown(countdown);
        t2.setName("Thread 2");

        t1.start();
        t2.start();
    }
}

class Countdown {
    private int i;

    public void doCountdown() {

        String color;

        switch (Thread.currentThread().getName()) {
            case "Thread 1":
                color = Colors.ANSI_PURPLE;
                break;
            case "Thread 2":
                color = Colors.ANSI_CYAN;
                break;
            default:
                color = Colors.ANSI_GREEN;
        }

        synchronized (this) {
            for (i = 10; i > 0; i--) {
                System.out.println(color + Thread.currentThread().getName() + ": i =" + i);
            }
        }
    }
}

class ThreadCountdown extends Thread {

    private Countdown countdown;

    public ThreadCountdown(Countdown countdown) {
        this.countdown = countdown;
    }

    public void run() {
        countdown.doCountdown();
    }
}
