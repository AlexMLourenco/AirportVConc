package sharedRegions;

import java.util.Queue;

public class ArrivalTerminalExit {

    private RepositoryInfo repository;

    private Queue<Integer> inTheBus;

    public ArrivalTerminalExit(RepositoryInfo repository){
        this.repository = repository;
    }

    /* Passenger functions */

    public synchronized void enterTheBus(int id){
        try {
            System.out.println("Passenger " + id + " is entering the bus!");
            repository.registerPassengerToEnterTheBus(id);
            wait();
        }catch(InterruptedException e){}

    }




    /**
     * Passenger goes home
     * @return
     */
    public synchronized void goHome(){
        // TODO: goHome()
        //Passenger passenger = (Passenger) Thread.currentThread();
        //passenger.setPassengerState(PassengerStates.EXITING_THE_ARRIVAL_TERMINAL);
    }

}
