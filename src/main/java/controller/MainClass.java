package controller;

import view.GUI;

public class MainClass {
    public static void main(String[] args) throws InterruptedException {
        //SimulationManager manager = new SimulationManager(2,4,15,2,6,1,4, SelectionPolicy.SHORTEST_TIME);
        //Thread t = new Thread(manager);
        //t.start();
        GUI simulationView = new GUI();
        simulationView.setVisible(true);
        SimulationManager manager = new SimulationManager(SelectionPolicy.SHORTEST_TIME,simulationView);

    }
}
