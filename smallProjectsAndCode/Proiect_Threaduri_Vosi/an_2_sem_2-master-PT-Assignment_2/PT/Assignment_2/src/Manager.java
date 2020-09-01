import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.BrokenBarrierException;

public class Manager implements Runnable {

  private PriorityQueue<Client> generatedClients;
  private ArrayList<Thread> threadList;
  private ArrayList<Server> serversList;
  private FileWriter outputWriter;
  private int simulationTime;
  private int finalTime;
  private int clientsServed;
  private int totalTimeSpent;

  /**
   * Constructor for the Manager class.
   *
   * @param outputWriter output stream for the output
   * @param finalTime maximum time allowed for the simulation
   * @param noOfServers number of queues
   * @param generatedClients priority queue of generated clients
   */
  public Manager(
      FileWriter outputWriter,
      int finalTime,
      int noOfServers,
      PriorityQueue<Client> generatedClients) {
    this.outputWriter = outputWriter;
    this.generatedClients = generatedClients;
    this.finalTime = finalTime;
    this.simulationTime = 0;
    serversList = new ArrayList<>();
    threadList = new ArrayList<>();
    for (int i = 0; i < noOfServers; i++) {
      Server newServer = new Server(i);
      serversList.add(i, newServer);
      threadList.add(i, new Thread(newServer));
    }
  }

  /** Distributes arrived clients to their queues. */
  public void distributeClients() {
    while (generatedClients.peek() != null
        && generatedClients.peek().getArrivalTime() <= simulationTime) {
      int min = 9999;
      Server minServer = null;
      for (Server server : serversList) {
        if (server.getWaitingPeriod().get() < min) {
          min = server.getWaitingPeriod().get();
          minServer = server;
        }
      }
      minServer.addClient(generatedClients.remove());
      clientsServed++;
      totalTimeSpent += minServer.getClientList().peek().getWaitingTime();
    }
  }

  /** Prints the status of the simulation(Time,Waiting clients,Queues). */
  public void printStatus() {
    try {
      outputWriter.write("Time " + simulationTime + "\n");
      outputWriter.write("Waiting clients: ");
      for (Client waitingClient : generatedClients) {
        outputWriter.write(waitingClient.toString() + ";");
      }
      outputWriter.write("\n");
      for (Server currentServer : serversList) {
        outputWriter.write("Queue " + (currentServer.getId() + 1) + ": ");
        if (currentServer.getClientList().size() == 0) {
          outputWriter.write("closed");
        }
        for (Client servedClient : currentServer.getClientList()) {
          outputWriter.write(servedClient.toString() + ";");
        }
        outputWriter.write("\n");
      }
    } catch (IOException e) {
      System.out.println("Unable to write in file");
    }
  }

  /**
   * Check if the simulation has ended.
   *
   * @return the boolean flag for ending the simulation
   */
  public boolean checkEnd() {
    if (generatedClients.size() > 0) {
      return false;
    }
    for (Server currentServer : serversList) {
      if (currentServer.getClientList().size() > 0) {
        return false;
      }
    }
    return true;
  }

  /** Force terminate the threads after ending the simulation. */
  public void terminateThreads() {
    try {
      outputWriter.write("Average waiting time: " + (float) totalTimeSpent / clientsServed + "\n");
      outputWriter.close();
    } catch (IOException e) {
      System.out.println("Unable to close file");
    }
    for (Thread currentThread : threadList) {
      currentThread.interrupt();
    }
  }

  /** (re)starts any thread which received a new client. */
  public void restartThreads() {
    for (Server currentServer : serversList) {
      if (currentServer.getClientList().size() > 0) {
        int index = serversList.indexOf(currentServer);
        if (threadList.get(index).getState() == Thread.State.TERMINATED) {
          threadList.set(index, new Thread(currentServer));
          threadList.get(index).start();
        }
        if (threadList.get(index).getState() == Thread.State.NEW) {
          threadList.get(index).start();
        }
      }
    }
  }

  /**
   * Syncs the running threads to reach either termination or the cycleBlock.
   */
  public void syncThreads() {
    try {
      for (Thread currentThread : threadList) {
        currentThread.join(100);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    try {
      while (simulationTime <= finalTime && !checkEnd()) {
        if (generatedClients.size() > 0) {
          distributeClients();
        }
        syncThreads();
        printStatus();
        restartThreads();
        for (Server currentServer : serversList) {
          if (currentServer.getClientList().size() > 0) {
            currentServer.getBarrier().await();
          }
        }
        simulationTime++;
      }
      syncThreads();
      terminateThreads();
    } catch (InterruptedException | BrokenBarrierException e) {
      e.printStackTrace();
    }
  }
}
