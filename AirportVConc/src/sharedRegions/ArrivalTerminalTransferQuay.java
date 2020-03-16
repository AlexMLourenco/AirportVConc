package sharedRegions;

import java.util.*;

import mainProject.*;
import commonInfra.*;

public class ArrivalTerminalTransferQuay {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Passengers waiting for a bus at the disembarking zone
     * @serialField waitingForBus
     */
    //private FIFO waitingForBus;

    // Alterar
    private Queue<Integer> waitingForBus;

    /**
     * Arrival Terminal Transfer Quay instantiation
     @param repository repositoryInfo
     */
    public ArrivalTerminalTransferQuay(RepositoryInfo repository){
        this.repository = repository;
        this.waitingForBus = new LinkedList<>();
    }

    /* Passenger functions */

    /**
     * Passenger take a bus
     * @return
     */
    public synchronized void takeABus(int id){
        System.out.println("Passenger " + id + " is waiting for a bus!");
        //Passenger passenger = (Passenger) Thread.currentThread();
        //passenger.setPassengerState(PassengerStates.AT_THE_ARRIVAL_TRANSFER_TERMINAL);

        waitingForBus.add(id);  // Add the passengers to the list of passengers waiting for the bus
        if (waitingForBus.size() == SimulPar.BUS_CAPACITY) {
            notifyAll();
        }



    }

    /* Driver functions */

    /**
     * Driver parks the bus
     * @return
     */
    public void parkTheBus(){

    }

}
