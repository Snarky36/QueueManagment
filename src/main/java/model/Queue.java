package model;

import controller.SelectionPolicy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Thread;

//Server e chivalentul clasei consumer din modelul OOP de Producer-Consumer;
public class Queue implements Runnable, Comparable<Queue>{
    int queueID;
    private SelectionPolicy mode;
    private BlockingQueue<Client> clients;
    private AtomicInteger WaitingTime;
    private AtomicBoolean canBeClosed;
    public Queue(int queueID,SelectionPolicy mode){
        this.queueID = queueID;
        this.mode = mode;
        this.clients = new LinkedBlockingQueue<Client>();
        this.WaitingTime = new AtomicInteger(0);
        this.canBeClosed = new AtomicBoolean(false);
    }

    public void addNewClient(Client client1){
        client1.isInQueue(true);
        WaitingTime.getAndAdd(client1.gettService());
        clients.add(client1);

    }

    public void waitingTimeCalc(){
        int sum = 0;
        for(Client client1: this.clients){
            sum+= client1.gettService();
        }
        this.WaitingTime.set(sum);
    }

    public AtomicBoolean getCanBeClosed() {
        return canBeClosed;
    }

    public void setCanBeClosed(boolean canBeClosed) {
        this.canBeClosed.set(canBeClosed);
    }

    public int getqueueID() {
        return queueID;
    }

    public void setqueueID(int queueID) {
        this.queueID = queueID;
    }


    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void setClients(BlockingQueue<Client> clients) {
        this.clients = clients;
    }

    public int getWaitingTime() {
        return this.WaitingTime.get();
    }

    public void setWaitingTime(int averageWaitingTime) {
        this.WaitingTime = WaitingTime;
    }

    @Override
    public void run() {
        //System.out.println("Queue(ID:" + this.queueID + ") initialized!");
        while(!this.canBeClosed.get()){

            if(!clients.isEmpty()){
                try {

                    Client headClient = clients.peek();
                    if(headClient.gettService() > 0) {
                      int time = headClient.gettService();
                        //System.out.println("Q"+headClient+" "+time);
                        while(headClient.gettService() > 0) {
                            Thread.sleep(1000);
                            headClient.settService(--time);
                            waitingTimeCalc();  // reactualizez waiting time pentru queue la fiecare secunda care a trecut;
                            if(headClient.gettService() == 0) {
                                System.out.println("Q" + this.queueID + ":" + headClient+ " is done.");
                                clients.remove(headClient);
                            }

                        }
                    }
                    else{
                        clients.remove(headClient);
                    }
                } catch (InterruptedException e) {
                    //continue;
                }
            }
            //System.out.println(this);
        }

    }



    @Override
    public String toString(){
        String mesaj = "";
        if(clients.isEmpty()){
            mesaj = "Q" + this.queueID + "("+this.getWaitingTime()+") -closed";
        }
        else {
            mesaj = "Q"+ this.getqueueID()+ "(wt = "+ this.getWaitingTime()+ "):"+" members: ";

            for (Client client1 : clients) {
                mesaj += client1.toString();
            }
        }
        return mesaj;
    }

    @Override
    public int compareTo(Queue o) {
        if(this.mode == SelectionPolicy.SHORTEST_QUEUE){
            return this.WaitingTime.get() - o.getWaitingTime();
        }
        else {
            return this.clients.size() - o.getClients().size();
        }
    }

    public SelectionPolicy getMode() {
        return mode;
    }

    public void setMode(SelectionPolicy mode) {
        this.mode = mode;
    }
}
