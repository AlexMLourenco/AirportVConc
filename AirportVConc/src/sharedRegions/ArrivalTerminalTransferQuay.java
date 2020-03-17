package sharedRegions;

import java.util.*;

import mainProject.*;

public class ArrivalTerminalTransferQuay {

    private RepositoryInfo repository;

    //private FIFO waitingForBus;

    // Alterar
    private Queue<Integer> waitingForBus;

    public ArrivalTerminalTransferQuay(RepositoryInfo repository){
        this.repository = repository;
        this.waitingForBus = new LinkedList<>();
    }

    /* Passenger functions */

    public synchronized void takeABus(int id){
        System.out.println("Passenger " + id + " is waiting for a bus!");
        waitingForBus.add(id);
        repository.registerPassengerToTakeABus(id);
         // Add the passengers to the list of passengers waiting for the bus
        if (waitingForBus.size() == SimulPar.BUS_CAPACITY ) {
            notifyAll();
        }
    }

    public synchronized void waitForBus(int id) {
        while(true){
            try{
                wait();

                //Se o passageiro estiver nos 3 primeiros forcar a saida do ciclo

            }catch(InterruptedException e){}
        }
    }

    /* Driver functions */
    public synchronized void readyToDeparture() {
        try{
            wait(SimulPar.BUS_SCHEDULE_MILLIS);
            System.out.println("WAKE UP BUS DRIVER");
        }catch(InterruptedException e){

        }

    }

    /**
     * Driver parks the bus
     * @return
     */
    public void parkTheBus(){

    }

    public synchronized void announcingBusBoarding(){
        // acordar as threads dos 3 primeiros passageiros
    }

}
