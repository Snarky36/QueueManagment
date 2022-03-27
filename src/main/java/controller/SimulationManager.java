package controller;

import model.Client;
import model.Queue;
import view.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.lang.Thread;

public class SimulationManager implements Runnable{
    private Scheduler scheduler;
    private List<Client> clients;
    private SelectionPolicy mode;
    private int numberOfClients;
    private int numberOfQueues;
    private int simulationTime;
    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;
    private double averageServiceTime;
    private boolean threadRunning;
    GUI simulationView;

    public SimulationManager(SelectionPolicy mode,GUI simulationView){
        this.mode = mode;
        this.averageServiceTime = 0;
        this.simulationView = simulationView;
        this.inputs();
        this.sliders();
    }

    public void inputs(){
        simulationView.startSimulationButtonListener(e -> {
            simulationTime = simulationView.getSimulationTimeText();
            numberOfQueues = simulationView.getNumberQueueText();
            numberOfClients = simulationView.getNumberClientsText();
            minArrival = simulationView.getMinArrivalText();
            maxArrival = simulationView.getMaxArrivalText();
            minService = simulationView.getMinServiceText();
            maxService = simulationView.getMaxServiceText();

            clients = new ArrayList<>(numberOfClients);

            simulationView.setTimeLeftLabel(""+simulationTime);
            simulationView.setTextArea("");
            this.threadRunning = true;
            Thread t = new Thread(this);
            t.start();

        });
    }
    public void sliders(){
        simulationView.numberQueueSliderListener(e -> {
            simulationView.getQueueSlider();
        });
        simulationView.NumberClientsSliderListener(e->{
            simulationView.getClientsSlider();
        });
        simulationView.simulationTimeSliderListener(e -> {
            simulationView.getTimeSlider();
        });
    }
    public void generateData() {
        System.out.println("Generated clients : ");
        for (int i = 0; i < this.numberOfClients; i++) {
            int tArrival = (int) Math.floor(Math.random() * (this.maxArrival - this.minArrival + 1) + this.minArrival);
            int tService = (int) Math.floor(Math.random() * (this.maxService - this.minService + 1) + this.minService);
            this.averageServiceTime+= tService;
            Client client1 = new Client(i, tService, tArrival);
            System.out.println(client1);
            clients.add(client1);
        }
        Collections.sort(clients);
        System.out.println("Clients were generated and sorted on Arrival time!\n");
    }
    public boolean AllTasksAreFinished(){
        boolean waitingListIsEmpty = true;
        boolean queuesDone = true;

        for(Client client1:clients) {
         if (!client1.isInQueue()){
             waitingListIsEmpty = false;
             break;
         }
        }
        for(Queue q: scheduler.getQueues()){
            if(!q.getClients().isEmpty()){
                queuesDone = false;
                break;
            }
        }
        return waitingListIsEmpty && queuesDone;
    }
    @Override
    public void run() {
        generateData(); // generez clientii random
        this.scheduler = new Scheduler(this.numberOfQueues,this.mode, this.simulationTime);//initializez threadurile si le porneste
        int time = 0;
        int timeLeft = this.simulationTime;
        int allTaskFinishedTime = 1;
        boolean endIfAllTaskFinishedTime = true;
        while(time < this.simulationTime){
            timeLeft = this.simulationTime -time;
            this.simulationView.setTimerLabel("" + timeLeft);
            this.simulationView.addToTextArea("---------------------------\n");
            this.simulationView.addToTextArea("Time = " + time+ "\n");
            this.simulationView.addToTextArea("---------------------------\n");
            this.simulationView.addToTextArea("\nWaiting list:");
            for(Client client1:clients){
                if(!client1.isInQueue()) {
                    if (client1.gettArrival() <= time) {
                        scheduler.dispachClients(client1);
                    }
                    else{
                        this.simulationView.addToTextArea(client1.toString());
                    }
                }
            }

            this.scheduler.calculatePeakHour(time);

            if(AllTasksAreFinished() && endIfAllTaskFinishedTime){
                allTaskFinishedTime = time;
                endIfAllTaskFinishedTime = false;

            }
            scheduler.calculateAverage();
            this.simulationView.addToTextArea("\n");
            scheduler.afisareQues(this.simulationView);

            try {

                Thread.sleep(1050);
                time++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for(Queue q:scheduler.getQueues()){
            q.setCanBeClosed(true);
        }
        this.simulationView.addToTextArea("------------------------------------");
        this.simulationView.addToTextArea("\nAverage Service Time = "+ this.averageServiceTime/this.numberOfClients);
        this.simulationView.addToTextArea("\nAverage Waiting Time = " + Scheduler.getTotalAverageTime()/allTaskFinishedTime);
        this.simulationView.addToTextArea("\nPeak hour was on "+ Scheduler.getPeakHour() + " and the waiting time was "+Scheduler.getWaitingPeakHour());

    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public SelectionPolicy getMode() {
        return mode;
    }

    public void setMode(SelectionPolicy mode) {
        this.mode = mode;
    }

    public int getNumberOfClients() {
        return numberOfClients;
    }

    public void setNumberOfClients(int numberOfClients) {
        this.numberOfClients = numberOfClients;
    }

    public int getNumberOfQueues() {
        return numberOfQueues;
    }

    public void setNumberOfQueues(int numberOfQueues) {
        this.numberOfQueues = numberOfQueues;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public void setSimulationTime(int simulationTime) {
        this.simulationTime = simulationTime;
    }

    public int getMinArrival() {
        return minArrival;
    }

    public void setMinArrival(int minArrival) {
        this.minArrival = minArrival;
    }

    public int getMaxArrival() {
        return maxArrival;
    }

    public void setMaxArrival(int maxArrival) {
        this.maxArrival = maxArrival;
    }

    public int getMinService() {
        return minService;
    }

    public void setMinService(int minService) {
        this.minService = minService;
    }

    public int getMaxService() {
        return maxService;
    }

    public void setMaxService(int maxService) {
        this.maxService = maxService;
    }
}
