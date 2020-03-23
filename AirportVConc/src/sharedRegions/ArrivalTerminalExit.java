package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;

public class ArrivalTerminalExit {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Arrival Terminal Exit instantiation
     @param repository repositoryInfo
     */
    public ArrivalTerminalExit(RepositoryInfo repository){
        this.repository = repository;
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passenger goes home
     */
    public synchronized void goHome(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.EXITING_THE_ARRIVAL_TERMINAL);
    }

}
