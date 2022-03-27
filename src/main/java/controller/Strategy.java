package controller;

import model.Client;
import model.Queue;

import java.util.List;

public abstract class Strategy {
    public abstract void addClient(List<Queue> queues, Client client1);
}
