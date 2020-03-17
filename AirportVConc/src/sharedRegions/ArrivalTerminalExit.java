package sharedRegions;

public class ArrivalTerminalExit {

    /**
     * General Repository of Information
     * @serialField repo
     */
    private RepositoryInfo repository;

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
