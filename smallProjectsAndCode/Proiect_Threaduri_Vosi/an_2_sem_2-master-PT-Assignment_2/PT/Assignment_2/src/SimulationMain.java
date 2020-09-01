import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;

public class SimulationMain {
  static int noOfClients;
  static int noOfQueues;
  static int finalTime;
  static int minTimeArrival;
  static int minTimeService;
  static int maxTimeArrival;
  static int maxTimeService;
  static FileWriter out;

  /** Generates the clients and starts the simulation thread. */
  public static void initSimulation() {
    Random randomGenerator = new Random();
    PriorityQueue<Client> clientQueue = new PriorityQueue<>(noOfClients, new Client());
    for (int i = 0; i < noOfClients; i++) {
      clientQueue.add(
          new Client(
              i + 1,
              randomGenerator.nextInt(maxTimeArrival - minTimeArrival) + minTimeArrival,
              randomGenerator.nextInt(maxTimeService - minTimeService) + minTimeService));
    }
    Manager manager = new Manager(out, finalTime, noOfQueues, clientQueue);
    Thread managerThread = new Thread(manager);
    managerThread.start();
  }

  /**
   * Reads opens the files and fetches the simulation configuration.
   *
   * @param args the program arguments
   */
  public static void main(String[] args) {
    try {
      FileInputStream in = new FileInputStream("in-text-1.txt");
      out = new FileWriter(new File("src/output_texting.txt"));
      Scanner s = new Scanner(in);
      noOfClients = s.nextInt();
      noOfQueues = s.nextInt();
      finalTime = s.nextInt();
      s.nextLine();
      String line = s.nextLine();
      String[] lineIntegers = line.split(",");
      minTimeArrival = Integer.parseInt(lineIntegers[0]);
      maxTimeArrival = Integer.parseInt(lineIntegers[1]);
      line = s.nextLine();
      lineIntegers = line.split(",");
      minTimeService = Integer.parseInt(lineIntegers[0]);
      maxTimeService = Integer.parseInt(lineIntegers[1]);
      initSimulation();
    } catch (IOException e) {
      System.out.println("File not found");
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Not enough arguments");
    }
  }
}
