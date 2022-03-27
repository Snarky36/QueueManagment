package controller;

import model.Client;
import model.Queue;
import view.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private int numberOfQueues;
    private int maxTasksPerQueue;
    private int simulationTime;
    private double averageTimePerHour;
    private static double totalAverageTime;
    private static int waitingPeakHour;
    private static int peakHour;
    private Strategy strategy;
    private List<Queue> queues;

    public Scheduler(int numberOfQueues, SelectionPolicy mode, int simulationTime){
        this.queues = new ArrayList<>(numberOfQueues+1);
        this.numberOfQueues = numberOfQueues;
        this.simulationTime = simulationTime;
        this.averageTimePerHour = 0;
        totalAverageTime = 0;
        waitingPeakHour = 0;
        changeStrategy(mode);
        for(int i = 0 ;i < numberOfQueues ; i++){
            Queue newQue = new Queue(i,mode);
            queues.add(newQue);
        }
        for(Queue thread1:queues){
            Thread t = new Thread(thread1);
            t.start();
        }
        System.out.println("All threads have started and are ready!");

    }

    public void changeStrategy(SelectionPolicy mode){
        if(mode == SelectionPolicy.SHORTEST_TIME){
            strategy = new TimeStrategy();
        }
        else{
            strategy = new ShortestQueueStrategy();
        }
    }

    public void calculatePeakHour(int time){
        int maxWaitingTime = 0;
        for(Queue q: queues){
            maxWaitingTime += q.getWaitingTime();
        }
        if(waitingPeakHour < maxWaitingTime){
            waitingPeakHour = maxWaitingTime;
            peakHour = time;
        }
    }

    public static int getWaitingPeakHour() {
        return waitingPeakHour;
    }

    public static int getPeakHour() {
        return peakHour;
    }

    public void calculateAverage(){
        int queuesUsed = 0;
        this.averageTimePerHour = 0;
        for(Queue q1: this.queues){
            if(!q1.getClients().isEmpty()){
                queuesUsed++;
                this.averageTimePerHour +=q1.getWaitingTime();
            }
        }
        if(queuesUsed != 0){
        this.averageTimePerHour /= queuesUsed;
        }
        totalAverageTime += this.averageTimePerHour;
    }

    public void afisareQues(GUI simulationView) {

        for(Queue q1: this.queues){
            simulationView.addToTextArea(q1.toString()+"\n");
        }
        simulationView.addToTextArea("\nAverage Waiting Time for this Hour is "+this.averageTimePerHour+"\n");
    }
    public void dispachClients(Client client1){
        this.strategy.addClient(queues,client1);

    }

    public double getAverageTimePerHour() {
        return averageTimePerHour;
    }

    public void setAverageTimePerHour(double averageTimePerHour) {
        this.averageTimePerHour = averageTimePerHour;
    }

    public static double getTotalAverageTime() {
        return totalAverageTime;
    }

    public int getNumberOfQueues() {
        return numberOfQueues;
    }

    public void setNumberOfQueues(int numberOfQueues) {
        this.numberOfQueues = numberOfQueues;
    }

    public int getMaxTasksPerQueue() {
        return maxTasksPerQueue;
    }

    public void setMaxTasksPerQueue(int maxTasksPerQueue) {
        this.maxTasksPerQueue = maxTasksPerQueue;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public List<Queue> getQueues() {
        return queues;
    }

    public void setQueues(List<Queue> queues) {
        this.queues = queues;
    }
}
