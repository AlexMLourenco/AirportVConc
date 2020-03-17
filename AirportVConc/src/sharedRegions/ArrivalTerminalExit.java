package sharedRegions;

import java.util.Queue;

public class ArrivalTerminalExit {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repository;

    private Queue<Integer> inTheBus;

    /**
     * Arrival Terminal Exit instantiation
     @param repository repositoryInfo
     */
    public ArrivalTerminalExit(RepositoryInfo repository){
        this.repository = repository;
    }

    /* Passenger functions */

    /**
     * Passenger enters in the bus
     * @return
     */
    public void enterTheBus(int id){
        System.out.println("Passenger " + id + " is entering the bus!");
        // TODO: falta condiçao para verificar 3 primeiros
        // remover da lista de espera
        // adicionar ao bus

    }

    /**
     * Passenger goes home
     * @return
     */
    public synchronized void goHome(){
        //Passenger passenger = (Passenger) Thread.currentThread();
        //passenger.setPassengerState(PassengerStates.EXITING_THE_ARRIVAL_TERMINAL);
    }

}
