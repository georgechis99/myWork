import java.util.LinkedList;

public class Queue implements Runnable{ //Class to manage the queues available

    private int id;
    private LinkedList<Client> clientsInQueue;
    private int queueWaitingTime;
    private boolean occupied;

    //Queue default constructor
    public Queue() {
    }

    //Queue explicit constructor
    public Queue(int id) {
        this.id = id;
        this.clientsInQueue = new LinkedList<>();
        this.queueWaitingTime = 0;
        this.occupied = false;
    }

    //method to add a client tot the queue
    public void addClient(Client client){
        clientsInQueue.add(client);
        queueWaitingTime = client.gettService();
        client.setWaitingTime(queueWaitingTime);
    }

    //Setters and getters
    public int getId() {
        return id;
    }

    public LinkedList<Client> getClientsInQueue() {
        return clientsInQueue;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    @Override
    public void run() {
        System.out.println("Queue thread started.");
    }
}
