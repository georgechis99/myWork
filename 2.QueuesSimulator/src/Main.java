import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static int noOfClients;
    static int noOfQueues;
    static int simulationTime;
    static int minTimeArrival;
    static int minTimeService;
    static int maxTimeArrival;
    static int maxTimeService;

    public static void main(String[] args) {
        try {
            File input = new File("src/In-test2.txt");
            FileInputStream in = new FileInputStream(input);
            Scanner s = new Scanner(in);
            noOfClients = s.nextInt();
            noOfQueues = s.nextInt();
            simulationTime = s.nextInt();
            s.nextLine();
            String line = s.nextLine();
            String[] lineIntegers = line.split(",");
            minTimeArrival = Integer.parseInt(lineIntegers[0]);
            maxTimeArrival = Integer.parseInt(lineIntegers[1]);
            line = s.nextLine();
            lineIntegers = line.split(",");
            minTimeService = Integer.parseInt(lineIntegers[0]);
            maxTimeService = Integer.parseInt(lineIntegers[1]);
            ManageWaitingQueues manager = new ManageWaitingQueues(noOfClients,noOfQueues,simulationTime,minTimeArrival,maxTimeArrival,minTimeService,maxTimeService);
            Thread managerThread = new Thread(manager);
            managerThread.start();
        } catch (IOException e) {
            System.out.println("File not found");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Not enough arguments");
        }
    }
}
