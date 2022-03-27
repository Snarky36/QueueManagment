package model;

public class Client implements Comparable<Client>{
    private int ID;
    private int tService;
    private int tArrival;
    private boolean inQueue;

    public Client(int ID, int tService, int tArrival){
        this.ID = ID;
        this.tService = tService;
        this.tArrival = tArrival;
        this.inQueue = false;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int gettService() {
        return tService;
    }

    public void settService(int tService) {
        this.tService = tService;
    }

    public int gettArrival() {
        return tArrival;
    }

    public void settArrival(int tArrival) {
        this.tArrival = tArrival;
    }


    @Override
    public int compareTo(Client client1) {
        return this.tArrival - client1.gettArrival();
    }

    public boolean isInQueue() {
        return inQueue;
    }

    public void isInQueue(boolean status) {
        this.inQueue = status;
    }

    public String toString(){
        return "("+ this.ID + ", "+this.tArrival + ", "+this.tService + "); ";
    }
}
