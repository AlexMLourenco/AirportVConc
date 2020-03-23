package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;

public class ArrivalTerminalExit {

    private RepositoryInfo repository;

    public ArrivalTerminalExit(RepositoryInfo repository){
        this.repository = repository;
    }

    /***** PASSENGER FUNCTIONS *********/

    public synchronized void goHome(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.EXITING_THE_ARRIVAL_TERMINAL);
    }

}
