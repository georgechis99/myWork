import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        String varFour = "this is private to main()";

        ScopeCheck scopeInstance = new ScopeCheck();
        scopeInstance.useInner();

        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
        System.out.println("varThree is not accessible here" + innerClass.varThree);
//
//        System.out.println("scopeInstance varOne is " + scopeInstance.getVarOne());
//        System.out.println(varFour);
//
//        scopeInstance.timesTwo();
//
//        System.out.println("******************");
//        ScopeCheck.InnerClass innerClass = scopeInstance.new InnerClass();
//        innerClass.timesTwo();






















//        long start = System.currentTimeMillis();
//
//        System.out.println("Enter an integer, you have 10 seconds : ");
//
//        Timer timer = new Timer();
//        int begin = 0;
//        int timeInterval = 1000;
//        timer.schedule(new TimerTask() {
//            int counter = 0;
//            @Override
//            public void run() {
//                System.out.println(counter);
//                counter++;
//                if (counter >= 11){
//                    timer.cancel();
//                    System.out.println("Time is up!");
//                }
//            }
//        }, begin, timeInterval);
//
//        int integer = scanner.nextInt();
//        scanner.nextLine();
//        timer.cancel();
//
//        System.out.println(integer);


    }
}
