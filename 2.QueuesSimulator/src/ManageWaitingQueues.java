import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ManageWaitingQueues implements Runnable {

    FileWriter out;
    private int numberOfClients;
    private int numberOfQueues;
    private int tSimulationMax;
    private int tArrivalMin, tArrivalMax;
    private int tServiceMin, tServiceMax;
    private LinkedList<Client> waitingClients;
    private LinkedList<Queue> availableQueues;
    private List<Thread> queuesThreads = new ArrayList<>();

    public ManageWaitingQueues(int numberOfClients,
                               int numberOfQueues,
                               int tSimulationMax,
                               int tArrivalMin,
                               int tArrivalMax,
                               int tServiceMin,
                               int tServiceMax) {
        this.availableQueues = new LinkedList<>();
        this.waitingClients = new LinkedList<>();
        this.numberOfClients = numberOfClients;
        this.numberOfQueues = numberOfQueues;
        this.tSimulationMax = tSimulationMax;
        this.tArrivalMin = tArrivalMin;
        this.tArrivalMax = tArrivalMax;
        this.tServiceMin = tServiceMin;
        this.tServiceMax = tServiceMax;
    }

    //randomly generate clients with parameters in the specified bounds
    public Client generateClient(int id) {

        int tArrival = (int) (Math.random() * tArrivalMax) + tArrivalMin;
        int tService = (int) (Math.random() * tServiceMax) + tServiceMin;

        return new Client(id, tArrival, tService);
    }

    public void populateWaitingClients() {

        for (int i = 0; i < numberOfClients; i++) {
            waitingClients.add(generateClient(i + 1));
        }
    }

    public void addAvailableQueues() {

        for (int i = 0; i < numberOfQueues; i++) {
            availableQueues.add(new Queue(i + 1));
        }
    }

    //method for removing a certain Client object in the availableClients list
    public static void removeClient(Client client, LinkedList<Client> clients) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).equals(client)) {
                clients.remove(i);
                break;
            }
        }

    }

    //method for calculating the average waiting time
    public double averageWaitingTime(LinkedList<Client> waitingClients){
        double average = 0;
        for(Client client : waitingClients){
            average += client.gettService();
        }
        return average/(waitingClients.size());
    }

    @Override
    public void run() {

        //creating the output file
        try {
            out = new FileWriter(new File("Out-Test2.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentTime = 0;
        populateWaitingClients();
        addAvailableQueues();

        //getting the average waiting time here(later it will not be possible, because of the removing of the clients from the list)
        double average = averageWaitingTime(waitingClients);

        for (int i = 0; i < numberOfQueues; i++) {
            Thread t = new Thread(availableQueues.get(i));
            queuesThreads.add(t);
        }

        queuesThreads.forEach(Thread::start);

        //running the simulation
        while (currentTime <= tSimulationMax && waitingClients.size() != 0) {

            //printing in the output file
            try {
                out.write("\n");
                out.write("Time " + currentTime + "\n");
                out.write("Waiting clients: ");
                for (Client waitingClient : waitingClients) {
                    if(!waitingClient.isCurrentlyInAQueue()) {
                        out.write(waitingClient.toString2() + ";");
                    }
                }
                out.write("\n");
                for (Queue queue : availableQueues) {
                    out.write("Queue " + queue.getId() + ": ");
                    if (queue.isOccupied()) {
                        out.write(queue.getClientsInQueue().get(0).toString() + ";" + "\n");
                    } else {
                        out.write("closed \n");
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            //testing if we can add clients to queues
            for (Client cBuff : waitingClients) {
                if (currentTime >= cBuff.gettArrival() - 1 && !(cBuff.isCurrentlyInAQueue())) {
                    for (Queue qBuff : availableQueues) {
                        if (!qBuff.isOccupied()) {
                            cBuff.setCurrentlyInAQueue(true);
                            qBuff.addClient(cBuff);
                            qBuff.setOccupied(true);
                            break;
                        }
                    }
                }
            }

            //testing if there are queues occupied and decreasing the waitingTime f clients
            for (Queue qBuff : availableQueues) {
                if (qBuff.isOccupied()) {
                    Client clientInQueue = qBuff.getClientsInQueue().get(0);
                    if (clientInQueue.getWaitingTime() < 1) {
                        removeClient(qBuff.getClientsInQueue().get(0), waitingClients);
                        qBuff.getClientsInQueue().remove(0);
                        qBuff.setOccupied(false);
                    } else {
                        clientInQueue.setWaitingTime(clientInQueue.getWaitingTime() - 1);
                    }
                }
            }
            currentTime++;
        }

        //printing the average waiting time
        try {
            out.write("Average waiting time: " + average);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
