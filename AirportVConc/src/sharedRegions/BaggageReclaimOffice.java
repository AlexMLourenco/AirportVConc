package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;

public class BaggageReclaimOffice {

    private RepositoryInfo repository;

    public BaggageReclaimOffice(RepositoryInfo repository){
        this.repository = repository;
    }

    /***** PASSENGER FUNCTIONS *********/
    public synchronized void reportMissingBag(){

        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE);
        try {
            passenger.sleep(2000);
        } catch (Exception e) { }

    }

}
