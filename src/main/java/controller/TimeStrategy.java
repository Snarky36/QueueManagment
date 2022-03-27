package controller;

import model.Client;
import model.Queue;

import java.util.List;

public class TimeStrategy extends Strategy{
    @Override
    public void addClient(List<Queue> queues, Client client1) {
        Queue lowerQueue = null;
        int minWaitingTime = Integer.MAX_VALUE;
        for(Queue q: queues){
            if(q.getWaitingTime() < minWaitingTime){
                lowerQueue = q;
                minWaitingTime = q.getWaitingTime();
            }
        }
        if(lowerQueue != null) {
            lowerQueue.addNewClient(client1);

        }


    }
}
