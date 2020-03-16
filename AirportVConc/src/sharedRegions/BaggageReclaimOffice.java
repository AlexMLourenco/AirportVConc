package sharedRegions;

public class BaggageReclaimOffice {

    /**
     * General Repository of Information
     * @serialField repository
     */
    private RepositoryInfo repository;

    /**
     * Baggage Reclaim Office instantiation
     @param repository repositoryInfo
     */
    public BaggageReclaimOffice(RepositoryInfo repository){
        this.repository = repository;
    }

    /* Passenger functions */

    /**
     * Passenger reports a missing bag
     * @return
     */
    public synchronized void reportMissingBag(){
        //Passenger passenger = (Passenger) Thread.currentThread();
        //passenger.setPassengerState(PassengerStates.AT_THE_BAGGAGE_RECLAIM_OFFICE);

    }

}
