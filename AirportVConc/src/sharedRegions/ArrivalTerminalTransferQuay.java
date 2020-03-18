package sharedRegions;

import java.util.*;

import mainProject.*;

public class ArrivalTerminalTransferQuay {

    private RepositoryInfo repository;

    private Queue<Integer> waitingForBus;
    private boolean busDriverReadyToReceivePassengers = false;

    public ArrivalTerminalTransferQuay(RepositoryInfo repository){
        this.repository = repository;
        this.waitingForBus = new LinkedList<>();
    }

    /* Passenger functions */

    public synchronized void takeABus(int id){
        //System.out.println("Passenger " + id + " is waiting for a bus!");
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
                if (this.busDriverReadyToReceivePassengers) {
                    Object[] tempArr = waitingForBus.toArray();

                    for (int i=0; i < 3; i++) {
                        if (id == (Integer)tempArr[i]) {
                            notifyAll();
                            return;
                        }
                    }
                }
            }catch(InterruptedException e){}
        }
    }

    /* Driver functions */
    public synchronized void readyToDeparture() {
        try{
            while(true) {
                this.busDriverReadyToReceivePassengers = false;
                wait(SimulPar.BUS_SCHEDULE_MILLIS);
                System.out.println("Bus Driver ready to receive passengers");
                this.busDriverReadyToReceivePassengers = true;
                if (waitingForBus.size() > 0) {
                    int numberOfPassengers = (waitingForBus.size() > 3? 3:waitingForBus.size());
                    while (true) {
                        wait();
                        System.out.println("CNT" + repository.getInTheBusCount());
                        if (numberOfPassengers == repository.getInTheBusCount()) {
                            System.out.println("PRONTO A PARTIR");
                            break;
                        }
                        System.out.println("NAO ESTOU PRONTO");
                    }
                    break;
                } else {
                    System.out.println("Nao tenho ninguem para levar");
                }

            }

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
