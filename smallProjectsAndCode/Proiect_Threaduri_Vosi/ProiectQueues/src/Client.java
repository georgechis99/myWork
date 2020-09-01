public class Client { //Class to manage the clients that are about to be created

    private final int id;
    private final int tArrival;
    private int tService;
    private int waitingTime;
    private boolean currentlyInAQueue;

    //Client constructor
    public Client(int id, int tArrival, int tService) {
        this.id = id;
        this.tArrival = tArrival;
        this.tService = tService;
        this.waitingTime = tService + 1;
        this.currentlyInAQueue = false;
    }

    //Setters and getters
    public int gettService() {
        return tService;
    }

    public boolean isCurrentlyInAQueue() {
        return currentlyInAQueue;
    }

    public void setCurrentlyInAQueue(boolean currentlyInAQueue) {
        this.currentlyInAQueue = currentlyInAQueue;
    }

    public int gettArrival() {
        return tArrival;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    //to print the client with the current waiting time
    @Override
    public String toString() {
        return "(" + id +
                "," + tArrival +
                "," + (waitingTime + 1) +
                ")";
    }

    //to print the client with the initial service time
    public String toString2() {
        return "(" + id +
                "," + tArrival +
                "," + tService +
                ")";
    }
}
