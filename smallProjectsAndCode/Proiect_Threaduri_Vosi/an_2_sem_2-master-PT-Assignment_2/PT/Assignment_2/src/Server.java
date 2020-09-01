import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
  private final int id;
  private BlockingQueue<Client> clientList;
  private AtomicInteger waitingPeriod;
  private volatile CyclicBarrier managerBarrier;

  /**
   * Initializes a server for its queue.
   *
   * @param id serverID
   */
  public Server(int id) {
    this.id = id;
    clientList = new LinkedBlockingQueue<>();
    waitingPeriod = new AtomicInteger(0);
    managerBarrier = new CyclicBarrier(2);
  }

  /**
   * Fetches its id.
   *
   * @return server's id
   */
  public int getId() {
    return id;
  }

  /**
   * Adds client to server's queue.
   *
   * @param client client to be added
   */
  public void addClient(Client client) {
    clientList.add(client);
    client.setWaitingTime(waitingPeriod.get() + client.getServiceTime());
    waitingPeriod.addAndGet(client.getServiceTime());
  }

  public BlockingQueue<Client> getClientList() {
    return clientList;
  }

  public AtomicInteger getWaitingPeriod() {
    return waitingPeriod;
  }

  public CyclicBarrier getBarrier() {
    return managerBarrier;
  }

  @Override
  public void run() {
    try {
      while (true) {
        managerBarrier.await();
        if (clientList.size() > 0) {
          clientList.peek().decServiceTime();
          waitingPeriod.addAndGet(-1);
          if (clientList.peek().getServiceTime() == 0) {
            clientList.take();
          }
          if (clientList.size() == 0) {
            break;
          }
        } else {
          break;
        }
      }
    } catch (InterruptedException | BrokenBarrierException e) {
      // Probably got stuck waiting after finishing the simulation,bail out
    }
  }
}
