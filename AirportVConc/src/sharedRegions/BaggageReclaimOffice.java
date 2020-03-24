package sharedRegions;

import entities.Passenger;
import entities.PassengerStates;

public class BaggageReclaimOffice {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Baggage Reclaim Office instantiation
     *
     @param repository repositoryInfo
     *
     */
    public BaggageReclaimOffice(RepositoryInfo repository){
        this.repository = repository;
    }

    /***** PASSENGER FUNCTIONS *********/

    /**
     * Passengers reports a missing bag
     *
     */
    public synchronized void reportMissingBag(){
        Passenger passenger = (Passenger) Thread.currentThread();
        repository.setPassengerState(passenger.getIdentifier(), PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE);
        repository.bagsLost++;
        try {
            passenger.sleep(2000);
        } catch (Exception e) { }

    }

}
