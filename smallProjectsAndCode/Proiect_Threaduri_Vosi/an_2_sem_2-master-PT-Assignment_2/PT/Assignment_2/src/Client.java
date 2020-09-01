import java.util.Comparator;

public class Client implements Comparator<Client> {
  private final int id;
  private final int arrivalTime;
  private int serviceTime;
  private int waitingTime;

  /** Client default constructor. */
  public Client() {
    this.id = -1;
    this.arrivalTime = 0;
    this.serviceTime = 0;
  }

  /**
   * Client constructor based on id,time of arrival and time needed to be served.
   *
   * @param id - client's id
   * @param arrivalTime arrival time in the simulation
   * @param serviceTime service time needed
   */
  public Client(int id, int arrivalTime, int serviceTime) {
    this.id = id;
    this.arrivalTime = arrivalTime;
    this.serviceTime = serviceTime;
  }

  public int getArrivalTime() {
    return arrivalTime;
  }

  public int getServiceTime() {
    return serviceTime;
  }

  public void decServiceTime() {
    serviceTime--;
  }

  public void setWaitingTime(int waitingTime) {
    this.waitingTime = waitingTime;
  }

  public int getWaitingTime() {
    return waitingTime;
  }

  @Override
  public String toString() {
    return "(" + id + "," + arrivalTime + "," + serviceTime + ")";
  }

  @Override
  public int compare(Client client, Client t1) {
    return client.arrivalTime - t1.arrivalTime;
  }
}
